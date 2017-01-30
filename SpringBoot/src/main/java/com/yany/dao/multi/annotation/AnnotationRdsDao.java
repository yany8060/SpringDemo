package com.yany.dao.multi.annotation;

import com.yany.configure.mybatis.multi.annotation.RdsRepository;

/**
 * Created by yanyong on 2017/1/30.
 */
@RdsRepository
public interface AnnotationRdsDao {
    int selectCount();
}
