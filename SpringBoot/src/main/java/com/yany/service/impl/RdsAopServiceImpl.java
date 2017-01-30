package com.yany.service.impl;

import com.yany.dao.multi.aop.RdsAopDao;
import com.yany.service.IRdsAopService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yanyong on 2017/1/30.
 */
@Service
public class RdsAopServiceImpl implements IRdsAopService {

    @Resource
    RdsAopDao rdsAopDao;

    @Override
    public int getUserCount() {
        return rdsAopDao.selectCount();
    }
}
