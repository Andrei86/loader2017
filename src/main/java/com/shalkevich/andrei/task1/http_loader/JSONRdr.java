package com.shalkevich.andrei.task1.http_loader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONRdr {

	public HashMap<String, String> map = new HashMap<>();
	
	public HashMap read(String file) {
		
		 JSONParser parser = new JSONParser();

	        try {

	            Object obj = parser.parse(new FileReader(file)); // парсим файл в объект java

	            JSONObject jsonObject = (JSONObject) obj; // приводим к типу json - список ключ-значение
	            //System.out.println(jsonObject);
	            
	            //System.out.println(jsonObject.toJSONString());

	            map = jsonObject;
	            
	            /*for (Map.Entry<String, String> entry : hMap.entrySet())
	            	System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());*/
	            
	            //return map;
	            
	         

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        
	        return map;

	}

}
