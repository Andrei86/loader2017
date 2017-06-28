package com.shalkevich.andrei.task1.http_loader;

import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLRdr {
	
	public HashMap<String, String> map = new HashMap<>();
	
	String key;
	String value;

	public HashMap read(String file) {

		try {

			SAXParserFactory factory = SAXParserFactory.newInstance(); // создаем экземпляр фабрики
			SAXParser saxParser = factory.newSAXParser(); // создаем парсер

			DefaultHandler handler = new DefaultHandler() { // создаем обработчика и переопределяем основные методы

			boolean bUrl = false;
			boolean bFileName = false;

			public void startElement(String uri, String localName,String qName,
		                Attributes attributes) throws SAXException {
				

				if (qName.equalsIgnoreCase("url")) {
					bUrl = true;
				}

				if (qName.equalsIgnoreCase("fileName")) {
					bFileName = true;
				}

			}

			public void endElement(String uri, String localName,
				String qName) throws SAXException {

				bUrl = false;
				bFileName = false;

			}

			public void characters(char ch[], int start, int length) throws SAXException {

				if (bUrl) {
					//System.out.println("URL : " + new String(ch, start, length));
					key = new String(ch, start, length);
					//bUrl = false;
				}

				if (bFileName) {
					//System.out.println("FileName : " + new String(ch, start, length));
					value = new String(ch, start, length);
					
					map.put(key, value);
					
					//bFileName = false;
				}
				
				//map.put(key, value);
				

			}

		     };

		       saxParser.parse(file, handler);

		     } catch (Exception e) {
		       e.printStackTrace();
		     }
		
		return map;

		   }

}
