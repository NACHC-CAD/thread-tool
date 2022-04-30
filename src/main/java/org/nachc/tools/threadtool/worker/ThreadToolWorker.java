package org.nachc.tools.threadtool.worker;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.nachc.tools.threadtool.ThreadRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadToolWorker {

	private List<Runnable> runnableList;

	private int numberOfThreads;

	private ThreadRunner runner;

	public ThreadToolWorker(List<Runnable> runnableList, int numberOfThreads, ThreadRunner runner) {
		this.runnableList = runnableList;
		this.numberOfThreads = numberOfThreads;
		this.runner = runner;
	}

	public void exec() {
		try {
			ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfThreads);
			for (Runnable runnable : runnableList) {
				executor.execute(runnable);
			}
			executor.shutdown();
			try {
				executor.awaitTermination(1000, TimeUnit.HOURS);
			} catch (Exception exp) {
				throw (new RuntimeException(exp));
			}
		} finally {
			if(runner != null) {
				runner.done(this);
			}
		}
		this.runner.logActive();
	}
}
