package com.xian.demo.task;

import com.xian.demo.dao.OrderMapper;
import com.xian.demo.dao.ProductEsMapper;
import com.xian.demo.dao.ProductMapper;
import com.xian.demo.entity.Product;
import com.xian.demo.service.impl.ProductServiceImpl;
import com.xian.demo.util.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

@Component
@EnableScheduling   // 开启定时任务
@EnableAsync        // 开启多线程
public class Task {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderMapper orderMapper;
    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductEsMapper productEsMapper;
    @Autowired
    private ProductMapper productMapper;
    // 这个时间的来源是 1.项目启动时，由ES查出来的，  2.有系统每次更新，记录下数据表中的最后修改时间
    private Date lastModfiyTime = null;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * @describe  每隔一分钟去定时执行，同步数据库中的数据到ES
     */
    @Async
    @Scheduled(fixedDelay = 1000 * 20)
    public void syncProductToEs () {

        logger.info("job----sync----product----to -----es");
        if (this.getLastModfiyTime() == null) { // 说明是项目启动第一次执行的情况
            elasticsearchTemplate.deleteIndex(Product.class);
            SimpleDateFormat sdfd = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            Date defaultTime = null;
            try {
                defaultTime = sdfd.parse("2000-00-00 00:00:00");
            } catch (ParseException e) {
                logger.error("parse Error"+ e.getMessage());
                defaultTime = new Date();
            }
            this.setLastModfiyTime(defaultTime);
        }
        Date currentLastUpdateTime = productMapper.getProductLastUpdateTime();
        // 获取这段时间中的被修改的商品ID
        List<Integer> pidList;
        if (this.getLastModfiyTime().getTime() < currentLastUpdateTime.getTime()) {
            pidList = productMapper.getProductIdByUpdateTime(this.getLastModfiyTime(), currentLastUpdateTime);
            this.setLastModfiyTime(currentLastUpdateTime);
        } else {
            pidList = new ArrayList<>();
        }

        // 将查询到的商品同步到ES中
        for (Integer pid : pidList) {

            Product product = productMapper.findProductById(pid);
            productEsMapper.save(new Product(product.getPrice(), product.getName(), product.getDesc(), product.getPid()+"",
                    product.getStatus(), product.getRecommend(), product.getProductImgList(), product.getPushTime(),
                    product.getLastUpdateTime(), product.getSellNumber(), product.getCreate(), product.getProductType()));
        }
    }

    /**
     * @description  每隔30秒定时执行
     * 去扫描没有及时支付的订单，并在30分钟后定时关闭改订单
     */
    //TODO 2019/4/14 12:06 PM  这里每次只拿一个数据，效率是有很大的提升空间的 。抽时间优化
    @Async
    @Scheduled(fixedDelay = 1000*30)  //间隔30秒
    public void checkOrder () {
        logger.info("job---check---order");
        Integer currentTime = Integer.valueOf(Common.timeStamp());
        ListOperations listOperations = redisTemplate.opsForList();
        Map<String, Integer> tempMap = (Map<String, Integer>) listOperations.rightPop(Common.getOrderQueueKey());
        if (null != tempMap) {
            // 拿到订单后，需要先判断是否超时。如果没超时，继续放入
            if((currentTime- tempMap.get("time")) <= 60*30) { // 超时时间， 单位是秒， 目前设置为三十分钟
                listOperations.rightPush(Common.getOrderQueueKey(),tempMap);
            }else{
                Integer tempStatus = orderMapper.closeOrder( tempMap.get("oid"), tempMap.get("uid"));
                if (tempStatus.equals(1)) {
                    logger.info("订单超时未支付" + tempMap);
                } else {
                    logger.info("修改失败");
                    listOperations.rightPush(Common.getOrderQueueKey(),tempMap);
                }
            }
        }

    }

    public Date getLastModfiyTime() {
        return lastModfiyTime;
    }

    public void setLastModfiyTime(Date lastModfiyTime) {
        this.lastModfiyTime = lastModfiyTime;
    }
}