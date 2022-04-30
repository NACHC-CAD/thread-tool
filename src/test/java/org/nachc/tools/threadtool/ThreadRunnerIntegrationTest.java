package org.nachc.tools.threadtool;

import org.junit.Test;
import org.nachc.tools.Z_unittesttools.widget.runnableiter.WidgetRunnableIter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadRunnerIntegrationTest {

	private static final int NUMBER_TO_MAKE = 1000;
	
	private static final int THREADS_PER_WORKER = 10;
	
	private static final int RUNNABLES_PER_WORKER = 100;
	
	private static final int NUMBER_OF_WORKERS = 10;
	
	@Test
	public void shouldMakeWidegets() {
		log.info("Starting test...");
		WidgetRunnableIter iter = new WidgetRunnableIter(NUMBER_TO_MAKE);
		ThreadRunner runner = new ThreadRunner(THREADS_PER_WORKER, NUMBER_OF_WORKERS, RUNNABLES_PER_WORKER, iter);
		runner.exec();
		log.info("Done.");
	}
	
}
