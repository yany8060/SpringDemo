package com.yany.dao.single;


import com.yany.module.UserModel;

import java.util.List;

/**
 * Created by yanyong on 2017/1/26.
 */
public interface UseDao {
    int selectCount();

    int selectTokenCount();

    List<UserModel> selectUsers();
}
