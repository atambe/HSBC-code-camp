package com.hsbc.rbwm.codecamp.converters;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hsbc.rbwm.codecamp.converters.beans.ResponseBody;
import com.hsbc.rbwm.codecamp.converters.beans.ResponseHeader;
import com.hsbc.rbwm.codecamp.converters.beans.RetrieveTransactionResponse;
import com.hsbc.rbwm.codecamp.converters.beans.Transaction;


public class InBoundConverter {

	public static final String NULL_PAD_REG_EXP = "/^\\s+$/";

	public RetrieveTransactionResponse unmarshall(String fixlength) {

		if (null != fixlength) {
				RetrieveTransactionResponse response = new RetrieveTransactionResponse();
				ResponseHeader header = new ResponseHeader();
				header.setReasonCode(this.unmarshallReasonCode(fixlength));
				header.setResponseCode(this.unmarshallResponseCode(fixlength));
				response.setResponseHeader(header);
				ResponseBody body = new ResponseBody();
				body.setAccountNumber(this.unmarshallAccountNumber(fixlength));
				body.setTransactionCount(this.unmarshallTransactionCount(fixlength));
				body.setTransactions(this.unmarshallTransactions(fixlength, body.getTransactionCount()));
				response.setResponseBody(body);
				return response;
		}
		return null;

	}
	
	
	/*public static void main(String[] args) {
		String str =  "000       1234567890123403098765432120161210TOACCXXX3707 DR     00000000000300020CR098765432120161210TOACCXXX3707 DR     00000000000300020CR098765432120161210TOACCXXX3707 DR     00000000000300020CR";
		System.out.println(new InBoundConverter().unmarshall(str));
	}*/

	public List<Transaction> unmarshallTransactions(String data, Integer transactionCount) {
		if (transactionCount > 0) {
				String transactionData = data.substring(26, data.length());
				int fixCount = 57;
				int beginIndex = 0;
				int endIndex = 57;
				List<Transaction> tranactions = new ArrayList<Transaction>();
				for (int i = 0; i < transactionCount; i++) {
					String subString = transactionData.substring(beginIndex, endIndex);
					tranactions.add(this.unmarshallTransaction(subString));
					beginIndex = endIndex ;
					endIndex = beginIndex + fixCount;
				}
				return tranactions;
		}
		return null;
	}

	public Transaction unmarshallTransaction(String transactionData) {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(this.unmarshallTransactionId(transactionData));
		transaction.setDescription(this.unmarshallDescription(transactionData));
		transaction.setAmount(this.unmarshallAmount(transactionData));
		transaction.setPostedDate(this.unmarshallPostedDate(transactionData));
		transaction.setMenmonics(this.unmarshallMnemonics(transactionData));
		return transaction;
	}

	private boolean isNull(String fieldData) {
		if (null == fieldData)
			return true;
		return fieldData.matches(NULL_PAD_REG_EXP);
	}

	public String unmarshallResponseCode(String data) {
		if (null == data)
			return null;
		String response = data.substring(0, 3);
		if (this.isNull(response))
			response = null;
		return response;
	}

	public String unmarshallReasonCode(String data) {
		if (null == data)
			return null;
		String response = data.substring(3, 10);
		if (this.isNull(response))
			response = null;
		return response;
	}

	public String unmarshallAccountNumber(String data) {
		if (null == data)
			return null;
		String response = data.substring(10, 24);
		if (this.isNull(response))
			response = null;
		return response;
	}

	public Integer unmarshallTransactionCount(String data) {
		if (null == data)
			return null;
		String response = data.substring(24, 26);
		if (this.isNull(response))
			response = null;
		if (null != response)
			return Integer.parseInt(response.trim());
		return 0;
	}

	public String unmarshallTransactionId(String data) {
		if (null == data)
			return null;
		String response = data.substring(0, 10);
		if (this.isNull(response))
			response = null;
		return response;
	}

	public Date unmarshallPostedDate(String data) {
		if (null == data)
			return null;
		String response = data.substring(10, 18);
		if (!this.isNull(response)) {
			SimpleDateFormat format = new SimpleDateFormat("YYYYMMDD");
			try {
				return format.parse(response);
			} catch (ParseException e) {
				return null;
			}
		}
		return null;
	}

	public String unmarshallDescription(String data) {
		if (null == data)
			return null;
		String response = data.substring(18, 38);
		if (this.isNull(response))
			return null;
		return response.trim();
	}

	public BigDecimal unmarshallAmount(String data) {
		if (null == data)
			return null;
		String response = data.substring(38, 55);
		if (!this.isNull(response)) {
			return new BigDecimal(Long.parseLong(response.trim()));
		}
		return null;
	}

	public String unmarshallMnemonics(String data) {
		if (null == data)
			return null;
		String response = data.substring(55, 57);
		if (this.isNull(response))
			response = null;
		return response;
	}
}
