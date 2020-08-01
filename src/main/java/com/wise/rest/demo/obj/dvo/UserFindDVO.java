package com.wise.rest.demo.obj.dvo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 用户查询参数校验类
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
public class UserFindDVO {

    /**
     * 用户名
     */
    @Size(max = 20, message = "用户名不能超过20个字符")
    String userName;

    /**
     * 有效开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date termStartTime;

    /**
     * 有效结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date termEndTime;

}
