package com.yany.dao;

import com.yany.model.UserModel;

import java.util.List;

/**
 * Created by yanyong on 2017/2/9.
 */
public interface IUserDao {
    public List<UserModel> queryUsers();

}
