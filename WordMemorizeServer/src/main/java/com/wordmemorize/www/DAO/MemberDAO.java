package com.wordmemorize.www.DAO;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wordmemorize.www.Mappers.MemberMappers;
import com.wordmemorize.www.VO.Member;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

@Repository
public class MemberDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);
	
	@Autowired
	SqlSession session;
	
	//회원가입
	public int insertMember(Member m) {
		int result = 0;
		MemberMappers mm = session.getMapper(MemberMappers.class);
		try {
			result = mm.insertMember(m);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//회원검색 (ID 중복검사 대응)
	public String searchMember(String id) {
		String searchResult = "";
		MemberMappers mm = session.getMapper(MemberMappers.class);
		if( mm.searchMember(id) == null) {
			searchResult = "";
		}
		else {
			searchResult = mm.searchMember(id);
		}
		return searchResult;
	}
	
	//login
	public Member loginMember(Member m) {
		MemberMappers mm = session.getMapper(MemberMappers.class);
		Member loginResult = mm.loginMember(m);
		return loginResult;
	}
}
