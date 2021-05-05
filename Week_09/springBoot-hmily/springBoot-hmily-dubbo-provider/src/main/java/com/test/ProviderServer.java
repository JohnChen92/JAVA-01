package com.test;

import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.annotation.HmilyTCC;

/**
 * @Author: John
 * @date: 2021/5/5 16:26
 * @describe:
 * @modify:
 */
public interface ProviderServer {

    @HmilyTCC(confirmMethod = "sayConfrim", cancelMethod = "sayCancel")
    String sayHello(String name);

    void sayConfrim(String hello);

    void sayCancel(String hello);
}
