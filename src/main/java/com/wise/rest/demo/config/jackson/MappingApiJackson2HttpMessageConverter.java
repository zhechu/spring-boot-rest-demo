package com.wise.rest.demo.config.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MappingApiJackson2HttpMessageConverter extends AbstractReadWriteJackson2HttpMessageConverter {

  @Nullable
  private String jsonPrefix;

  public MappingApiJackson2HttpMessageConverter(ObjectMapper objectMapper, WiseJacksonProperties properties) {
    super(objectMapper, initWriteObjectMapper(objectMapper, properties), initMediaType(properties));
  }

  private static List<MediaType> initMediaType(WiseJacksonProperties properties) {
    List<MediaType> supportedMediaTypes = new ArrayList();
    supportedMediaTypes.add(MediaType.APPLICATION_JSON);
    supportedMediaTypes.add(new MediaType("application", "*+json"));
    if (Boolean.TRUE.equals(properties.getSupportTextPlain())) {
      supportedMediaTypes.add(MediaType.TEXT_PLAIN);
    }

    return supportedMediaTypes;
  }

  private static ObjectMapper initWriteObjectMapper(ObjectMapper readObjectMapper, WiseJacksonProperties properties) {
    ObjectMapper writeObjectMapper = readObjectMapper.copy();
    if (Boolean.TRUE.equals(properties.getBigNumToString())) {
      writeObjectMapper.registerModules(new Module[]{NumberModule.INSTANCE});
    }

    if (Boolean.TRUE.equals(properties.getNullToEmpty())) {
      writeObjectMapper.setSerializerFactory(writeObjectMapper.getSerializerFactory().withSerializerModifier(new WiseBeanSerializerModifier()));
      writeObjectMapper.getSerializerProvider().setNullValueSerializer(WiseBeanSerializerModifier.NullJsonSerializers.STRING_JSON_SERIALIZER);
    }

    return writeObjectMapper;
  }

  protected void writeInternal(@NonNull Object object, @Nullable Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
    if (object instanceof String) {
      Charset defaultCharset = this.getDefaultCharset();
      Charset charset = defaultCharset == null ? Charsets.UTF_8 : defaultCharset;
      StreamUtils.copy((String)object, charset, outputMessage.getBody());
    } else {
      super.writeInternal(object, type, outputMessage);
    }
  }

  public void setJsonPrefix(String jsonPrefix) {
    this.jsonPrefix = jsonPrefix;
  }

  public void setPrefixJson(boolean prefixJson) {
    this.jsonPrefix = prefixJson ? ")]}', " : null;
  }

  protected void writePrefix(JsonGenerator generator, Object object) throws IOException {
    if (this.jsonPrefix != null) {
      generator.writeRaw(this.jsonPrefix);
    }
  }

}