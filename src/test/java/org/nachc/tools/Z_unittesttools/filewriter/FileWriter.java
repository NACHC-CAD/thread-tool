package org.nachc.tools.Z_unittesttools.filewriter;

import java.io.File;
import java.io.InputStream;

import org.yaorma.util.time.Timer;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileWriter {

	private static Object lock = new Object();

	private static final String SOURCE_FILE_PATH = "/org/nachc/tools/Z_unittesttools/filewriter/patients.csv";

	private static final String OUTPUT_FILE_PATH = "/testoutput/output-file";

	private boolean isLongRunning;
	
	private int id;

	public FileWriter(int id, boolean isLongRunning) {
		this.id = id;
		this.isLongRunning = isLongRunning;
	}

	public boolean isLongRunning() {
		return this.isLongRunning;
	}
	
	public void writeFile() {
		log.info("Starting thread " + id);
		Timer timer = new Timer();
		timer.start();
		InputStream in = FileUtil.getInputStream(SOURCE_FILE_PATH);
		String fileName = OUTPUT_FILE_PATH + "-" + this.id + ".csv";
		File outFile = FileUtil.getFile(fileName);
		FileUtil.write(in, outFile);
		if(isLongRunning) {
			writeFiles(fileName);
		}
		timer.stop();
		synchronized (lock) {
			log.info("--------------");
			log.info("Finished worker:  " + this.id);
			log.info("Started:  " + timer.getStartAsString());
			log.info("Finished: " + timer.getStopAsString());
			log.info("Elapsed:  " + timer.getElapsedString());
			log.info("--------------");
		}
	}

	private void writeFiles(String fileName) {
		for (int i = 1; i < 10; i++) {
			InputStream in = FileUtil.getInputStream(fileName);
			String outFileName = fileName + "-" + i + ".csv";
			File outFile = FileUtil.getFile(outFileName);
			FileUtil.write(in, outFile);
			try {
				in.close();
			} catch(Exception exp) {
				throw new RuntimeException(exp);
			}
		}
	}

}
