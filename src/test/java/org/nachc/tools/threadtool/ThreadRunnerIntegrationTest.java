package org.nachc.tools.threadtool;

import org.junit.Test;
import org.nachc.tools.Z_unittesttools.widget.runnableiter.WidgetRunnableIter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadRunnerIntegrationTest {

	private static final int NUMBER_TO_MAKE = 1000;

	private static final int THREADS_PER_WORKER = 20;

	private static final int RUNNABLES_PER_WORKER = 20;

	private static final int NUMBER_OF_WORKERS = 200;

	private static final int LOG_RATE = 1000;

	@Test
	public void shouldMakeWidegets() {
		log.info("Starting test...");
		WidgetRunnableIter iter = new WidgetRunnableIter(NUMBER_TO_MAKE, LOG_RATE);
		ThreadRunner runner = new ThreadRunner(THREADS_PER_WORKER, RUNNABLES_PER_WORKER, NUMBER_OF_WORKERS, iter);
		runner.exec();
		log.info("Done.");
	}

}
