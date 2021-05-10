package com.sjsu.dto;

public abstract class CreditCard {
	
	private String cardNumber;
	private String holderName;
	private String expirationDate;

	public CreditCard() {
		// TODO Auto-generated constructor stub
	}

	public CreditCard(String cardNumber, String holderName, String expirationDate) {
		this.cardNumber = cardNumber;
		this.holderName = holderName;
		this.expirationDate = expirationDate;
	}

	abstract public String getType();

	public String getNumber() {
		return cardNumber;
	}
	public String getErrorReason() {
		return "Valid";
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
}
