package org.nachc.tools.Z_unittesttools.widget.runnableiter;

import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.Z_unittesttools.widget.Widget;
import org.nachc.tools.Z_unittesttools.widget.WidgetRunnable;
import org.nachc.tools.Z_unittesttools.widget.impl.CreateStringsWidget;
import org.nachc.tools.threadtool.runnableiter.ThreadToolUser;

public class WidgetRunnableIter implements ThreadToolUser {

	private List<Runnable> runnableList = new ArrayList<Runnable>();

	private Object lock = new Object();

	public WidgetRunnableIter(int howMany, int logRate) {
		CreateStringsWidget template = new CreateStringsWidget(0, 0, logRate);
		for (int i = 0; i < howMany; i++) {
			Widget widget = template.getNewInstance();
			WidgetRunnable runnable = new WidgetRunnable(widget);
			this.runnableList.add(runnable);
		}
	}

	@Override
	public Runnable getNext() {
		synchronized (lock) {
			if (this.runnableList.size() > 0) {
				return (this.runnableList.remove(0));
			} else {
				return null;
			}
		}
	}

	@Override
	public boolean hasNext() {
		synchronized (lock) {
			if (this.runnableList.size() > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public int waiting() {
		synchronized (lock) {
			return this.runnableList.size();
		}
	}

}
