package com.wise.rest.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wise.rest.demo.config.jackson.MappingApiJackson2HttpMessageConverter;
import com.wise.rest.demo.config.jackson.WiseJacksonProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.converter.*;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
@Order(-2147483648)
@AllArgsConstructor
public class MessageConfiguration implements WebMvcConfigurer {

  private final ObjectMapper objectMapper;
  private final WiseJacksonProperties properties;

  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.removeIf((x) -> {
      return x instanceof StringHttpMessageConverter || x instanceof AbstractJackson2HttpMessageConverter;
    });
    converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
    converters.add(new ByteArrayHttpMessageConverter());
    converters.add(new ResourceHttpMessageConverter());
    converters.add(new ResourceRegionHttpMessageConverter());
    converters.add(new MappingApiJackson2HttpMessageConverter(this.objectMapper, this.properties));
  }

  public void addFormatters(FormatterRegistry registry) {
    registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
    registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
  }

}