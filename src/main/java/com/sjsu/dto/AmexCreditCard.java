package com.sjsu.dto;

public class AmexCreditCard extends CreditCard {
    public AmexCreditCard(String CreditCardNumber, String HolderName, String ExpirationDate) {
	super(CreditCardNumber, HolderName, ExpirationDate);
    }

    @Override 
    public String getType() {
	return "AmericanExpress";
    }
}
