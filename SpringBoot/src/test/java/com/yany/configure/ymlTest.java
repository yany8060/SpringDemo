package com.yany.configure;

import com.yany.Application;


import com.yany.dao.single.UseDao;
import com.yany.module.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


/**
 * Created by yanyong on 2017/1/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ymlTest {


//    @Resource
//    AdsDataSourceConfig adsDataSourceConfig;


    @Resource
    UseDao useDao;

    @Test
    public void getConfig() {
        System.out.println(useDao.selectCount());
    }


}
