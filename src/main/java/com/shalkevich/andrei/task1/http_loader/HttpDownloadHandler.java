package com.shalkevich.andrei.task1.http_loader;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpDownloadHandler {
	
	private static final String urlParam = "-l";
	
	private static final String pathParam = "-p";
	
	private static final String nameParam = "-n";
	
	private static final String listFileParam = "-f";
	
	private static final String threadParam = "-t";
	
	static HashMap<String, String> valuesMap = new HashMap<String, String>();
	

	/**
	 * @param args
	 * 
	 * Class for download process
	 */
	public static void main(String[] args) {
		
		String paramString = Arrays.toString(args);
		
		String url = "";
		
		String fileName = "";
		
		String path = "";
		
		String listFile = "";
		
		Integer threadNum = 0;
		
		boolean isListFileParam = paramString.contains(listFileParam);
		
		boolean isThreadNumber = paramString.contains(threadParam);
		
		path = args[3];
		
		if(!isListFileParam)
		{
		
		url = args[1];
		fileName = args[5];
		
		// TODO download 1 file from url
		
		//valuesMap.put(url, filename);
		
		HttpDownloader loader = new HttpDownloader(); // Runnable object
		
		loader.setStrURL(url);
		loader.setStrPath(path);
		loader.setFileName(fileName);
		
		Thread t = new Thread((Runnable)loader);
		t.start();
		
		try
		{
		loader.downloadFiles();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		}
		else
		{
			listFile = args[1];
			/*if(isThreadNumber)
				threadNum = Integer.parseInt(args[5]);*/
			
			if(listFile.endsWith(".csv"))
			{
				CSVRdr csvRdr = new CSVRdr();
				 valuesMap = csvRdr.read(listFile);
				 
			}
			else if(listFile.endsWith(".xml"))
			{
				
				XMLRdr xmlRdr = new XMLRdr();
				valuesMap = xmlRdr.read(listFile);
				
			}
			else if(listFile.endsWith(".json"))
			{
				JSONRdr jsonRdr = new JSONRdr();
			}
			
			// Получили карту ссылок - файлов
			if(!isThreadNumber)
			{
				// TODO download files 1 by 1 from map
				
				HttpDownloader loader = new HttpDownloader();
				loader.setStrPath(path);
				
				for(Map.Entry<String, String> mapa: valuesMap.entrySet())
				{
					loader.setStrURL(mapa.getKey());
					loader.setFileName(mapa.getValue());
					
					Thread t = new Thread((Runnable)loader);
					t.start();
					
					try {
						loader.downloadFiles();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
			else
				
				// TODO multithreading - 
				
				threadNum = Integer.parseInt(args[5]); // количество потоков
			
			ExecutorService executor = Executors.newFixedThreadPool(threadNum);

			
			for(Map.Entry<String, String> mapa: valuesMap.entrySet())
			{
				
					HttpMultiDownloader loader = 
							new HttpMultiDownloader(mapa.getKey(), mapa.getValue(), path);
					
					executor.execute(loader);

					
			}			
			
		}

	}

}
