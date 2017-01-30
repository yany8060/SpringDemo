package com.yany.dao.multi.annotation;

import com.yany.configure.mybatis.multi.annotation.AdsRepository;

/**
 * Created by yanyong on 2017/1/30.
 */
@AdsRepository
public interface AnnotationAdsDao {
    int selectCount();
}
