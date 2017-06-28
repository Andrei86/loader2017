package com.shalkevich.andrei.task1.http_loader;

import java.io.IOException;

public class HttpMultiDownloader implements Runnable{
	
	private String strURL;
	private String strPath;
	private String fileName;
	
	/*Thread t;*/
	
	public HttpMultiDownloader(String strURL, String strPath, String fileName)
	{
		this.strURL = strURL;
		this.strPath = strPath;
		this.fileName = fileName;
		
		/*Thread t = new Thread(this);
		t.start();*/
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		HttpDownloader loader = new HttpDownloader();
		
		loader.setStrURL(strURL);
		loader.setStrURL(strPath);
		loader.setStrURL(fileName);
		
		try
		{
		loader.downloadFiles();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}

}
