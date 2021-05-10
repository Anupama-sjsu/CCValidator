package com.sjsu.dto;

public class MasterCreditCard extends CreditCard {
    public MasterCreditCard(String CreditCardNumber, String HolderName, String ExpirationDate) {
	super(CreditCardNumber, HolderName, ExpirationDate);
    }

    public String getType() {
	return "Master";
    }
}
