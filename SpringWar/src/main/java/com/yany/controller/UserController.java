package com.yany.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yany.common.kafka.bean.IKafkaProducerService;
import com.yany.common.redis.JedisClusterFactory;
import com.yany.model.UserModel;
import com.yany.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by yanyong on 2017/2/9.
 */
@Controller
@RequestMapping("war")
public class UserController {

    @Resource
    IUserService userService;

//    @Resource
//    JedisClusterFactory jedisClusterFactory;

//    @Resource
//    IKafkaProducerService kafkaProducerService;


    @ResponseBody
    @RequestMapping(value = "/userinfo", method = {RequestMethod.GET})
    public String userTest(HttpServletRequest request) {

        List<UserModel> userModelList = userService.queryUsers();

//        PageInfo<UserModel> info = new PageInfo<UserModel>(page);
//        System.out.println(JSON.toJSONString(info));

//        JedisCluster jedisCluster = jedisClusterFactory.getInstance();

//        kafkaProducerService.sendMessage("testYanY","ssssss");


        return JSON.toJSONString(userModelList);

    }

}
