package com.wise.rest.demo.config.jackson;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.lang.Nullable;
import org.springframework.util.TypeUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractReadWriteJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

  private static final Charset DEFAULT_CHARSET;
  private ObjectMapper writeObjectMapper;

  @Nullable
  private PrettyPrinter ssePrettyPrinter;

  public AbstractReadWriteJackson2HttpMessageConverter(ObjectMapper readObjectMapper, ObjectMapper writeObjectMapper) {
    super(readObjectMapper);
    this.writeObjectMapper = writeObjectMapper;
    this.initSsePrettyPrinter();
  }

  public AbstractReadWriteJackson2HttpMessageConverter(
      ObjectMapper readObjectMapper,
      ObjectMapper writeObjectMapper,
      MediaType supportedMediaType
  ) {
    this(readObjectMapper, writeObjectMapper);
    this.setSupportedMediaTypes(Collections.singletonList(supportedMediaType));
    this.initSsePrettyPrinter();
  }

  public AbstractReadWriteJackson2HttpMessageConverter(
      ObjectMapper readObjectMapper,
      ObjectMapper writeObjectMapper,
      List<MediaType> supportedMediaTypes
  ) {
    this(readObjectMapper, writeObjectMapper);
    this.setSupportedMediaTypes(supportedMediaTypes);
  }

  private void initSsePrettyPrinter() {
    this.setDefaultCharset(DEFAULT_CHARSET);
    DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
    prettyPrinter.indentObjectsWith(new DefaultIndenter("  ", "\ndata:"));
    this.ssePrettyPrinter = prettyPrinter;
  }

  public boolean canWrite(Class<?> clazz, @Nullable MediaType mediaType) {
    if (!this.canWrite(mediaType)) {
      return false;
    } else {
      AtomicReference<Throwable> causeRef = new AtomicReference();
      if (this.objectMapper.canSerialize(clazz, causeRef)) {
        return true;
      } else {
        this.logWarningIfNecessary(clazz, (Throwable)causeRef.get());
        return false;
      }
    }
  }

  protected void writeInternal(
      Object object,
      @Nullable Type type,
      HttpOutputMessage outputMessage
  ) throws IOException, HttpMessageNotWritableException {
    MediaType contentType = outputMessage.getHeaders().getContentType();
    JsonEncoding encoding = this.getJsonEncoding(contentType);
    JsonGenerator generator = this.writeObjectMapper.getFactory().createGenerator(outputMessage.getBody(), encoding);

    try {
      this.writePrefix(generator, object);
      Object value = object;
      Class<?> serializationView = null;
      FilterProvider filters = null;
      JavaType javaType = null;
      if (object instanceof MappingJacksonValue) {
        MappingJacksonValue container = (MappingJacksonValue)object;
        value = container.getValue();
        serializationView = container.getSerializationView();
        filters = container.getFilters();
      }

      if (type != null && TypeUtils.isAssignable(type, value.getClass())) {
        javaType = this.getJavaType(type, (Class)null);
      }

      ObjectWriter objectWriter = serializationView != null ? this.writeObjectMapper.writerWithView(serializationView) : this.writeObjectMapper.writer();
      if (filters != null) {
        objectWriter = objectWriter.with(filters);
      }

      if (javaType != null && javaType.isContainerType()) {
        objectWriter = objectWriter.forType(javaType);
      }

      SerializationConfig config = objectWriter.getConfig();
      if (contentType != null && contentType.isCompatibleWith(MediaType.TEXT_EVENT_STREAM) && config.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
        objectWriter = objectWriter.with(this.ssePrettyPrinter);
      }

      objectWriter.writeValue(generator, value);
      this.writeSuffix(generator, object);
      generator.flush();
    } catch (InvalidDefinitionException var13) {
      throw new HttpMessageConversionException("Type definition error: " + var13.getType(), var13);
    } catch (JsonProcessingException var14) {
      throw new HttpMessageNotWritableException("Could not write JSON: " + var14.getOriginalMessage(), var14);
    }
  }

  static {
    DEFAULT_CHARSET = StandardCharsets.UTF_8;
  }

}