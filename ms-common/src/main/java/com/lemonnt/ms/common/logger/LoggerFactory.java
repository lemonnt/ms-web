package com.lemonnt.ms.common.logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerFactory {
	private static final String DEFAULT_NAME ="DEFAULT";
	public static final Logger getLogger(String name){
		if(null == name)
			name = DEFAULT_NAME;
		Logger logger = Logger.getLogger(name);
		logger.setLevel(Level.ALL);
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.OFF);  
		logger.addHandler(consoleHandler);
		return logger;
	}

}
