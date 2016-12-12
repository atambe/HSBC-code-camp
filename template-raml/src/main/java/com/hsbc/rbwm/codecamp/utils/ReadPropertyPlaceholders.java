package com.hsbc.rbwm.codecamp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;

public class ReadPropertyPlaceholders {

	private static int HTTP_ERROR_STATUS = 500;
	
	public int getHttpStatus(String reasonCode) {
		Properties prop = new Properties();
		InputStream input = null;
		int httpsStatus = HTTP_ERROR_STATUS;
		try {
			
			String filename = "template.properties";
			input = getClass().getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				return httpsStatus;
			}
			prop.load(input);
			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);
				if (Arrays.asList(value.split(",")).contains(reasonCode) && key.matches("\\d{3}")) {
					httpsStatus = Integer.parseInt(key);
				}

			}

		} catch (IOException ex) {
			ex.printStackTrace();
			return httpsStatus;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					
					return httpsStatus;
				}
			}
		}
		return httpsStatus;

	}

	
	
	
	public String getErrorDescription(String reasonCode){
		Properties prop = new Properties();
		InputStream input = null;
		String errorDescription = "Internal Server Error";
		try {
			
			String filename = "template.properties";
			input = getClass().getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				return errorDescription;
			}
			prop.load(input);
			errorDescription = prop.getProperty(reasonCode);
		} catch (IOException ex) {
			
			return errorDescription;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
					return errorDescription;
				}
			}
		}
		return errorDescription;

	}
}
