package com.cts;

public class EPWLoaderSummary {
	
	private String commandJobName;
	private String environment;
	private String boxJobName;
	
	public EPWLoaderSummary(String commandJobName , String environment , String boxJobName) {
		this.commandJobName = commandJobName;
		this.environment = environment;
		this.boxJobName = boxJobName;
	}
	
	public String getCommandJobName() {
		return commandJobName;
	}
	public String getEnvironment() {
		return environment;
	}
	public String getBoxJobName() {
		return boxJobName;
	}
	
	

}
