 
package org.thebes.util.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Quick way of logging stuff in a formatted message. I thought I would do my own, 
 * and since I'm not that interested in logging, this will do for now.
 * @author smokey
 * 
 */
public final class Logger {

	/**
	 * The date
	 */
	public Date date = new Date();

	/**
	 * Date format
	 */
	public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");

	/**
	 * The loggers name.
	 */
	public String name = null;

	/**
	 * Create a new logger
	 * 
	 * @param name
	 *            the name of the logger
	 */
	public Logger(String name) {
		this.name = name;
	}

	/**
	 * Log a message
	 * 
	 * @param level
	 *            the messages severity level
	 * @param s
	 *            the message to log
	 */
	public void log(Level level, String s) { 
            System.out.println(s);
		StringBuilder sb = new StringBuilder();
		sb.append(level).append(": ").append(s).append("\n");
		System.out.println("[" + getDate() + "]: " + name);
		switch (level) {
		case INFO:
		case WARN:
			System.out.println(sb.toString());
			break;
		case FATAL:
		case ERROR:
			System.err.println(sb.toString());
			break;
		}

	}

	/**
	 * Gets the current date
	 * 
	 * @return the current date
	 */
	public String getDate() { 
		return simpleDateFormat.format(date);
	}

	/**
	 * Gets a new logger.
	 * 
	 * @param name
	 *            the name to append
	 * @return a new logger instance
	 */
	public static Logger getLogger(String name) { 
		return new Logger(name);
	}

}
