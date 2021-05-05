package com.john.rpc.core.meta;


import com.alibaba.fastjson.JSON;

public interface MsgBase {

    Object[] objMsg();

    default String toStringMsg() {
        return JSON.toJSONString(objMsg());
    }

}
