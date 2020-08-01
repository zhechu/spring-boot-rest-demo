package com.wise.rest.demo.enums;

/**
 * 是否枚举
 *
 * @author lingyuwang
 * @date 2020-08-01 14:27
 * @since 1.1.3.0
 */
public enum YesNoEnum {

    YES(1,"是"),
    NO(0,"否");

    private Integer key;

    private String value;

    YesNoEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
