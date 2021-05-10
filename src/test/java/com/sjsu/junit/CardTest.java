package com.sjsu.junit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.sjsu.core.CCApp;
import com.sjsu.dto.AmexCreditCard;
import com.sjsu.dto.CreditCard;
import com.sjsu.dto.DiscoverCreditCard;
import com.sjsu.dto.MasterCreditCard;
import com.sjsu.dto.VisaCreditCard;
import com.sjsu.factory.CreditCardFactory;

import junit.framework.Assert;

public class CardTest {

	@Test
	public void testIndividualCreditCard() {
		CreditCard card1 = new MasterCreditCard("5100000000232357", "John Doe", "06/30/2021");
		Assert.assertEquals("Master", card1.getType());
		Assert.assertEquals("5100000000232357", card1.getCardNumber());
		Assert.assertEquals("Valid", card1.getErrorReason());
		
		CreditCard card2 = new VisaCreditCard("4123203428879932", "John Doe", "06/30/2021");
		Assert.assertEquals("Visa", card2.getType());
		Assert.assertEquals("4123203428879932", card2.getCardNumber());
		Assert.assertEquals("Valid", card2.getErrorReason());
		
		CreditCard card3 = new AmexCreditCard("346646287785541", "John Doe", "06/30/2021");
		Assert.assertEquals("AmericanExpress", card3.getType());
		Assert.assertEquals("346646287785541", card3.getCardNumber());
		Assert.assertEquals("Valid", card3.getErrorReason());
		
		CreditCard card4 = new DiscoverCreditCard("6011399870435672", "John Doe", "06/30/2021");
		Assert.assertEquals("Discover", card4.getType());
		Assert.assertEquals("6011399870435672", card4.getCardNumber());
		Assert.assertEquals("Valid", card4.getErrorReason());
	}
	
	@Test	
	public void testMasterCardRules() {
		CreditCardFactory factory = new CreditCardFactory();
		
		/* Valid cases */
		CreditCard card1 = factory.getCreditCard("5112345432345321", "John Doe", "06/30/2021");
		Assert.assertEquals("Master", card1.getType());
		Assert.assertEquals("5112345432345321", card1.getCardNumber());
		Assert.assertEquals("Valid", card1.getErrorReason());
		
		CreditCard card2 = factory.getCreditCard("5212345432345321", "John Doe", "06/30/2021");
		Assert.assertEquals("Master", card2.getType());
		Assert.assertEquals("5212345432345321", card2.getCardNumber());
		Assert.assertEquals("Valid", card2.getErrorReason());
		
		CreditCard card3 = factory.getCreditCard("5312345432345321", "John Doe", "06/30/2021");
		Assert.assertEquals("Master", card3.getType());
		Assert.assertEquals("5312345432345321", card3.getCardNumber());
		Assert.assertEquals("Valid", card3.getErrorReason());
		
		CreditCard card4 = factory.getCreditCard("5412345432345321", "John Doe", "06/30/2021");
		Assert.assertEquals("Master", card4.getType());
		Assert.assertEquals("5412345432345321", card4.getCardNumber());
		Assert.assertEquals("Valid", card4.getErrorReason());
		
		CreditCard card5 = factory.getCreditCard("5512345432345321", "John Doe", "06/30/2021");
		Assert.assertEquals("Master", card5.getType());
		Assert.assertEquals("5512345432345321", card5.getCardNumber());
		Assert.assertEquals("Valid", card5.getErrorReason());
		
		/* Invalid cases */
		/* Wrong number of digits */
		CreditCard card6 = factory.getCreditCard("55123454323453210", "John Doe", "06/30/2021");
		Assert.assertEquals("Error", card6.getType());
		Assert.assertEquals("55123454323453210", card6.getCardNumber());
		System.out.println(card6.getErrorReason());
		Assert.assertEquals("Credit card number invalid", card6.getErrorReason());
		
		/* Second digit is not in range [1-5] */
		CreditCard card7 = factory.getCreditCard("5612345432345321", "John Doe", "06/30/2021");
		Assert.assertEquals("Error", card7.getType());
		Assert.assertEquals("5612345432345321", card7.getCardNumber());
		Assert.assertEquals("Credit card type invalid", card7.getErrorReason());
		
		CreditCard card8 = factory.getCreditCard("5012345432345321", "John Doe", "06/30/2021");
		Assert.assertEquals("Error", card8.getType());
		Assert.assertEquals("5012345432345321", card8.getCardNumber());
		Assert.assertEquals("Credit card type invalid", card8.getErrorReason());
	}
	
	@Test	
	public void testDiscoverRules() {
		CreditCardFactory factory = new CreditCardFactory();
		
		/* Valid cases */
		CreditCard card1 = factory.getCreditCard("6011123456789876", "John Doe", "06/30/2021");
		Assert.assertEquals("Discover", card1.getType());
		Assert.assertEquals("6011123456789876", card1.getCardNumber());
		Assert.assertEquals("Valid", card1.getErrorReason());
		
		CreditCard card2 = factory.getCreditCard("6011202576853003", "John Doe", "06/30/2021");
		Assert.assertEquals("Discover", card2.getType());
		Assert.assertEquals("6011202576853003", card2.getCardNumber());
		Assert.assertEquals("Valid", card2.getErrorReason());
		
		/* Invalid cases */
		CreditCard card3 = factory.getCreditCard("6010202576853003", "John Doe", "06/30/2021");
		Assert.assertEquals("Error", card3.getType());
		Assert.assertEquals("6010202576853003", card3.getCardNumber());
		Assert.assertEquals("Credit card type invalid", card3.getErrorReason());
		
		CreditCard card4 = factory.getCreditCard("6000202576853003", "John Doe", "06/30/2021");
		Assert.assertEquals("Error", card4.getType());
		Assert.assertEquals("6000202576853003", card4.getCardNumber());
		Assert.assertEquals("Credit card type invalid", card4.getErrorReason());
	}
	
	@Test	
	public void testAmexRules() {
		CreditCardFactory factory = new CreditCardFactory();
		
		/* Valid cases */
		CreditCard card1 = factory.getCreditCard("340005553425543", "John Doe", "06/30/2021");
		Assert.assertEquals("AmericanExpress", card1.getType());
		Assert.assertEquals("340005553425543", card1.getCardNumber());
		Assert.assertEquals("Valid", card1.getErrorReason());
	
		CreditCard card2 = factory.getCreditCard("370005553425543", "John Doe", "06/30/2021");
		Assert.assertEquals("AmericanExpress", card2.getType());
		Assert.assertEquals("370005553425543", card2.getCardNumber());
		Assert.assertEquals("Valid", card2.getErrorReason());
		
		/* Invalid cases */
		CreditCard card3 = factory.getCreditCard("310005553425543", "John Doe", "06/30/2021");
		Assert.assertEquals("Error", card3.getType());
		Assert.assertEquals("310005553425543", card3.getCardNumber());
		Assert.assertEquals("Credit card type invalid", card3.getErrorReason());
		
		CreditCard card4 = factory.getCreditCard("680005553425543", "John Doe", "06/30/2021");
		Assert.assertEquals("Error", card4.getType());
		Assert.assertEquals("680005553425543", card4.getCardNumber());
		Assert.assertEquals("Credit card type invalid", card4.getErrorReason());
		
		CreditCard card5 = factory.getCreditCard("640005553425543", "John Doe", "06/30/2021");
		Assert.assertEquals("Error", card5.getType());
		Assert.assertEquals("640005553425543", card5.getCardNumber());
		Assert.assertEquals("Credit card type invalid", card5.getErrorReason());
	}
	
	@Test
	public void testVisaRule() {
		CreditCardFactory factory = new CreditCardFactory();
		
		/* Valid cases */
		CreditCard card1 = factory.getCreditCard("4000000384729", "John Doe", "06/30/2021");
		Assert.assertEquals("Visa", card1.getType());
		Assert.assertEquals("4000000384729", card1.getCardNumber());
		Assert.assertEquals("Valid", card1.getErrorReason());
	
		CreditCard card2 = factory.getCreditCard("4239202238374823", "John Doe", "06/30/2021");
		Assert.assertEquals("Visa", card2.getType());
		Assert.assertEquals("4239202238374823", card2.getCardNumber());
		Assert.assertEquals("Valid", card2.getErrorReason());
		
		/* Invalid cases */
		CreditCard card3 = factory.getCreditCard("3093483847293", "John Doe", "06/30/2021");
		Assert.assertEquals("Error", card3.getType());
		Assert.assertEquals("3093483847293", card3.getCardNumber());
		Assert.assertEquals("Credit card type invalid", card3.getErrorReason());
	}
}
