package com.kh.spring.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * * Lombok(롬복)
 * 1. 라이브러리 다운 후 적용(Maven pom.xml)
 * 2. 다운로드된 jar 찾아서 설치 (작업할 IDE 선택해서 설치)
 * 3. IDE 재실행
 * 
 */

@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 전체매개변수 생성자
@Setter // setter 메소드
@Getter // getter 메소드
@ToString // toString 메소드

public class Member {
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String gender;
	// private int age;
	private String age;
	private String phone;
	private String address;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
	
	// private String uName; => userName
	// 단, 롬복을 쓸때는 필드명 작성시 적어도 소문자 두글자 이상으로 시작할 것
}
