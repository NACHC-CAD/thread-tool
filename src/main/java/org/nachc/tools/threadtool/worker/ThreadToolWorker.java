package org.nachc.tools.threadtool.worker;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadToolWorker {

	private List<Runnable> runnableList;

	private int numberOfThreads;
	
	public ThreadToolWorker(List<Runnable> runnableList, int numberOfThreads) {
		this.runnableList = runnableList;
		this.numberOfThreads = numberOfThreads;
	}

	public void exec() {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfThreads);
		for (Runnable runnable : runnableList) {
			executor.execute(runnable);
		}
		log.info("SHUTTING DOWN----------------");
		executor.shutdown();
		try {
			executor.awaitTermination(1000, TimeUnit.HOURS);
		} catch (Exception exp) {
			throw (new RuntimeException(exp));
		}
	}
}
