package com.wise.rest.demo.obj.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * API 统一返回对象
 *
 * @author lingyuwang
 * @date 2020-08-01 10:48
 * @since 1.1.3.0
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -8433771148527580971L;

    /**
     * 状态码
     */
    Integer code;

    /**
     * 提示信息
     */
    String message;

    /**
     * 返回数据
     */
    T data;

    public static <T> Response<T> success(T data) {
        return new Response<T>(200, "请求成功", data);
    }

    public static Response error(Integer code, String message) {
        return new Response(code, message, null);
    }

}
