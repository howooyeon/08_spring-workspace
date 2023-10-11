package com.kh.ajax.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.ajax.model.vo.Member;

@Controller
public class AjaxController {
	
	/*
	1. HttpServletResponse 객체로 응답데이터 응답하기 (기존의 jsp, servlet 때 했던 Stream 이용한 방식)
	@RequestMapping("ajax1.do")
	public void ajaxMethod1(String name, int age, HttpServletResponse response) throws IOException {
		System.out.println(name);
		System.out.println(age);
		
		// 요청 처리를 위해 서비스 호출
		
		// 요청 처리가 다 됐다는 가정하에 요청한 그 페이지에 응답할 데이터가 있을 경우
		String responseData = "응답문자열 :" + name + "은(는)" + age + "살 입니다.";
		
		  response.setContentType("text/html; charset=UTF-8");
		  response.getWriter().print(responseData);
		 
		
		System.out.println(responseData); 
	}
	*/
	
	/*
	 * 2. 응답할 데이터로 문자열로 리턴
	 * 	=> HttpServletResponse를 안쓸 수 없음
	 * 	   단, 문자열 리턴하면 원래는 포워딩 방식이었음 => 응답뷰 인식해서 해당 뷰 페이지를 찾고있음
	 * 	   따라서 내가 리턴하는 문자열이 응답뷰가 아니라 응답데이터야 라는걸 선언하는
	 * 	   어노테이션 @ResponseBody를 붙여야됨
	 */
	
	/*
	@ResponseBody
	@RequestMapping(value="ajax1.do", produces = "text/html; charset=UTF-8")
	public String ajaxMethod1(String name, int age) {
		String responseData = "응답문자열 : " + name + "은(는) " + age + "살 입니다.";
		return responseData;
	}
	*/
	
	// 다수의 응답데이터가 있을 경우??
	/*
	@RequestMapping("ajax1.do")
	public void ajaxMethod1(String name, int age, HttpServletResponse response) throws IOException {
		// 요청처리가 다 됐다는 가정하에 데이터 응답
		
		//response.setContentType("text/html; charset=UTF-8");
		//response.getWriter().print(name);
		//response.getWriter().print(age);
		// 연이어서 출력하는 데이터가 하나의 문자열로 연이어져 있음
		
		
		// JSON(JavaScript Object Notation) 형태로 담아서 응답
		// JSONArray => [값, 값, 값, ...] 		=> 자바에서의 ArrayList와 유사 (list에 추가할때는 add)
		// JSONObject => {키:값, 키:값, 키:값}		=> 자바에서의 HashMap과 유사 (map에 추가할 때는 put)
		
		// 첫번째 방법. JSONArray로 담아서 응답
		
		//JSONArray jArr = new JSONArray();
		//jArr.add(name); // ["차은우"]
		//jArr.add(age); // ["차은우" , 20]
		
		
		// 두번쨰 방법. JSONObject로 담아서 응답
		//JSONObject jobj = new JSONObject(); // {}
		//jobj.put("name", name); // {name:'차은우'}
		//jobj.put("age", age); // {name:'차은우', age:20}
		
		
		//response.setContentType("application/json; charset=UTF-8");
		//response.getWriter().print(jobj);
	}
	*/
	
	@ResponseBody
	@RequestMapping(value="ajax1.do", produces = "application/json; charset=UTF-8")
	public String ajaxMethod1(String name, int age) {
		
		JSONObject jobj = new JSONObject(); // {}
		jobj.put("name", name); // {name:'차은우'}
		jobj.put("age", age); // {name:'차은우', age:10}
		
		return jobj.toJSONString(); // "{name:'차은우', age:10}"
	}

	/*
	@ResponseBody
	@RequestMapping(value="ajax2.do", produces = "application/json; charset=UTF-8")
	public String ajaxMethod2(int num) {
		// Member m = mService.selectMember(num);
		Member m = new Member("user01", "pass01", "차은우", 20, "01025862357");
		
		// JSON형태로 만들어서 응답
		JSONObject jobj = new JSONObject(); // {}
		jobj.put("userId", m.getUserId());
		jobj.put("userName", m.getUserName());
		jobj.put("age", m.getAge());
		jobj.put("phone", m.getPhone()); // 대신 해쉬맵이라 순서 보장x
		
		return jobj.toJSONString();
	}
	*/
	@ResponseBody
	@RequestMapping(value="ajax2.do", produces = "application/json; charset=UTF-8")
	public String ajaxMethod2(int num) {
		// Member m = mService.selectMember(num);
		Member m = new Member("user01", "pass01", "차은우", 20, "01025862357");
		
		return new Gson().toJson(m); // {userId:'user01', userPwd:'pass01'}
	}
	
	@ResponseBody
	@RequestMapping(value="ajax3.do", produces = "application/json; charset=UTF-8")
	public String ajaxMethod3() {
		// ArrayList<Member> list = mService.selectList();
		
		ArrayList<Member> list = new ArrayList<Member>();
		list.add(new Member("user01", "pass01", "차은우", 22, "01055556666")); // [{은우객체}]
		list.add(new Member("user02", "pass02", "작은우영", 30, "01022225555")); // [{은우객체, 우영객체}]
		list.add(new Member("user03", "pass03", "어먼상", 22, "01066667777")); // [{은우객체, 우영객체, 원상객체}]
		
		return new Gson().toJson(list); // [{},{},{}]
	
	}
	
	
}
