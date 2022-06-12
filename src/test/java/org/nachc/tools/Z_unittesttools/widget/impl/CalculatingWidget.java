package org.nachc.tools.Z_unittesttools.widget.impl;

import java.util.Random;

import org.nachc.tools.Z_unittesttools.widget.Widget;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CalculatingWidget implements Widget {

	private double max;

	private int threadId;

	public static void main(String[] args) {
		new CalculatingWidget(10000000000L * 1.1, 1).build();
	}

	public CalculatingWidget(double max, int threadId) {
		this.max = max;
		this.threadId = threadId;
	}

	@Override
	public Widget getNewInstance() {
		double max = 100000000000L * java.lang.Math.random();
		return new CalculatingWidget(max, threadId);
	}

	@Override
	public void build() {
		spin(5000);
		log.info("Done with thread " + threadId + "\t (cnt=" + max + ")");
	}
	
	private static void spin(int milliseconds) {
	    long sleepTime = milliseconds*1000000L; // convert to nanoseconds
	    long startTime = System.nanoTime();
	    while ((System.nanoTime() - startTime) < sleepTime) {}
	}

}
