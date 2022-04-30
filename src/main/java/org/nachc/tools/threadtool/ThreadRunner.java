package org.nachc.tools.threadtool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.nachc.tools.threadtool.runnableiter.ThreadToolUser;
import org.nachc.tools.threadtool.worker.ThreadToolWorker;
import org.nachc.tools.threadtool.worker.ThreadToolWorkerRunnable;
import org.yaorma.util.time.TimeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadRunner {

	private int numberOfThreadsPerWorker;

	private int numberOfRunnablesPerWorker;

	private int numberOfWorkers;

	private ThreadToolUser runnableIter;

	private List<ThreadToolWorker> active = new ArrayList<ThreadToolWorker>();

	private Object lock = new Object();

	private ThreadPoolExecutor executor;

	public ThreadRunner(int numberOfThreadsPerWorker, int numberOfRunnablesPerWorker, int numberOfWorkers, ThreadToolUser runnableIter) {
		this.numberOfThreadsPerWorker = numberOfThreadsPerWorker;
		this.numberOfRunnablesPerWorker = numberOfRunnablesPerWorker;
		this.numberOfWorkers = numberOfWorkers;
		this.runnableIter = runnableIter;
		this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfWorkers);
	}

	public void exec() {
		synchronized (lock) {
			addWorkers();
		}
		while (true) {
			synchronized (lock) {
				if(this.active.size() == 0) {
					break;
				}
			}
		}
		log.info("SHUTTING DOWN----------------");
		executor.shutdown();
		try {
			executor.awaitTermination(1000, TimeUnit.HOURS);
		} catch (Exception exp) {
			throw (new RuntimeException(exp));
		}
	}

	private void addWorkers() {
		while (runnableIter.hasNext() && active.size() < numberOfWorkers) {
			ThreadToolWorker worker = getNextWorker();
			if (worker == null) {
				break;
			} else {
				this.active.add(worker);
			}
		}
	}

	private ThreadToolWorker getNextWorker() {
		synchronized (lock) {
			if (runnableIter.hasNext() == false) {
				return null;
			}
			List<Runnable> runnableList = new ArrayList<Runnable>();
			while (runnableList.size() < numberOfRunnablesPerWorker && runnableIter.hasNext()) {
				runnableList.add(runnableIter.getNext());
			}
			ThreadToolWorker worker = new ThreadToolWorker(runnableList, numberOfThreadsPerWorker, this);
			ThreadToolWorkerRunnable runnable = new ThreadToolWorkerRunnable(worker);
			this.executor.execute(runnable);
			return worker;
		}
	}

	public void done(ThreadToolWorker worker) {
		synchronized (lock) {
			log.info("start done");
			this.active.remove(worker);
			if (active.size() > 0) {
				addWorkers();
			}
			log.info("done done");
		}
	}

	public void logActive() {
		synchronized (lock) {
			log.info("------------");
			log.info("active:  " + active.size());
			log.info("waiting: " + runnableIter.waiting());
			log.info("------------");
		}
	}

}
