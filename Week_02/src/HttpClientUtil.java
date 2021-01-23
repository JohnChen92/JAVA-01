package com.geek.week02;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

  private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
  private static final int TIME_OUT = 2000;


  public static void get(String url, Map<String, String> header, Map<String, Object> params) {
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    CloseableHttpResponse response = null;
    try {
      // 创建Get
      url = joinParam(url, params);
      HttpGet httpGet = new HttpGet(url);
      RequestConfig requestConfig = RequestConfig.custom()
          .setSocketTimeout(TIME_OUT) //服务器响应超时时间
          .setConnectTimeout(TIME_OUT) //连接服务器超时时间
          .build();
      httpGet.setConfig(requestConfig);
      getHeader(httpGet, header);
      // 由客户端执行(发送)Get请求
      response = httpClient.execute(httpGet);
      // 从响应模型中获取响应实体
      HttpEntity responseEntity = response.getEntity();
      logger.info("响应状态为:" + response.getStatusLine());
      if (responseEntity != null) {
        logger.info("响应内容长度为:" + responseEntity.getContentLength());
        logger.info("响应内容为:" + EntityUtils.toString(responseEntity));
      }
    } catch (Exception e) {
      logger.error("HttpClient get error! ", e);
    } finally {
      try {
        // 释放资源
        if (httpClient != null) {
          httpClient.close();
        }
        if (response != null) {
          response.close();
        }
      } catch (IOException e) {
        logger.error("HttpClient get IOException! ", e);
      }
    }
  }


  public static void post(String url, Map<String, String> header, Map<String, String> params) {
    List<NameValuePair> pairs = generatePairs(params);

    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    CloseableHttpResponse response = null;
    try {
      HttpPost httpPost = new HttpPost(url);
      RequestConfig requestConfig = RequestConfig.custom()
          .setSocketTimeout(TIME_OUT) //服务器响应超时时间
          .setConnectTimeout(TIME_OUT) //连接服务器超时时间
          .build();
      httpPost.setConfig(requestConfig);

      UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, "utf-8");
      httpPost.setEntity(entity);
      getHeader(httpPost, header);
      // 由客户端执行(发送)请求
      response = httpClient.execute(httpPost);
      logger.info("响应状态为:" + response.getStatusLine());
      // 从响应模型中获取响应实体
      HttpEntity responseEntity = response.getEntity();
      if (responseEntity != null) {
        logger.info("响应内容长度为:" + responseEntity.getContentLength());
        logger.info("响应内容为:" + EntityUtils.toString(responseEntity));
      }
    } catch (Exception e) {
      logger.error("HttpClient post error! ", e);
    } finally {
      try {
        // 释放资源
          if (httpClient != null) {
              httpClient.close();
          }
          if (response != null) {
              response.close();
          }
      } catch (IOException e) {
        logger.error("HttpClient post IOException! ", e);
      }
    }
  }


  /**
   * 设置header
   *
   * @param httpRequest
   * @param headers
   * @return
   */
  private static HttpRequest getHeader(HttpRequest httpRequest, Map<String, String> headers) {
      if (httpRequest == null || headers == null || headers.isEmpty()) {
          return httpRequest;
      }

    for (Map.Entry<String, String> entry : headers.entrySet()) {
      httpRequest.setHeader(entry.getKey(), entry.getValue());
    }
    return httpRequest;
  }

  /**
   * 组合post参数
   *
   * @param params
   * @return
   */
  private static List<NameValuePair> generatePairs(Map<String, String> params) {
      if (params == null || params.size() == 0) {
          return Collections.emptyList();
      }

    List<NameValuePair> pairs = new ArrayList<>();
    for (Map.Entry<String, String> entry : params.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();
        if (key == null) {
            continue;
        }

      pairs.add(new BasicNameValuePair(key, value));
    }
    return pairs;
  }

  /**
   * 组合get参数
   *
   * @param url
   * @param params
   * @return
   */
  private static String joinParam(String url, Map<String, Object> params) {
      if (params == null || params.size() == 0) {
          return url;
      }

    StringBuilder urlBuilder = new StringBuilder(url);
    urlBuilder.append("?");

    int counter = 0;
    for (Map.Entry<String, Object> entry : params.entrySet()) {
      String key = entry.getKey();
      Object value = entry.getValue();
        if (key == null) {
            continue;
        }

        if (counter == 0) {
            urlBuilder.append(key).append("=").append(value);
        } else {
            urlBuilder.append("&").append(key).append("=").append(value);
        }

      counter++;
    }
    return urlBuilder.toString();
  }

}
