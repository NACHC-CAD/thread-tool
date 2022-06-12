package org.nachc.tools.Z_unittesttools.cpuuser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CpuUserRunnable implements Runnable {

	CpuUser cpuUser;

	public CpuUserRunnable(CpuUser cpuUser) {
		this.cpuUser = cpuUser;
	}

	@Override
	public void run() {
		cpuUser.spin();
	}

}
