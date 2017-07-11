package com.yany.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by yanyong on 2017/7/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/xmlConfig/applicationContext.xml"})
public class UserServiceTest {

    @Resource
    IUserService userService;

    @Test
    public void queryUserId() {
        userService.queryUserId();
    }

    @Test
    public void insertTest(){
        userService.insertTest();
    }

    @Test
    public void multiInsertTest(){
        userService.multiInsertTest();
    }


}
