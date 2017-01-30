package com.yany.service.impl;

import com.yany.dao.multi.aop.AdsAopDao;
import com.yany.service.IAdsAopService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yanyong on 2017/1/30.
 */
@Service
public class AdsAopServiceImpl implements IAdsAopService {

    @Resource
    AdsAopDao adsAopDao;

    @Override
    public int getUserCount() {
        return adsAopDao.selectCount();
    }
}
