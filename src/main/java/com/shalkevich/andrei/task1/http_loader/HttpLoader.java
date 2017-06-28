package com.shalkevich.andrei.task1.http_loader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpLoader {

	private String urlStr = "";
	private String savePath = "";
	private String fileName = "";

	public String getUrlStr() {
		return urlStr;
	}

	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public static void downloadFile(String urlStr, String savePath, String fileName) throws IOException {

		URL url = new URL(urlStr); // MalformedURLException

		HttpURLConnection con = (HttpURLConnection) url.openConnection(); // IOException

		con.connect(); // IOException

		if (con.getResponseCode() == 301) // IOException
			url = new URL(con.getHeaderField("Location")); // MalformedURLException

		try(InputStream in = new BufferedInputStream(url.openStream()); // IOException

		OutputStream out = new FileOutputStream(savePath + "/" + fileName); // FileNotFoundException
		BufferedOutputStream bos = new BufferedOutputStream(out)) // IOException
		{

		byte[] buf = new byte[1024 * 8];
		
		int n = 0;
		
		while (-1 != (n = in.read(buf))) { // IOException
			//System.out.println(n);
			bos.write(buf, 0, n); // IOException
		}

	/*	bos.flush();
		in.close();
		bos.close();*/

	}
	}

}
