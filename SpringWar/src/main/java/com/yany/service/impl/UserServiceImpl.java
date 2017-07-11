package com.yany.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yany.dao.ITestDao;
import com.yany.dao.IUserDao;
import com.yany.model.PlatformModel;
import com.yany.model.UserModel;
//import com.yany.repository.PlatformRepository;
import com.yany.service.IUserService;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yanyong on 2017/2/9.
 */
@Service
public class UserServiceImpl implements IUserService {

//    @Resource
//    IUserDao userDao;

//    @Resource
//    PlatformRepository platformRepository;

    @Resource(name = "testDao")
    ITestDao testDao;

//    @Resource(name = "adsTxManager")
//    DataSourceTransactionManager adsTxManager;

//    @Resource(name = "rdsTxManager")
//    DataSourceTransactionManager rdsTxManager;

    @Override
    public List<UserModel> queryUsers() {

        Page<UserModel> page = PageHelper.startPage(1, 2);
//        List<UserModel> userModels = userDao.queryUsers();
        List<UserModel> userModels = null;

//        List<PlatformModel> platformModels = platformRepository.getAllPlatForm();
//        System.out.println(JSON.toJSONString(platformModels));

        return userModels;
    }

    @Override
    public void queryUserId() {
        String rdsID = testDao.rdsQueryUserId();
        System.out.println(rdsID);

        String adsID = testDao.adsQueryUserId();
        System.out.println(adsID);
    }


    @Transactional
    @Override
    public void insertTest() {
//        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
//        TransactionStatus adsStatus = adsTxManager.getTransaction(def);
//
//        DefaultTransactionDefinition def2 = new DefaultTransactionDefinition();
//        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
//        TransactionStatus rdsStatus = rdsTxManager.getTransaction(def2);

//        try {
//            testDao.adsInsertTest();
//            testDao.rdsInsertTest();
//
////            UserModel userModel = null;
////            userModel.getAddTime();
//
//        }catch (Exception e){
//            adsTxManager.rollback(adsStatus);
//            rdsTxManager.rollback(rdsStatus);
//        }finally {
//            adsTxManager.commit(adsStatus);
//            rdsTxManager.commit(rdsStatus);
//        }


        testDao.adsInsertTest();
        testDao.rdsInsertTest();

        System.out.println("aaaaa");

        int i = 1 / 0;

    }

    @Override
    public void multiInsertTest() {
        testDao.multiInsertTest();
    }
}
