package com.kh.spring.member.model.service;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

//@Component
@Service
public class MemberServiceImpl implements MemberService{
	
//	private MemberDao mDao = new MemberDao();
	
	@Autowired
	private MemberDao mDao;
	
	@Autowired
	private SqlSessionTemplate SqlSession;

	@Override
	public Member loginMember(Member m) {
		Member loginMember = mDao.loginMember(SqlSession, m);
		return loginMember;
	}

	@Override
	public int insertMember(Member m) {
		return 0;
	}

	@Override
	public int updateMember(Member m) {
		return 0;
	}

	@Override
	public int deleteMember(String userId) {
		return 0;
	}

	@Override
	public int idCheck(String checkId) {
		return 0;
	}

}
