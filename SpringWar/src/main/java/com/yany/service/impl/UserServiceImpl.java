package com.yany.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yany.dao.IUserDao;
import com.yany.model.PlatformModel;
import com.yany.model.UserModel;
//import com.yany.repository.PlatformRepository;
import com.yany.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yanyong on 2017/2/9.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    IUserDao userDao;

//    @Resource
//    PlatformRepository platformRepository;

    @Override
    public List<UserModel> queryUsers() {

        Page<UserModel> page = PageHelper.startPage(1, 2);
        List<UserModel> userModels = userDao.queryUsers();

//        List<PlatformModel> platformModels = platformRepository.getAllPlatForm();
//        System.out.println(JSON.toJSONString(platformModels));

        return userModels;
    }
}
