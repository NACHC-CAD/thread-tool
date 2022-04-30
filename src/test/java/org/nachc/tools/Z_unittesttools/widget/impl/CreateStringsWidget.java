package org.nachc.tools.Z_unittesttools.widget.impl;

import org.nachc.tools.Z_unittesttools.widget.Widget;
import org.yaorma.util.time.TimeUtil;

import com.nach.core.util.guid.GuidFactory;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class CreateStringsWidget implements Widget {

	private static int idCounter = 0;
	
	private int logRate;

	private int lot;

	private int id;

	public CreateStringsWidget(int lot, int id, int logRate) {
		this.lot = lot;
		this.id = id;
		this.logRate = logRate;
		if(id % logRate == 0) {
			log.info("Constructor for widget called: " + id);
		}
	}

	@Override
	public Widget getNewInstance() {
		idCounter++;
		return new CreateStringsWidget(this.lot, idCounter, this.logRate);
	}

	@Override
	public void build() {
		if (id % logRate == 0) {
			log.info("Starting build: " + lot + "/" + id);
		}
		String guid = null;
		/*
		for (int i = 0; i < 1000; i++) {
			guid = GuidFactory.getGuid();
		}
		*/
		TimeUtil.sleep(1);
		if (id % logRate == 0) {
			log.info("DONE: " + lot + "/" + id + " (" + guid + ")");
		}
	}

}
