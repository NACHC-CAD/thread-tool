package org.nachc.tools.Z_unittesttools.cpuuser;

import org.yaorma.util.time.Timer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CpuUser {

	private static Object lock = new Object();

	private int id;

	private int seconds;

	public CpuUser(int seconds, int id) {
		this.seconds = seconds;
		this.id = id;
	}

	public void spin() {
		log.info("Starting worker: " + this.id);
		Timer timer = new Timer();
		timer.start();
		int milliseconds = this.seconds * 1000;
		long sleepTime = milliseconds * 1000000L; // convert to nanoseconds
		long startTime = System.nanoTime();
		while ((System.nanoTime() - startTime) < sleepTime) {
			// spend time calculating the while condition
		}
		timer.stop();
		synchronized (lock) {
			log.info("--------------");
			log.info("Finished worker:  " + this.id);
			log.info("Started:  " + timer.getStartAsString());
			log.info("Finished: " + timer.getStopAsString());
			log.info("Elapsed:  " + timer.getElapsedString());
			log.info("--------------");
		}
	}

}
