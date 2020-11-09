package com.wise.rest.demo.config.jackson;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("wise.jackson")
@Data
public class WiseJacksonProperties {

  private Boolean nullToEmpty = Boolean.TRUE;

  private Boolean bigNumToString = Boolean.TRUE;

  private Boolean supportTextPlain = Boolean.FALSE;

}