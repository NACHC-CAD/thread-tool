package org.nachc.tools.threadtool.worker;

import java.util.ArrayList;

import org.junit.Test;
import org.nachc.tools.Z_unittesttools.widget.Widget;
import org.nachc.tools.Z_unittesttools.widget.WidgetRunnable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadToolWorkerIntegrationTest {

	private int MAX = 1000;

	@Test
	public void shouldCreateWidgets() {
		log.info("Starting test...");
		ArrayList<Runnable> runnableList = new ArrayList<Runnable>();
		for (int i = 0; i < MAX; i++) {
			Widget widget = new Widget(1, i);
			WidgetRunnable runnable = new WidgetRunnable(widget);
			runnableList.add(runnable);
		}
		ThreadToolWorker worker = new ThreadToolWorker(runnableList);
		worker.exec();
		log.info("Done.");
	}

}
