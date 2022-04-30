package org.nachc.tools.threadtool;

import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.threadtool.runnableiter.RunnableIterator;
import org.nachc.tools.threadtool.worker.ThreadToolWorker;
import org.yaorma.util.time.TimeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadRunner {

	private int numberOfThreadsPerWorker;

	private int numberOfRunnablesPerWorker;

	private int numberOfWorkers;

	private RunnableIterator runnableIter;

	private List<ThreadToolWorker> active = new ArrayList<ThreadToolWorker>();

	private Object lock = new Object();

	public ThreadRunner(int numberOfThreadsPerWorker, int numberOfRunnablesPerWorker, int numberOfWorkers, RunnableIterator runnableIter) {
		this.numberOfThreadsPerWorker = numberOfThreadsPerWorker;
		this.numberOfRunnablesPerWorker = numberOfRunnablesPerWorker;
		this.numberOfWorkers = numberOfWorkers;
		this.runnableIter = runnableIter;
	}

	public void exec() {
		synchronized (lock) {
			addWorkers();
		}
		while (this.active.size() > 0) {
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
		if (runnableIter.hasNext() == false) {
			return null;
		}
		List<Runnable> runnableList = new ArrayList<Runnable>();
		while (runnableList.size() < numberOfRunnablesPerWorker && runnableIter.hasNext()) {
			runnableList.add(runnableIter.getNext());
		}
		ThreadToolWorker worker = new ThreadToolWorker(runnableList, numberOfThreadsPerWorker, this);
		worker.exec();
		log.info("------------");
		log.info("active:  " + active.size());
		log.info("waiting: " + runnableIter.waiting());
		log.info("------------");
		return worker;
	}

	public void done(ThreadToolWorker worker) {
		synchronized (lock) {
			this.active.remove(worker);
			addWorkers();
		}
	}

}
