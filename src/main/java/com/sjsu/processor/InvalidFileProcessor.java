package com.sjsu.processor;

import java.util.List;

import com.sjsu.dto.CreditCard;

public class InvalidFileProcessor extends FileProcessor {

	public InvalidFileProcessor(String InputFileName, String OutputFileName) {
		super(InputFileName, OutputFileName);
	}

	@Override
	public void readCreditCardRecords(List<CreditCard> cards) {
		System.out.println("Error: Cannot read records from an unsupported file");
	}

	@Override
	public void writeCreditCardRecords(List<CreditCard> cards) {
		System.out.println("Error: Cannot write records to an unsupported file");
	}

}
