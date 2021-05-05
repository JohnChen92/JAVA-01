package com.john.rpc.core.meta;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * rpc//com.test.abc:methodName?123;345
 */
@Data
public class CallMsg implements MsgBase{

  private String type;

  private String clazzName;

  private String methodName;

  private Object[] params;

  private Class[] paramTyeps;

  @Override
  public Object[] objMsg() {
    return new Object[]{type,clazzName,methodName,params,paramTyeps};
  }
}
