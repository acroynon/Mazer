package com.acroynon.mazer.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

	Properties props = null;
	
	public PropertyManager(String filepath){
		File file = new File(filepath);
		if(file.exists()){
			props = new Properties();
			try {
				props.load(new FileInputStream(filepath));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getStringOrDefault(String key, String defaultValue){
		if(props == null){ return defaultValue; }
		String prop = props.getProperty(key);
		if(prop == null || prop.trim().equals("")){
			return defaultValue;
		}
		return prop;
	}
	
	public int getIntOrDefault(String key, int defaultValue){
		int value = defaultValue;
    	try{
    		value = Integer.parseInt(props.getProperty(key));
    	}catch(NumberFormatException e){
    		// no op
    	}
    	return value;
	}
	
}
