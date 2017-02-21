package com.yany.controller;

import com.alibaba.fastjson.JSONObject;
import com.yany.common.filter.annotation.YanYUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yanyong on 2017/2/20.
 */
@Controller
@RequestMapping("fail")
public class FailController {

    @YanYUrl(value = "WHITE")
    @RequestMapping(value = "permissiodnenied", method = RequestMethod.GET)
    @ResponseBody
    public String permissioDnenied() {
        JSONObject json = new JSONObject();
        json.put("msg", "没有权限");
        json.put("code", "1001");
        return json.toString();
    }

}
