package com.yany.service.impl;

import com.yany.dao.single.UseDao;
import com.yany.service.ITestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yanyong on 2017/1/29.
 */
@Service
public class TestService implements ITestService {

    @Resource
    UseDao useDao;

    @Override
    public int getUserCount() {
        return useDao.selectCount();
    }
}
