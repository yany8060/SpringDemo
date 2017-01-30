package com.yany.dao;

import com.yany.Application;
import com.yany.dao.multi.ads.AdsDao;
import com.yany.dao.multi.annotation.AnnotationAdsDao;
import com.yany.dao.multi.annotation.AnnotationRdsDao;
import com.yany.dao.multi.rds.RdsDao;
import com.yany.dao.single.UseDao;
import com.yany.service.IAdsAopService;
import com.yany.service.IRdsAopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by yanyong on 2017/1/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ARdsDaoTest {
    @Resource
    AdsDao adsDao;

    @Resource
    RdsDao rdsDao;

    @Resource
    UseDao useDao;

    @Resource
    IRdsAopService rdsAopService;

    @Resource
    IAdsAopService adsAopService;

    @Resource
    AnnotationRdsDao annotationRdsDao;

    @Resource
    AnnotationAdsDao annotationAdsDao;

    @Test
    public void testDao() {
        int adsCount = adsDao.selectCount();
        int rdsCount = rdsDao.selectCount();

        int tokenCount = useDao.selectTokenCount();

        int serviceAdsCount = adsAopService.getUserCount();
        int serviceRdsCount = rdsAopService.getUserCount();

        int annotationRdsCount = annotationRdsDao.selectCount();
        int annotationAdsCount = annotationAdsDao.selectCount();

        System.out.println("adsCount: " + adsCount);
        System.out.println("rdsCount: " + rdsCount);
        System.out.println("tokenCount: " + tokenCount);

        System.out.println("serviceAdsCount: " + serviceAdsCount);
        System.out.println("serviceRdsCount: " + serviceRdsCount);

        System.out.println("annotationRdsCount: " + annotationRdsCount);
        System.out.println("annotationAdsCount: " + annotationAdsCount);

    }

}
