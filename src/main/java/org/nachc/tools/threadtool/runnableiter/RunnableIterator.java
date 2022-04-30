package org.nachc.tools.threadtool.runnableiter;

public interface RunnableIterator {

	public Runnable getNext();
	
	public boolean hasNext();
	
	public int waiting();
	
}
