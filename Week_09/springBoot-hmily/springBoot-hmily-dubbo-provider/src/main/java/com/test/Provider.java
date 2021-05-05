package com.test;

import java.io.IOException;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: John
 * @date: 2021/5/5 16:29
 * @describe:
 * @modify:
 */
public class Provider {
  public static void main(String[] args) throws Exception {
    doDubboRpcService();
  }

  public static void doDubboRpcService() throws IOException {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"/provider.xml"});
    context.start();
    System.in.read(); // 按任意键退出
  }
}
