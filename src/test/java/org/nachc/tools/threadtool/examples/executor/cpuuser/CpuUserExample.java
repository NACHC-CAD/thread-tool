package org.nachc.tools.threadtool.examples.executor.cpuuser;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.nachc.tools.Z_unittesttools.cpuuser.CpuUser;
import org.nachc.tools.Z_unittesttools.cpuuser.CpuUserRunnable;
import org.nachc.tools.threadtool.worker.ThreadToolWorker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CpuUserExample {

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
		int max = 9;
		int cnt = 0;
		for(int n=0;n<10;n++) {
			for (int i = 0; i < max; i++) {
				CpuUser cpuUser = new CpuUser(10, cnt++);
				CpuUserRunnable runnable = new CpuUserRunnable(cpuUser);
				rtn.add(runnable);
			}
			CpuUser cpuUser = new CpuUser(20, cnt++);
			CpuUserRunnable runnable = new CpuUserRunnable(cpuUser);
			rtn.add(runnable);
			
		}
		return rtn;
	}

}
