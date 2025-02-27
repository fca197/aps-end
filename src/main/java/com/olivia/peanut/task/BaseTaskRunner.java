package com.olivia.peanut.task;

import okhttp3.internal.concurrent.TaskRunner;

public interface BaseTaskRunner {

  void executeTask(TaskRunner taskRunner);
}
