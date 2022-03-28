package org.jeecg.modules.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.CommonUtils;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.UUIDGenerator;
import org.jeecg.modules.entity.VO.ApplyLoginByMailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;

@Slf4j
@Service
public class LoginByMailServiceImpl implements LoginByMailService {

    @Autowired
    private MailService mailService;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Result<JSONObject> applyLoginByMail(String baseUrl, String mail) {
        String checkCode = UUIDGenerator.generate();
        try {
            boolean setRedisResult = redisUtil.set(checkCode, "", 120);
            if (!setRedisResult) {
                return Result.error("发送登陆邮件失败，请稍后再试");
            }
            Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_0);
            ClassLoader loader = LoginByMailService.class.getClassLoader();
            configuration.setClassLoaderForTemplateLoading(loader, "ftl");
            Template template = configuration.getTemplate("maintemplate.ftl");
            StringWriter mailWriter = new StringWriter();
            String url = baseUrl + "/sys/confirmLoginByMail";
            ApplyLoginByMailVO applyLoginByMailVO = new ApplyLoginByMailVO();
            applyLoginByMailVO.setLoginUrl(url);
            applyLoginByMailVO.setCheckCode(checkCode);
            template.process(applyLoginByMailVO, mailWriter);
            mailService.sendHtmlMail(fromMail, mail, "keepitui登陆", mailWriter.toString());
        } catch (Exception e) {
            log.error("发送登录邮件失败,to:{},message:{}", mail, e.getMessage());
        }
        return Result.OK();
    }

    @Override
    public Result<JSONObject> confirmLoginByMail(String checkCode) {
        if (redisUtil.get(checkCode) == null){
            return Result.error("该登陆邮件已过期，请重新获取登陆邮件");
        }
        return Result.OK();
    }
}
