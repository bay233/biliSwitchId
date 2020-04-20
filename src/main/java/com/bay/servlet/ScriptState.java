package com.bay.servlet;

import java.io.IOException;
import java.net.URL;

public class ScriptState implements Runnable{
	public static boolean ScriptState = false;
	public URL path;
	
	public ScriptState() {
		
	}

	public ScriptState(URL path) {
		super();
		this.path = path;
	}

	@Override
	public void run() {
		if (!ScriptState){
			ScriptState = true;
			try {
				//Process p = Runtime.getRuntime().exec("python3 "+ path.getPath().substring(1, path.getPath().length()));
				Process p = Runtime.getRuntime().exec("python3 "+ path.getPath());
				if (p.getErrorStream().read() == -1 && p.getInputStream().read() == -1){
					run();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
