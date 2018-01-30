package org.apache.dynamic.validator.commons;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.dynamic.validator.commons.exception.CommonsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CmdLineParser {

	final static Logger logger = LoggerFactory.getLogger(CmdLineParser.class);

	public abstract Options getOptions();
	
	//public abstract AppArgs parseArgs(String[] args) throws Exception;
	
	private PropertyReader propertyReader = new PropertyReader();
	
	protected CommandLine getCommandLine(String[] args) throws CommonsException {
		CommandLineParser parser = new DefaultParser();
		CommandLine cmdLine;
		try {
			cmdLine = parser.parse(getOptions(), args);
		} catch (ParseException e) {
			getHelpStackTrace();
			throw new CommonsException("Error while parsing the arguments: " + e.getMessage());
		}
		return cmdLine;
	}
	
	public void getHelpStackTrace() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("Help::", getOptions());
	}

	public PropertyReader getPropertyReader() {
		return propertyReader;
	}

}
