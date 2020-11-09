package com.wise.rest.demo.config.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberModule extends SimpleModule {

  public static final NumberModule INSTANCE = new NumberModule();

  public NumberModule() {
    super(NumberModule.class.getName());
    this.addSerializer(Long.class, BigNumberSerializer.instance);
    this.addSerializer(Long.TYPE, BigNumberSerializer.instance);
    this.addSerializer(BigInteger.class, BigNumberSerializer.instance);
    this.addSerializer(BigDecimal.class, ToStringSerializer.instance);
  }

}