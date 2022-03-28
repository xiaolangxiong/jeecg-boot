package org.jeecg.modules.service;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.api.vo.Result;

public interface LoginByMailService {
    public Result<JSONObject> applyLoginByMail(String baseUrl, String mail);

    public Result<JSONObject> confirmLoginByMail(String checkCode);
}
