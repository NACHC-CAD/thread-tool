package org.nachc.tools.threadtool.runnableiter;

public interface ThreadToolUser {

	public Runnable getNext();
	
	public boolean hasNext();
	
	public int waiting();
	
}
