package com.hsbc.rbwm.codecamp.converters.beans;

public class RetrieveTranactionRequest {
	
	private RequestHeader header;
	
	private RequestPayload payload;

	public RequestHeader getHeader() {
		return header;
	}

	public void setHeader(RequestHeader header) {
		this.header = header;
	}

	public RequestPayload getPayload() {
		return payload;
	}

	public void setPayload(RequestPayload payload) {
		this.payload = payload;
	}
	
	

}
