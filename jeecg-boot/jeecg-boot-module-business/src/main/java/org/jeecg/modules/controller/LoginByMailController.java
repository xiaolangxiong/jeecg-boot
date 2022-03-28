package org.jeecg.modules.controller;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.CommonUtils;
import org.jeecg.modules.service.LoginByMailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "通过邮箱登陆接口")
@RequestMapping("/sys")
public class LoginByMailController {
    @Autowired
    private LoginByMailService loginByMailService;

    @ApiOperation("申请通过邮箱登陆--设置redis")
    @RequestMapping(value = "/applyLoginByMail", method = RequestMethod.POST)
    public Result<JSONObject> applyLoginByMail(@RequestParam String mail, HttpServletRequest httpServletRequest) {
        String baseUrl = CommonUtils.getBaseUrl(httpServletRequest);
        return loginByMailService.applyLoginByMail(baseUrl, mail);
    }


    @ApiOperation("邮箱登陆--确认登陆")
    @RequestMapping(value = "/confirmLoginByMail", method = RequestMethod.POST)
    public Result<JSONObject> confirmLoginByMail(@RequestParam String checkCode) {
        return loginByMailService.confirmLoginByMail(checkCode);
    }

}
