package org.nachc.tools.Z_unittesttools.filewriter;

public class FileWriterRunnable implements Runnable {

	FileWriter fileWriter;

	public FileWriterRunnable(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}

	@Override
	public void run() {
		fileWriter.writeFile();
	}
	
}
