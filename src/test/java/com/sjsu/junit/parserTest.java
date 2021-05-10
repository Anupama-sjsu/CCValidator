package com.sjsu.junit;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.sjsu.core.CCApp;

import junit.framework.Assert;

public class parserTest {
	List<String> outCSVFileLines;
	List<String> outJSONFileLines;
	List<String> outXMLFileLines;
	
	/* Helper functions */
	public boolean createTestCSVFile() {
		try {
			FileWriter outFile = new FileWriter("test.csv");
			outFile.write("CardNumber,ExpirationDate,NameOfCardholder\n");
			outFile.write("5410000000000000,3/20/2030,Alice\n");
			outFile.write("4120000000000,4/20/2030,Bob\n");
			outFile.write("5878938422340028,4/20/2030,Rick\n");
			outFile.close();
			return true;
		} catch (Exception e) {
			System.out.println("Exception: " + e + " occurred");
		}
		return false;
	}
	
	public boolean createTestJsonFile() {
		try {
			FileWriter outFile = new FileWriter("test.json");
			outFile.write("[{");
			outFile.write("   \"CardNumber\" : \"5410000000000000\",\n");
			outFile.write("   \"ExpirationDate\" : \"3/20/2030\",\n");
			outFile.write("   \"NameOfCardholder\" : \"Alice\"\n");
			outFile.write(" },\n");
			outFile.write(" { \n");
			outFile.write("   \"CardNumber\" : \"4120000000000\",\n");
			outFile.write("   \"ExpirationDate\" : \"4/20/2030\",\n");
			outFile.write("   \"NameOfCardholder\" : \"Bob\"\n");
			outFile.write(" },\n");
			outFile.write(" { \n");
			outFile.write("   \"CardNumber\" : \"5878938422340028\",\n");
			outFile.write("   \"ExpirationDate\" : \"4/20/2030\",\n");
			outFile.write("   \"NameOfCardholder\" : \"Rick\"\n");
			outFile.write(" }]\n");
			outFile.close();
			return true;
		} catch (Exception e) {
			System.out.println("Exception: " + e + " occurred");
		}
		return false;
	}
	
	public boolean createTestXMLFile() {
		try {
			FileWriter outFile = new FileWriter("test.xml");
			outFile.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>");
			outFile.write("<row>\n");
			outFile.write("<CardNumber>5410000000000000</CardNumber>\n");
			outFile.write("<ExpirationDate>3/20/2030</ExpirationDate>");
			outFile.write("<NameOfCardholder>Alice</NameOfCardholder>");
			outFile.write("</row>\n");
			outFile.write("<row>\n");
			outFile.write("<CardNumber>4120000000000</CardNumber>\n");
			outFile.write("<ExpirationDate>4/20/2030</ExpirationDate>");
			outFile.write("<NameOfCardholder>Bob</NameOfCardholder>");
			outFile.write("</row>\n");
			outFile.write("<row>\n");
			outFile.write("<CardNumber>5878938422340028</CardNumber>\n");
			outFile.write("<ExpirationDate>4/20/2030</ExpirationDate>");
			outFile.write("<NameOfCardholder>Rick</NameOfCardholder>");
			outFile.write("</row>\n");
			outFile.write("</root>\n");
			outFile.close();
			return true;
		} catch (Exception e) {
			System.out.println("Exception: " + e + " occurred");
		}
		return false;
	}
	
	@Before
	public void setUp() throws Exception {
		System.out.println("Setup invoked");
		/* Setup the CSV test */
		createTestCSVFile();
		CCApp app1 = new CCApp("test.csv", "out.csv");
		app1.run();
		try {
			outCSVFileLines = new ArrayList<String>();
			File fileObj = new File("out.csv");
			Scanner lineReader = new Scanner(fileObj);
			while (lineReader.hasNextLine()) {
				String line = lineReader.nextLine();
				outCSVFileLines.add(line);
			}
			lineReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Output file was not generated");
			Assert.fail();
		} catch (Exception e) {
			System.out.println("Error: Other exception found: "  + e);
		}
		
		/* Setup the JSON test */
		createTestJsonFile();
		CCApp app3 = new CCApp("test.json", "out.json");
		app3.run();
		try {
			outJSONFileLines = new ArrayList<String>();
			File fileObj = new File("out.json");
			Scanner lineReader = new Scanner(fileObj);
			while (lineReader.hasNextLine()) {
				String line = lineReader.nextLine();
				outJSONFileLines.add(line);
			}
			lineReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Output file was not generated");
			Assert.fail();
		} catch (Exception e) {
			System.out.println("Error: Other exception found: "  + e);
		}

		
		/* Setup the XML test */
		createTestXMLFile();
		CCApp app2 = new CCApp("test.xml", "out.xml");
		app2.run();
		try {
			outXMLFileLines = new ArrayList<String>();
			File fileObj = new File("out.xml");
			Scanner lineReader = new Scanner(fileObj);
			while (lineReader.hasNextLine()) {
				String line = lineReader.nextLine();
				outXMLFileLines.add(line);
			}
			lineReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Output file was not generated");
			Assert.fail();
		} catch (Exception e) {
			System.out.println("Error: Other exception found: "  + e);
		}
	}

	@Test
	public void testAllParserOutputs() {
		Assert.assertEquals(outXMLFileLines.size(), 1);
		Assert.assertEquals(outXMLFileLines.get(0), 
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><row><CardNumber>5410000000000000</CardNumber><CardType>Master</CardType><Error>Valid</Error></row><row><CardNumber>4120000000000</CardNumber><CardType>Visa</CardType><Error>Valid</Error></row><row><CardNumber>5878938422340028</CardNumber><CardType>Error</CardType><Error>Credit card type invalid</Error></row></root>");
		
		Assert.assertEquals(outJSONFileLines.size(), 1);
		Assert.assertEquals(outJSONFileLines.get(0),
				"[{ \"CardNumber\" : 5410000000000000, \"CardType\" : \"Master\", \"Error\" : \"Valid\" },{ \"CardNumber\" : 4120000000000, \"CardType\" : \"Visa\", \"Error\" : \"Valid\" },{ \"CardNumber\" : 5878938422340028, \"CardType\" : \"Error\", \"Error\" : \"Credit card type invalid\" }]");
		
		
		Assert.assertEquals(outCSVFileLines.size(), 4);
		Assert.assertEquals(outCSVFileLines.get(0), "CardNumber,CardType,Error");
		Assert.assertEquals(outCSVFileLines.get(1), "5410000000000000,Master,Valid");
		Assert.assertEquals(outCSVFileLines.get(2), "4120000000000,Visa,Valid");
		Assert.assertEquals(outCSVFileLines.get(3), "5878938422340028,Error,Credit card type invalid");
		System.out.println(outXMLFileLines.get(0));
		System.out.println(outJSONFileLines.get(0));
		
	}

}
