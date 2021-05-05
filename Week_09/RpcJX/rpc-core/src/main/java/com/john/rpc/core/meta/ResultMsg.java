package com.john.rpc.core.meta;

import lombok.Data;

@Data
public class ResultMsg implements MsgBase{

  private Object o;
  private String resultType;

  @Override
  public Object[] objMsg() {
    return new Object[]{resultType,o};
  }
}
