package com.kh.opendata.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.opendata.model.vo.AirVO;


public class AirPollutionJavaApp {
	// 발급받은 인증키 정보 변수에 담아두기
	public static final String serviceKey = "FEoUH%2FWcbn1RevPi7Q8a49EnmCBF2OgVP5jlXlFH%2BObR3I2mOq3%2BiuJPe0ePQmUW5z4N6hykMyXJNas2i7u3Ug%3D%3D";
	
	public static void main(String[] args) throws IOException {
		
		// OpenAPI 서버로 요청하고자 하는 URL 만들기
		String url = "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		// url += "?serviceKey=서비스키"; // 서비스키가 제대로 부여되지 않았을 경우 => SERVICE_IS_NOT_REGISTERED_ERROR
		url += "?serviceKey=" + serviceKey;
		url += "&sidoName=" + URLEncoder.encode("서울", "UTF-8"); // 요청시 전달값 중 한글이 있을 경우 encoding 해야됨!
		url += "&returnType=json";
		
		// System.out.println(url);
		
		// ** HttpURLConnection 객체를 활용해서 OpenAPI 요청 절차 **
		// 1. 요청하고자 하는 url 전달하면서 java.net.URL 객체 생성
		URL requestUrl = new URL(url);
		
		// 2. 1번 과정으로 생성된 URL객체를 가지고 HttpURLConnection 객체 생성
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		
		// 3. 요청에 필요한 Header 설정하기
		urlConnection.setRequestMethod("GET");
		
		// 4. 해당 OpenAPI 서버로 요청 보낸 후 입력 스트림을 통해 응답 데이터 읽어들이기
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = "";
		String line;
		
		while((line = br.readLine()) != null) {
			// System.out.println(line);
			responseText += line;
		}
		
		System.out.println(responseText);
		/*
		 * {
			"response":
				{
				"body":
					{
				"totalCount":40,
				"items":
					[	
						{
						"so2Grade":"1",
						"coFlag":null,
						"khaiValue":"75",
						"so2Value":"0.003",
						"coValue":"0.4",
						"pm10Flag":null,
						"o3Grade":"2",
						"pm10Value":"26",
						"khaiGrade":"2",
						"sidoName":"서울",
						"no2Flag":null,
						"no2Grade":"1",
						"o3Flag":null,
						"so2Flag":null,
						"dataTime":"2023-10-12 14:00",
						"coGrade":"1",
						"no2Value":"0.017",
						"stationName":"중구",
						"pm10Grade":"1",
						"o3Value":"0.060"
						},
							
		 */
		
		// JSONObject, JSONArray, JSONElement 이용해서 파싱할 수 있음(gson 라이브러리) => 내가 원하는 데이터만을 추출할 수 있음
		// 각각의 item 정보를 => AirVo 객체에 담고 => ArrayList에 차곡차곡 쌓기
		JsonObject totalObj = JsonParser.parseString(responseText).getAsJsonObject();
		JsonObject responseObj = totalObj.getAsJsonObject("response"); // response 속성 접근 => {} JsonObject
		JsonObject bodyObj = responseObj.getAsJsonObject("body"); // body 속성접근 => {} JsonObject
		// System.out.println(bodyObj);
		
		int totalCount = bodyObj.get("totalCount").getAsInt(); // totalCount 속성 접근 => 40 int
		JsonArray itemArr = bodyObj.getAsJsonArray("items"); // items 속성 접근
		
		ArrayList<AirVO> list = new ArrayList<AirVO>(); // []
		
		for(int i = 0; i <itemArr.size(); i++) {
			JsonObject item = itemArr.get(i).getAsJsonObject();
			
			AirVO air = new AirVO();
			air.setStationName(item.get("stationName").getAsString());
			air.setDataTime(item.get("dataTime").getAsString());
			air.setKhaiValue(item.get("khaiValue").getAsString());
			air.setPm10Value(item.get("pm10Value").getAsString());
			air.setSo2Value(item.get("so2Value").getAsString());
			air.setCoValue(item.get("coValue").getAsString());
			air.setNo2Value(item.get("no2Value").getAsString());
			air.setO3Value(item.get("o3Value").getAsString());
			
			list.add(air);
		}
		
		System.out.println(list);
		
		for(AirVO a : list) {
			System.out.println(a);
		}
		
				
		// 5. 다 사용한 스트림 반납
		br.close();
		urlConnection.disconnect();
		
	}
}
