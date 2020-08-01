package com.wise.rest.demo.obj.dvo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 用户更新参数校验类
 *
 * @author lingyuwang
 * @date 2020-08-01 10:09
 * @since 1.1.3.0
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ScriptAssert(
        lang = "javascript",
        script = "_this.termStartTime == null || _this.termEndTime == null || _this.termStartTime.getTime() < _this.termEndTime.getTime()",
        message = "有效开始时间必须小于结束时间")
public class UserUpdateDVO {

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    @Range
    Long userId;

    /**
     * 密码
     */
    @Size(max = 20, message = "密码不能超过20个字符")
    String password;

    /**
     * 有效开始时间
     */
    @NotNull(message = "有效开始时间不能为空")
    Date termStartTime;

    /**
     * 有效结束时间
     */
    @NotNull(message = "有效结束时间不能为空")
    Date termEndTime;

}
