package com.sjsu.error;

import com.sjsu.dto.CreditCard;

public class ErrorCreditCard extends CreditCard {

	private String ErrorReason;

	public ErrorCreditCard(String CreditCardNumber, String HolderName, 
			String ErrorReason) {
		super(CreditCardNumber, HolderName, "");
		this.ErrorReason = ErrorReason;
	}
	public String getType() {
		return "Error";
	}
	@Override
	public String getErrorReason() {
		return ErrorReason;
	}

}
