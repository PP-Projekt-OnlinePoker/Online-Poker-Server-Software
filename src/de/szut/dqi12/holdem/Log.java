package de.szut.dqi12.holdem;

import java.io.File;

public class Log {

	private File logFile;
	
	public Log(File logFile){
		this.logFile = logFile;
	}
	
	public void log(String msg){
		System.out.println("[+] " + msg);
		
	}
	
	public void logError(String error){
		System.out.println("[-] " + error);
	}

}
