package com.hsbc.rbwm.codecamp.converters.beans;

public class RetrieveTransactionResponse {

	private ResponseHeader responseHeader;
	
	private ResponseBody responseBody;

	public ResponseHeader getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(ResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}

	public ResponseBody getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(ResponseBody responseBody) {
		this.responseBody = responseBody;
	}
	
}
