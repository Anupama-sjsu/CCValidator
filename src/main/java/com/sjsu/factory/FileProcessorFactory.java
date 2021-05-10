package com.sjsu.factory;

import com.sjsu.processor.CSVFileProcessor;
import com.sjsu.processor.FileProcessor;
import com.sjsu.processor.InvalidFileProcessor;
import com.sjsu.processor.JSONFileProcessor;
import com.sjsu.processor.XMLFileProcessor;

public class FileProcessorFactory {

	private static final String UNKNOWN_EXTENSION="unknown";
	private static final String CSV_EXTENSION="csv";
	private static final String JSON_EXTENSION="json";
	private static final String XML_EXTENSION="xml";
	private String getLowerCaseExtension(String fileName) {
		int dotIdx = fileName.lastIndexOf(".");
		if (dotIdx == -1) {
			return UNKNOWN_EXTENSION;
		}
		String ext = fileName.substring(dotIdx + 1).toLowerCase();
		return ext;
	}
	public FileProcessor getFileProcessor(String inFileName, String outFileName) {
		String inExt = getLowerCaseExtension(inFileName);
		String outExt = getLowerCaseExtension(outFileName);
		if (!inExt.equals(outExt)) {
			System.out.println("Please enter the same type of files.");
		}
		FileProcessor processor;
		if (inExt.equals(CSV_EXTENSION)) {
			processor = new CSVFileProcessor(inFileName, outFileName);
		} else if (inExt.equalsIgnoreCase(JSON_EXTENSION)) {
			processor = new JSONFileProcessor(inFileName, outFileName);
		} else if (inExt.equalsIgnoreCase(XML_EXTENSION)) {
			processor = new XMLFileProcessor(inFileName, outFileName);
		} else {
			processor = new InvalidFileProcessor(inFileName, outFileName);
			throw new IllegalArgumentException(inExt + " is not a supported file type");
		}
		return processor;
	}

}