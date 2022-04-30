package org.nachc.tools.threadtool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.nachc.tools.threadtool.runnableiter.ThreadToolUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleThreadRunner {

	private ThreadToolUser threadToolUser;

	private int numberOfThreads;

	public SimpleThreadRunner(ThreadToolUser threadToolUser, int numberOfThreads) {
		this.threadToolUser = threadToolUser;
		this.numberOfThreads = numberOfThreads;
	}

	public void exec() {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfThreads);
		while (threadToolUser.hasNext()) {
			Runnable runnable = threadToolUser.getNext();
			executor.execute(runnable);
		}
		executor.shutdown();
		try {
			executor.awaitTermination(1000, TimeUnit.HOURS);
		} catch (Exception exp) {
			throw (new RuntimeException(exp));
		}
	}

}
