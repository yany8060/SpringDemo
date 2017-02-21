package com.yany.dao;

import com.alibaba.fastjson.JSON;
import com.yany.common.kafka.bean.IKafkaProducerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by yanyong on 2017/2/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/xmlConfig/applicationContext.xml", "/xmlConfig/spring-kafka-producer.xml"})
public class UserDaoTest {
    @Resource
    IUserDao userDao;

    @Resource
    IKafkaProducerService kafkaProducerService;

    @Test
    public void testQueryUsers() {
        System.out.println(JSON.toJSONString(userDao.queryUsers()));

        kafkaProducerService.sendMessage("testYanY", "test data adat");

    }


}
