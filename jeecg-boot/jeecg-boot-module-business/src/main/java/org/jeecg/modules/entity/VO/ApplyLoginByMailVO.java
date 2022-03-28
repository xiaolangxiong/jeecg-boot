package org.jeecg.modules.entity.VO;

import lombok.Data;

@Data
public class ApplyLoginByMailVO {
    private String loginUrl;

    private String checkCode;
}
