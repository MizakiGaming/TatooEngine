package com.engine.utils;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TatooLog { // class pour la gestion des logs

	final private String DEBUG = "[DEBUG]";

	private boolean debug;

	private ArrayList<String> logs;

	private FileManager fileManager;
	private File logFile;

	public TatooLog(boolean debug) { // si le mode debug est activé toute les log de debuging vont être affiché
		this.debug = debug;
		logs = new ArrayList<String>();

		this.logFile = new File(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/logs/"), getDate() + ".txt");
		this.fileManager = new FileManager(this.logFile);
		dispatch("Generate file: " + this.logFile.getPath());
	}

	private String getDate() {
		return new SimpleDateFormat("dd-MM-yyyy'|'hh:mm:ss").format(new Date());
	}

	public void dispatch(String log) { // afficher une log avec une date
		System.out.println("[" + getDate() + "] : " + log);
		logs.add("[" + getDate() + "] : " + log);
	}

	public void debugingDispatch(String log) {
		if (debug) {
			System.out.println(DEBUG + " | [" + getDate() + "] : " + log);
			logs.add(DEBUG + " | [" + getDate() + "] : " + log);
		}
	}

	public void setDebugMode(boolean debugMode) {
		this.debug = debugMode;
	}

	public ArrayList<String> getLogs() {
		return logs;
	}

	public void printLog() { // imprimer les log dans un fichier texte
		this.fileManager.printList(getLogs());
	}

}
