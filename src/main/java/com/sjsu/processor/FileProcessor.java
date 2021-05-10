package com.sjsu.processor;

import java.util.List;

import com.sjsu.dto.CreditCard;

public abstract class FileProcessor {
	protected String inFileName;
	protected String outFileName;

	public FileProcessor(String InputFileName, String OutputFileName) {
		inFileName = InputFileName;
		outFileName = OutputFileName;
	}

	abstract public void readCreditCardRecords(List<CreditCard> cards);
	abstract public void writeCreditCardRecords(List<CreditCard> cards);
}

