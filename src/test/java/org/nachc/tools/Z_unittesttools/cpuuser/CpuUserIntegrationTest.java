package org.nachc.tools.Z_unittesttools.cpuuser;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CpuUserIntegrationTest {

	@Test
	public void shouldRunCpuUser() {
		log.info("Starting test...");
		CpuUser cpuUser = new CpuUser(30, 1);
		cpuUser.spin();
		log.info("Done.");
	}
	
}
