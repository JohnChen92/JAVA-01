package com.geek.week02;

import java.util.HashMap;

public class TestHttpClient {

  public static void main(String[] args) {
    HttpClientUtil.get("http://localhost:8801", new HashMap<>(), new HashMap<>());
  }
}
