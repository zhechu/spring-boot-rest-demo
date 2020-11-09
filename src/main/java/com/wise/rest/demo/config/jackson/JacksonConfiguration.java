package com.wise.rest.demo.config.jackson;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

@Configuration
@ConditionalOnClass({ObjectMapper.class})
@AutoConfigureBefore({JacksonAutoConfiguration.class})
@EnableConfigurationProperties({WiseJacksonProperties.class})
public class JacksonConfiguration {

  @Primary
  @Bean
  public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
    builder.simpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ObjectMapper objectMapper = builder.createXmlMapper(false).build();
    objectMapper.setLocale(Locale.CHINA);
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    objectMapper.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA));
    objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    objectMapper.configure(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
    objectMapper.getDeserializationConfig().withoutFeatures(new DeserializationFeature[]{DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES});
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.findAndRegisterModules();
    return objectMapper;
  }

}