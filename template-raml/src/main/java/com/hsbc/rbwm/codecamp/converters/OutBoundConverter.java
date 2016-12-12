package com.hsbc.rbwm.codecamp.converters;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hsbc.rbwm.codecamp.converters.beans.RequestHeader;
import com.hsbc.rbwm.codecamp.converters.beans.RequestPayload;

public class OutBoundConverter {
	
	private final static String DATE_FORMAT = "YYYYMMDD";
	private final static String DATE_NULL_PAD = "        ";
	
	public String convert(RequestHeader ismHeader, RequestPayload payload){
		String headerFXL = ismHeader.getServiceId()  + ismHeader.getOperationId();
		String payloadFXL = payload.getAccountNumber() + this.dateToStringConverter(payload.getStartDate()) + this.dateToStringConverter(payload.getEndDate());
		return headerFXL + payloadFXL;
	}

	
	
	public String dateToStringConverter(Date date){
		
		if(null != date){
		   SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		   return formatter.format(date);
		}
		return DATE_NULL_PAD;
		
	}
	
}
