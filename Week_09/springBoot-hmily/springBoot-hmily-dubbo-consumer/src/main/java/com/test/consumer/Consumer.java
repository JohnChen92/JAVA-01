package com.test.consumer;

import com.test.ProviderServer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: John
 * @date: 2021/5/5 16:34
 * @describe:
 * @modify:
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"/consumer.xml"});
      context.start();
      ProviderServer service = (ProviderServer)context.getBean("providerServer"); // 获取远程服务代理
      String hello = service.sayHello("world"); // 执行远程方法
      System.out.println( hello ); // 显示调用结果
    }

}
