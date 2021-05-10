package com.sjsu.dto;

public class DiscoverCreditCard extends CreditCard {
    public DiscoverCreditCard(String CreditCardNumber, String HolderName, String ExpirationDate) {
	super(CreditCardNumber, HolderName, ExpirationDate);
    }

    public String getType() {
	return "Discover";
    }
}
