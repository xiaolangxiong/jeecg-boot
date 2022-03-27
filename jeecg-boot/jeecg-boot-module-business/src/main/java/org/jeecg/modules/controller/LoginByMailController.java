package org.jeecg.modules.controller;

import org.jeecg.modules.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags="新登录接口")
@RequestMapping("/sys")
public class LoginByMailController {
    @Autowired
    MailService mailService;

    @ApiOperation("通过邮件登录接口")
    @RequestMapping(value = "/loginByMail", method = RequestMethod.POST)
    public void loginByMail(){
        mailService.sendSimpleMail("3329642252@qq.com","13719343938@163.com","xlanxiong@163.com","测试邮件主题","测试邮件内容");
    }



}
