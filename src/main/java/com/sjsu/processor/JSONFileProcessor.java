package com.sjsu.processor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sjsu.dto.CreditCard;
import com.sjsu.factory.CreditCardFactory;


public class JSONFileProcessor extends FileProcessor {
	private CreditCardFactory cardFactory;
	private JSONArray jsonObjects;

	public JSONFileProcessor(String InputFileName, String OutputFileName) {
		super(InputFileName, OutputFileName);
		cardFactory = new CreditCardFactory();
		jsonObjects = new JSONArray();
	}

	@Override
	public void readCreditCardRecords(List<CreditCard> cards) {
		/* Get all the lines from the input file */
		getJSONObjectsFromFile();

		if (jsonObjects.size() == 0) {
			/* TODO: Add an error message here */
			return;
		}

		/* Convert all lines to credit cards */
		processJSONObjects(cards);
	}

	@Override
	public void writeCreditCardRecords(List<CreditCard> cards) {
		try {
			FileWriter outFile = new FileWriter(outFileName);
			/* Write the title here */
			outFile.write("[");
			int objCount = 0;
			Iterator<CreditCard> cardIterator = cards.iterator();
			while (cardIterator.hasNext()) {
				if (objCount != 0) {
					outFile.write(",");
				}
				objCount++;
				CreditCard card = cardIterator.next();
				String cardNumber = card.getNumber();
				String cardType = card.getType();
				String cardErrorReason = card.getErrorReason();
				
				outFile.write("{ \"CardNumber\" : " + cardNumber + ", \"CardType\" : \"" + cardType + "\", \"Error\" : \"" + cardErrorReason + "\" }");
			}
			outFile.write("]");
			outFile.close();
		} catch (Exception e) {
			System.out.println("Error: Exception in writing to file");
		}
	}

	private void getJSONObjectsFromFile() {
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(inFileName))
		{
			Object obj = jsonParser.parse(reader);
			jsonObjects = (JSONArray) obj;
		} catch (FileNotFoundException e) {
			System.out.println("Error: File " + inFileName + " could not be found");
		} catch (ParseException e) {
			System.out.println("Error: JSON parse failure: " + e);
		} catch (Exception e) {
			System.out.println("Error: Other exception found: " + e);
		}
	}

	private void processJSONObjects(List<CreditCard> cards) {
		int objectCount = 0;
		try {
			for (int idx = 0; idx < jsonObjects.size(); idx++) {
				JSONObject jsonObj = (JSONObject) jsonObjects.get(idx);
				String CreditCardNumber = jsonObj.get("CardNumber").toString();
				String ExpirationDate = jsonObj.get("ExpirationDate").toString();
				String holderName = jsonObj.get("NameOfCardholder").toString();
				CreditCard creditCard = 
						cardFactory.getCreditCard(CreditCardNumber, holderName, ExpirationDate);
				cards.add(creditCard);
				objectCount++;
			}
		} catch (Exception e) {
			System.out.println("Error: Object number " + objectCount + "has an error");
		}
	}

}