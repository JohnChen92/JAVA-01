package com.test;

import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: John
 * @date: 2021/5/5 16:27
 * @describe:
 * @modify:
 */
@Service("providerServer")
public class ProviderServerImpl implements ProviderServer{

  @Override
  @HmilyTCC(confirmMethod = "sayConfrim", cancelMethod = "sayCancel")
  public String sayHello(String name) {
      System.out.println(" sayHello ");
      int i = 1/0;
      return "Say Hello :" + name;
  }


  @Override
  @Transactional
  public void sayConfrim(String hello) {
    System.out.println(" confirm hello world");
  }

  @Override
  @Transactional
  public void sayCancel(String hello) {
    System.out.println(" cancel hello world");
  }
}
