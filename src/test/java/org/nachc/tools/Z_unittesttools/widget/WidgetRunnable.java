package org.nachc.tools.Z_unittesttools.widget;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WidgetRunnable implements Runnable {

	Widget widget;

	public WidgetRunnable(Widget widget) {
		this.widget = widget;
	}

	@Override
	public void run() {
		widget.build();
	}

}
