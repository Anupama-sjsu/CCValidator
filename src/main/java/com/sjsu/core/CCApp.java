package com.sjsu.core;

import java.util.ArrayList;

import com.sjsu.dto.CreditCard;
import com.sjsu.factory.FileProcessorFactory;
import com.sjsu.processor.FileProcessor;

public class CCApp {
	private String inFileName;
	private String outFileName;
	ArrayList<CreditCard> creditCards;

	public CCApp(String InputFileName, String OutputFileName) {
		inFileName = InputFileName;
		outFileName = OutputFileName;
		creditCards = new ArrayList<CreditCard>();
	}

	public void run() {
		/* Instantiate file processor factory */
		FileProcessorFactory factory = new FileProcessorFactory();

		/* Create the appropriate file processor with the 
	   InputFileName */
		FileProcessor ccFileProcessor = 
				factory.getFileProcessor(inFileName, outFileName);

		/* Process the Input file and create the credit 
	   card records*/
		ccFileProcessor.readCreditCardRecords(creditCards);

		/* Display progress message */
		System.out.println("Processed all records from \"" + inFileName + "\"");

		/* Write records into output file specified 
	   in the outFileName */
		ccFileProcessor.writeCreditCardRecords(creditCards);

		/* Display progress message */
		System.out.println("Completed writing records into file \"" + outFileName + "\"");
	}    
}
