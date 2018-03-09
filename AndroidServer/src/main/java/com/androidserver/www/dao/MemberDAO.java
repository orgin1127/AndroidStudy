package com.androidserver.www.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.androidserver.www.Member;

/**
 * 게시판 관련 DAO
 */
@Repository
public class MemberDAO {
	@Autowired
	SqlSession sqlSession;
	
	
	public int insertMember(Member member) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		int result = 0;
		try {
			result = mapper.insertMember(member);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public ArrayList<Member> selectMember(){
		ArrayList<Member> result = new ArrayList<>();
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		try{
			result = mapper.selectMember();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

}
