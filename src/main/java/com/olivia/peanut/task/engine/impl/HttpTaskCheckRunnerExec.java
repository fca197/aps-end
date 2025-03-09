package com.olivia.peanut.task.engine.impl;

import cn.hutool.core.util.BooleanUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.google.common.collect.Lists;
import com.olivia.peanut.task.engine.entity.TaskCheckRunnerReq;
import com.olivia.peanut.task.engine.entity.TaskInfoDef;
import com.olivia.peanut.task.engine.entity.vo.HttpReqMethod;
import com.olivia.peanut.task.engine.exec.TaskCheckRunnerExec;
import com.olivia.peanut.task.model.TaskInstanceHistory;
import com.olivia.sdk.exception.RunException;
import com.olivia.sdk.model.KVEntity;
import com.olivia.sdk.utils.RunUtils;
import com.olivia.sdk.utils.Str;
import com.olivia.sdk.utils.ValueUtils;
import lombok.Cleanup;
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
@Component("httpTaskCheckRunnerExec")
public class HttpTaskCheckRunnerExec implements TaskCheckRunnerExec {
  @Override
  public KVEntity getTaskCheckRunnerName() {
    return KVEntity.of(Str.DEFAULT_ZN, Str.DEFAULT).setChildrenList(Lists.newArrayList(KVEntity.of("HTTP检查请求", "HTTP")));
  }

  @Override
  public void exec(TaskCheckRunnerReq req) {

    try {
      @Cleanup HttpClient client = HttpClient.newHttpClient();

      TaskInstanceHistory currentTaskInstanceHistory = req.getCurrentTaskInstanceHistory();
      TaskInfoDef taskInfoDef = req.getCurrentTaskInfoDef();
      String checkUrl = taskInfoDef.getCheckUrl();
      if (HttpReqMethod.POST.equals(taskInfoDef.getCheckReqMethod())) {

        log.info("check instanceId : {}  taskInstanceId: {} url :{}", currentTaskInstanceHistory.getInstanceId(), currentTaskInstanceHistory.getId(), checkUrl);

        String jsonBody = JSON.toJSONString(req.getLastOutMap());
        // 构建 POST 请求
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(taskInfoDef.getReqUrl())).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8)).build();
        // 发送请求并获取响应
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        checkResponse(response, req);

      } else {
//      else if (HttpReqMethod.GET.equals(taskInfoDef.getReqMethod())) {
        String reqParam = req.getLastOutMap().entrySet().stream().map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + ("=") + (URLEncoder.encode(ValueUtils.value2Str(entry.getValue()), StandardCharsets.UTF_8))).collect(Collectors.joining("&"));
        checkUrl = checkUrl + "?" + reqParam;

        log.info("check instanceId : {}  taskInstanceId: {} url :{}", currentTaskInstanceHistory.getInstanceId(), currentTaskInstanceHistory.getId(), checkUrl);

        // 构建 GET 请求
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(checkUrl)).GET().build();
        // 发送请求并获取响应
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // 将响应体转换为 Map
        checkResponse(response, req);
      }
    } catch (Exception e) {
      log.error("执行异常 {}", e.getMessage(), e);
      failToNexLoop(req);
      throw new RunException(e);
    }
    throw new RunException("请求方法没有实现 " + req.getCurrentTaskInfoDef().getCheckReqMethod());
  }

  private void checkResponse(HttpResponse<String> response, TaskCheckRunnerReq req) {
    String body = response.body();
    TaskInstanceHistory currentTaskInstanceHistory = req.getCurrentTaskInstanceHistory();
    Long taskId = currentTaskInstanceHistory.getId();
    log.debug("check instanceId : {}  taskInstanceId: {} response :{}", currentTaskInstanceHistory.getInstanceId(), taskId, body);

    Map<String, Object> retMap = JSONObject.parseObject(response.body(), new TypeReference<Map<String, Object>>() {
    }.getType());
    if (BooleanUtil.toBoolean(String.valueOf(retMap.get("data")))) {
      RunUtils.run("任务检查成功，执行下一个任务 " + taskId, req.getSuccessRun());
    } else {
      failToNexLoop(req);
    }
  }


}
