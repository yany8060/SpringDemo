package com.yany.controller;

import com.yany.service.ITestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by yanyong on 2017/1/24.
 */
@RestController
public class TestContoller {
    @Resource
    ITestService testService;

    @RequestMapping(value = "/hello", method = {RequestMethod.GET})
    public String test() {

        int count = testService.getUserCount();
        return "Hello world: " + count;
    }

}
