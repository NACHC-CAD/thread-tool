package org.nachc.tools.threadtool.worker;

public class ThreadToolWorkerRunnable implements Runnable {

	private ThreadToolWorker worker;
	
	public ThreadToolWorkerRunnable(ThreadToolWorker worker) {
		this.worker = worker;
	}
	
	@Override
	public void run() {
		worker.exec();
	}

}
