package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {
	public static final String serviceKey = "FEoUH%2FWcbn1RevPi7Q8a49EnmCBF2OgVP5jlXlFH%2BObR3I2mOq3%2BiuJPe0ePQmUW5z4N6hykMyXJNas2i7u3Ug%3D%3D";

	@ResponseBody
	@RequestMapping(value = "airplane.do", produces = "application/json; charset=UTF-8")
	public String flight() throws IOException {
		
		String url = "https://apis.data.go.kr/B551177/AviationStatsByAirline/getTotalNumberOfFlight";
		url += "?serviceKey=" + serviceKey;
		url += "&numOfRows=10";
		url += "&pageNo=1";
		url += "&type=json";
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = "";
		String line; 
		
		while((line = br.readLine()) != null) {
			responseText += line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		return responseText;
	}
	
	@ResponseBody
	@RequestMapping(value="disaster.do", produces="text/xml; charset=UTF-8")
	public String disasterShelter() throws IOException { 
		String url ="https://apis.data.go.kr/1741000/EarthquakeIndoors2/getEarthquakeIndoors1List";
		url += "?serviceKey=" + serviceKey;
		url += "&pageNo=1";
		url += "&type=xml";
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = "";
		String line;
		
		while((line = br.readLine()) != null) {
			responseText += line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		// System.out.println(responseText);
		
		return responseText;
		
	}
	
}
