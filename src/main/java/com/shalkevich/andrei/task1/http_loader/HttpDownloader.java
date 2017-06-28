package com.shalkevich.andrei.task1.http_loader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;



/**
 * @author Andrei Shalkevich
 * 
 * Class for download file with downloadFiles() method
 * 
 *  from given URL to given PATH with specified filename
 *
 */

public class HttpDownloader {
	
	private String strURL = "";
	private String strPath = "";
	private String fileName = "";
	
	
	public String getStrURL() {
		return strURL;
	}

	public void setStrURL(String strURL) {
		this.strURL = strURL;
	}

	public String getStrPath() {
		return strPath;
	}

	public void setStrPath(String strPath) {
		this.strPath = strPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	
	
	public void downloadFiles() throws IOException
    {
   
             URL url = new URL(strURL);
             
             HttpURLConnection con = (HttpURLConnection)url.openConnection();
             
             con.connect();
             
            if(con.getResponseCode() == 301)
            	url = new URL(con.getHeaderField("Location")); // redirection
             
             InputStream in = new BufferedInputStream(url.openStream());
             
             /*String fullPath = strURL.substring(strURL.lastIndexOf("/") + 1,
                     strURL.length());*/
             
             OutputStream writer = new FileOutputStream(strPath + "/" + fileName);
             BufferedOutputStream bos = new BufferedOutputStream(writer);
             
             /*long l1 = System.currentTimeMillis();*/
             
            byte[] buf = new byte[1024*8];
 	        int n = 0;
 	        while (-1!=(n=in.read(buf))) {
 	            //System.out.println(n);
 	            bos.write(buf, 0, n);
 	        }
 	        
 	       /*long l2 = System.currentTimeMillis();
 	      long dif = l2 - l1;
          
          System.out.println(dif);*/
 	       
 	        bos.flush();
 	        in.close();
 	        bos.close();
           
    }

}
