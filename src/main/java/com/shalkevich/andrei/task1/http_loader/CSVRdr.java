package com.shalkevich.andrei.task1.http_loader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CSVRdr {
	
	public HashMap<String, String> map = new HashMap<>();
	
/*	public HashMap<String, String> getMap() {
		return map;
	}

	public void setMap(HashMap<String, String> map) {
		this.map = map;
	}*/



	public HashMap read(String file)
	{
		
	//String file = "files/in/csv.csv";
	
	try
	{
	
	CSVReader reader = new CSVReader(new FileReader(file));
	
	String [] line = null;
	
	while((line = reader.readNext()) != null)
	{
		
		map.put(line[0],line[1]);
	
	}
	
	
	}
	catch (FileNotFoundException ex)
	{
		ex.printStackTrace();
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
	
	return map;
	
	}

}
