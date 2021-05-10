package com.sjsu.processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.sjsu.dto.CreditCard;
import com.sjsu.factory.CreditCardFactory;

public class CSVFileProcessor extends FileProcessor {
	private ArrayList<String> inputLines;
	private CreditCardFactory cardFactory;

	public CSVFileProcessor(String InputFileName, String OutputFileName) {
		super(InputFileName, OutputFileName);
		inputLines = new ArrayList<String>();
		cardFactory = new CreditCardFactory();
	}

	@Override
	public void readCreditCardRecords(List<CreditCard> cards) {
		/* Get all the lines from the input file */
		getLinesFromFile();

		if (inputLines.size() == 0) {
			/* TODO: Add an error message here */
			return;
		}

		/* Convert all lines to credit cards */
		processLinesToCards(cards);
	}

	@Override
	public void writeCreditCardRecords(List<CreditCard> cards) {
		try {
			FileWriter outFile = new FileWriter(outFileName);
			/* Write the title here */
			outFile.write("CardNumber,CardType,Error\n");
			Iterator<CreditCard> cardIterator = cards.iterator();
			while (cardIterator.hasNext()) {
				CreditCard card = cardIterator.next();
				String cardString;
				String cardNumber = card.getNumber();
				String cardType = card.getType();
				String cardErrorReason = card.getErrorReason();
				cardString = 
						(cardNumber + "," + 
								cardType + "," + 
								cardErrorReason + "\n");
				outFile.write(cardString);
			}
			outFile.close();
		} catch (Exception e) {
			System.out.println("Error: Exception in writing to file");
		}
	}

	private void getLinesFromFile() {
		try {
			File fileObj = new File(inFileName);
			Scanner lineReader = new Scanner(fileObj);
			while (lineReader.hasNextLine()) {
				String line = lineReader.nextLine();
				/* Skip the title line in the csv file */
				if (line.toLowerCase().startsWith("card")) {
					continue;
				}
				inputLines.add(line);
			}
			lineReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error: File " + inFileName + " could not be found");
		} catch (Exception e) {
			System.out.println("Error: Other exception found: "  + e);
		}
	}

	private void processLinesToCards(List<CreditCard> cards) {
		String line = "";
		int lineCount = 0;
		try {
			Iterator<String> lineIterator = inputLines.iterator();
			while (lineIterator.hasNext()) {
				line = lineIterator.next();
				List<String> valuesList = 
						Arrays.asList(line.split(",", -1));
				if (valuesList.size() != 3) {
					continue;
				}
				String CreditCardNumber = valuesList.get(0);
				String ExpirationDate = valuesList.get(1);
				String HolderName = valuesList.get(2);
				//		System.out.println("Read line: " + CreditCardNumber + "  " + HolderName + "  " + ExpirationDate);
				CreditCard creditCard = 
						cardFactory.getCreditCard(CreditCardNumber, HolderName, ExpirationDate);
				cards.add(creditCard);
				//		System.out.println("Added credit card for line: " + line);
				lineCount++;
			}
		} catch (Exception e) {
			System.out.println("Error: Line " + line + " has an error, line number: " + String.valueOf(lineCount));
		}
	}
}
