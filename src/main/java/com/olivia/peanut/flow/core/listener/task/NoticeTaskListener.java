//package com.olivia.peanut.flow.core.listener.task;
//
//import com.olivia.peanut.flow.core.FlowBaseService;
//import com.olivia.peanut.flow.core.listener.DelegateTaskInfo;
//import lombok.extern.slf4j.Slf4j;
//import org.camunda.bpm.engine.delegate.DelegateTask;
//import org.camunda.bpm.engine.delegate.TaskListener;
//
///***
// *  通知
// */
//@Slf4j
//public class NoticeTaskListener extends FlowBaseService implements TaskListener {
//
//
//  @Override
//  public void notify(DelegateTask delegateTask) {
//    delegateTask.complete();
//    DelegateTaskInfo delegateTaskInfo = getDelegateTaskInfo(delegateTask);
//  }
//}
