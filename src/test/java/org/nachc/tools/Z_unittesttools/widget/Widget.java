package org.nachc.tools.Z_unittesttools.widget;

import org.yaorma.util.time.TimeUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class Widget {

	private int lot;

	private int id;

	public Widget(int lot, int id) {
		this.lot = lot;
		this.id = id;
	}

	public void build() {
		log.info("Starting build: " + lot + "/" + id);
		TimeUtil.sleep(1);
		log.info("DONE: " + lot + "/" + id);
	}

}
