package org.nachc.tools.threadtool.examples.executor.filewriter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.Z_unittesttools.cpuuser.CpuUser;
import org.nachc.tools.Z_unittesttools.cpuuser.CpuUserRunnable;
import org.nachc.tools.Z_unittesttools.filewriter.FileWriter;
import org.nachc.tools.Z_unittesttools.filewriter.FileWriterRunnable;
import org.nachc.tools.threadtool.worker.ThreadToolWorker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileWriterExample {

	@Test
	public void runThreads() {
		log.info("Starting test...");
		List<Runnable> runnableList = getRunnableList();
		ThreadToolWorker worker = new ThreadToolWorker(runnableList, 16, null);
		worker.exec();
		log.info("Done.");
	}
	
	private List<Runnable> getRunnableList() {
		ArrayList<Runnable> rtn = new ArrayList<Runnable>();
		int max = 1000;
		for(int i=0;i<max;i++) {
			FileWriter fileWriter = null;
			if(i % 16 == 0) {
				fileWriter = new FileWriter(i, true);
			} else {
				fileWriter = new FileWriter(i, false);
			}
			FileWriterRunnable runnable = new FileWriterRunnable(fileWriter);
			rtn.add(runnable);
		}
		return rtn;
	}

}
