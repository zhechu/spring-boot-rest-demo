package com.wise.rest.demo.config.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class WiseBeanSerializerModifier extends BeanSerializerModifier {

  public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
    beanProperties.forEach((writer) -> {
      if (!writer.hasNullSerializer()) {
        JavaType type = writer.getType();
        Class<?> clazz = type.getRawClass();
        if (type.isTypeOrSubTypeOf(Number.class)) {
          writer.assignNullSerializer(WiseBeanSerializerModifier.NullJsonSerializers.STRING_JSON_SERIALIZER);
        } else if (type.isTypeOrSubTypeOf(Boolean.class)) {
          writer.assignNullSerializer(WiseBeanSerializerModifier.NullJsonSerializers.BOOLEAN_JSON_SERIALIZER);
        } else if (type.isTypeOrSubTypeOf(Character.class)) {
          writer.assignNullSerializer(WiseBeanSerializerModifier.NullJsonSerializers.STRING_JSON_SERIALIZER);
        } else if (type.isTypeOrSubTypeOf(String.class)) {
          writer.assignNullSerializer(WiseBeanSerializerModifier.NullJsonSerializers.STRING_JSON_SERIALIZER);
        } else if (!type.isArrayType() && !clazz.isArray() && !type.isTypeOrSubTypeOf(Collection.class)) {
          if (type.isTypeOrSubTypeOf(OffsetDateTime.class)) {
            writer.assignNullSerializer(WiseBeanSerializerModifier.NullJsonSerializers.STRING_JSON_SERIALIZER);
          } else if (!type.isTypeOrSubTypeOf(Date.class) && !type.isTypeOrSubTypeOf(TemporalAccessor.class)) {
            writer.assignNullSerializer(WiseBeanSerializerModifier.NullJsonSerializers.OBJECT_JSON_SERIALIZER);
          } else {
            writer.assignNullSerializer(WiseBeanSerializerModifier.NullJsonSerializers.STRING_JSON_SERIALIZER);
          }
        } else {
          writer.assignNullSerializer(WiseBeanSerializerModifier.NullJsonSerializers.ARRAY_JSON_SERIALIZER);
        }

      }
    });
    return super.changeProperties(config, beanDesc, beanProperties);
  }

  public interface NullJsonSerializers {
    JsonSerializer<Object> STRING_JSON_SERIALIZER = new JsonSerializer<Object>() {
      public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString("");
      }
    };
    JsonSerializer<Object> NUMBER_JSON_SERIALIZER = new JsonSerializer<Object>() {
      public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(-1);
      }
    };
    JsonSerializer<Object> BOOLEAN_JSON_SERIALIZER = new JsonSerializer<Object>() {
      public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeObject(Boolean.FALSE);
      }
    };
    JsonSerializer<Object> ARRAY_JSON_SERIALIZER = new JsonSerializer<Object>() {
      public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        gen.writeEndArray();
      }
    };
    JsonSerializer<Object> OBJECT_JSON_SERIALIZER = new JsonSerializer<Object>() {
      public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeEndObject();
      }
    };
  }

}