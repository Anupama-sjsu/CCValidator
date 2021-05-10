package com.sjsu.dto;

public class VisaCreditCard extends CreditCard {
    public VisaCreditCard(String CreditCardNumber, String HolderName, String ExpirationDate) {
	super(CreditCardNumber, HolderName, ExpirationDate);
    }

    public String getType() {
	return "Visa";
    }
}
