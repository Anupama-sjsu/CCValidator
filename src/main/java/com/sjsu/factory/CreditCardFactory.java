package com.sjsu.factory;

import com.sjsu.dto.AmexCreditCard;
import com.sjsu.dto.CreditCard;
import com.sjsu.dto.DiscoverCreditCard;
import com.sjsu.dto.MasterCreditCard;
import com.sjsu.dto.VisaCreditCard;
import com.sjsu.error.ErrorCreditCard;

public class CreditCardFactory {


	private long checkCardNumber(String CreditCardNumber) {
		long number = 0;
		try {
			number = Double.valueOf(CreditCardNumber).longValue();
			int CardNumberLength = Long.toString(number).length();
			if (CardNumberLength > 16) {
				throw new IllegalArgumentException("Card number is invalid");
			}
			return number;
		} catch (Exception e) {
			throw e;
		}
	}

	public CreditCard getCreditCard(String CreditCardNumber, String HolderName, String ExpirationDate) {
		long number = 0;
		System.out.println("In getCreditCard Processing: " + CreditCardNumber + " " + HolderName + " " + ExpirationDate);
		try {
			number = checkCardNumber(CreditCardNumber);
		} catch (Exception e) {
			return new ErrorCreditCard(CreditCardNumber, HolderName, "Card number invalid");
		}
		System.out.println("Check card number complete");
		int cardNumLength = Long.toString(number).length();
		System.out.println("Card Number of length: " + cardNumLength);

		if (cardNumLength == 15) {
			System.out.println("Creating Amex card");
			return new AmexCreditCard(CreditCardNumber, HolderName, ExpirationDate);

		} else if (cardNumLength == 16 &&
				CreditCardNumber.charAt(0) == '5') {
			System.out.println("Creating Master card");
			return new MasterCreditCard(CreditCardNumber, HolderName, ExpirationDate);

		} else if (cardNumLength == 16 &&
				CreditCardNumber.charAt(0) == '6') {
			System.out.println("Creating Discover card");
			return new DiscoverCreditCard(CreditCardNumber, HolderName, ExpirationDate);

		} else if ((cardNumLength == 13 || cardNumLength == 16) &&
				CreditCardNumber.charAt(0) == '4') {
			System.out.println("Creating Visa card");
			return new VisaCreditCard(CreditCardNumber, HolderName, ExpirationDate);
		} 

		return new ErrorCreditCard(CreditCardNumber, HolderName, "Credit card type invalid");
	}
}
