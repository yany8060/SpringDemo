package com.yany.service;

import com.yany.model.UserModel;

import java.util.List;

/**
 * Created by yanyong on 2017/2/9.
 */
public interface IUserService {

    List<UserModel> queryUsers();

    void queryUserId();

    void insertTest();

    void multiInsertTest();

}
