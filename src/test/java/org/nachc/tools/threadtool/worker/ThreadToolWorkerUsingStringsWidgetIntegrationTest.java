package org.nachc.tools.threadtool.worker;

import java.util.ArrayList;

import org.junit.Test;
import org.nachc.tools.Z_unittesttools.widget.Widget;
import org.nachc.tools.Z_unittesttools.widget.WidgetRunnable;
import org.nachc.tools.Z_unittesttools.widget.impl.CreateStringsWidget;
import org.yaorma.util.time.Timer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadToolWorkerUsingStringsWidgetIntegrationTest {

	private static final int NUMBER_OF_WIDGETS = 10000;

	private static final int NUMBER_OF_THREADS = 1000;
	
	@Test
	public void shouldCreateWidgets() {
		testWidget(new CreateStringsWidget(0, 0, 1000));
	}
	
	private void testWidget(Widget template) {
		Timer timer = new Timer();
		log.info("Starting test...");
		ArrayList<Runnable> runnableList = new ArrayList<Runnable>();
		for (int i = 0; i < NUMBER_OF_WIDGETS; i++) {
			Widget widget = template.getNewInstance();
			WidgetRunnable runnable = new WidgetRunnable(widget);
			runnableList.add(runnable);
		}
		timer.start();
		ThreadToolWorker worker = new ThreadToolWorker(runnableList, NUMBER_OF_THREADS, null);
		worker.exec();
		timer.stop();
		log.info("");
		log.info("------------------------");
		log.info("class:   " + template.getClass().getName());
		log.info("elapsed: " + timer.getElapsedString());
		log.info("------------------------");
		log.info("");
		log.info("Done.");
	}

}

