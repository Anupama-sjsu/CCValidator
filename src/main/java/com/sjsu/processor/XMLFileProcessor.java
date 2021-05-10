package com.sjsu.processor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sjsu.dto.CreditCard;
import com.sjsu.factory.CreditCardFactory;

public class XMLFileProcessor extends FileProcessor {
	private CreditCardFactory cardFactory;

	public XMLFileProcessor(String InputFileName, String OutputFileName) {
		super(InputFileName, OutputFileName);
		cardFactory = new CreditCardFactory();
	}

	@Override
	public void readCreditCardRecords(List<CreditCard> cards) {
		/* Get all the lines from the input file */
		getXmlObjectsFromFile(cards);
	}

	@Override
	public void writeCreditCardRecords(List<CreditCard> cards) {
		try {
			FileWriter outFile = new FileWriter(outFileName);
			/* Write the title here */
			outFile.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>");
			Iterator<CreditCard> cardIterator = cards.iterator();
			while (cardIterator.hasNext()) {
				CreditCard card = cardIterator.next();
				String cardNumber = card.getNumber();
				String cardType = card.getType();
				String cardErrorReason = card.getErrorReason();

				outFile.write("<row><CardNumber>" + cardNumber + "</CardNumber><CardType>" + cardType + "</CardType><Error>" + cardErrorReason + "</Error></row>");
			}
			outFile.write("</root>");
			outFile.close();
		} catch (Exception e) {
			System.out.println("Error: Exception in writing to file");
		}
	}

	private void getXmlObjectsFromFile(List<CreditCard> cards) {

		// Instantiate the Factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			// optional, but recommended
			// process XML securely, avoid attacks like XML External Entities (XXE)
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

			// parse XML file
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(new File(inFileName));

			// optional, but recommended
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			NodeList list = doc.getElementsByTagName("row");

			for (int temp = 0; temp < list.getLength(); temp++) {

				Node node = list.item(temp);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) node;

					// get text
					String firstname = element.getElementsByTagName("CardNumber").item(0).getTextContent();
					String lastname = element.getElementsByTagName("ExpirationDate").item(0).getTextContent();
					String nickname = element.getElementsByTagName("NameOfCardholder").item(0).getTextContent();

					CreditCard card = cardFactory.getCreditCard(firstname, lastname, nickname);
					cards.add(card);
				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

}