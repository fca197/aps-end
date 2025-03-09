package com.olivia.peanut.task.engine.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.google.common.collect.Lists;
import com.olivia.peanut.task.engine.entity.ExecTaskReq;
import com.olivia.peanut.task.engine.entity.TaskInfoDef;
import com.olivia.peanut.task.engine.entity.vo.HttpReqMethod;
import com.olivia.peanut.task.engine.exec.TaskRunnerExec;
import com.olivia.sdk.exception.RunException;
import com.olivia.sdk.model.KVEntity;
import com.olivia.sdk.utils.Str;
import com.olivia.sdk.utils.ValueUtils;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component("http" + "TaskRunnerExec")
public class HttpTaskRunnerExecImpl implements TaskRunnerExec {

  @Override
  public KVEntity getTaskRunnerExecName() {
    return KVEntity.of(Str.DEFAULT_ZN, Str.DEFAULT).setChildrenList(Lists.newArrayList(KVEntity.of("HTTP请求", "HTTP")));
  }

  @Override
  @SneakyThrows
  public Map<String, Object> exec(ExecTaskReq req) {

    @Cleanup HttpClient client = HttpClient.newHttpClient();

    TaskInfoDef taskInfoDef = req.getCurrentTaskInfoDef();
    if (HttpReqMethod.POST.equals(taskInfoDef.getReqMethod())) {
      String jsonBody = JSON.toJSONString(req.getLastOutMap());
      // 构建 POST 请求
      HttpRequest request = HttpRequest.newBuilder().uri(URI.create(taskInfoDef.getReqUrl())).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8)).build();
      // 发送请求并获取响应
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      // 将响应体转换为 Map
      return JSONObject.parseObject(response.body(), new TypeReference<Map<String, Object>>() {
      }.getType());

    } else if (HttpReqMethod.GET.equals(taskInfoDef.getReqMethod())) {
      String reqParam = req.getLastOutMap().entrySet().stream().map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + ("=") + (URLEncoder.encode(ValueUtils.value2Str(entry.getValue()), StandardCharsets.UTF_8))).collect(Collectors.joining("&"));
      String url = taskInfoDef.getReqUrl() + "?" + reqParam;
      if (log.isDebugEnabled()) {
        log.debug("instanceId : {} lastTaskInstanceId : {} taskInstanceId: {} url :{}", req.getInstanceId(), req.getLastTaskInstanceId(), req.getTaskInstanceId(), url);
      }
      // 构建 GET 请求
      HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
      // 发送请求并获取响应
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      // 将响应体转换为 Map
      return JSONObject.parseObject(response.body(), new TypeReference<Map<String, Object>>() {
      }.getType());


    }
    throw new RunException("请求方法没有实现 " + taskInfoDef.getReqMethod());
  }
}
