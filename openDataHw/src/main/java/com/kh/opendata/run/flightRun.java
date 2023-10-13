package com.kh.opendata.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class flightRun {
	public static final String serviceKey = "FEoUH%2FWcbn1RevPi7Q8a49EnmCBF2OgVP5jlXlFH%2BObR3I2mOq3%2BiuJPe0ePQmUW5z4N6hykMyXJNas2i7u3Ug%3D%3D";
	
	public static void main(String[] args) throws IOException {
		
		String url = "https://apis.data.go.kr/B551177/AviationStatsByAirline/getTotalNumberOfFlight";
		url += "?serviceKey=" + serviceKey;
		url += "&from_month=" + URLEncoder.encode("date", "UTF-8");
		url += "&returnType=json";
		
		System.out.println(url);
		
		/*
		 * URL requestUrl = new URL(url);
		 * 
		 * HttpURLConnection urlConnection = (HttpURLConnection)
		 * requestUrl.openConnection();
		 * 
		 * urlConnection.setRequestMethod("GET");
		 * 
		 * BufferedReader br = new BufferedReader(new
		 * InputStreamReader(urlConnection.getInputStream()));
		 * 
		 * String responseText = ""; String line;
		 * 
		 * while((line = br.readLine()) != null) { responseText += line; }
		 * 
		 * System.out.println(responseText);
		 */
		
	}
	
}
