package com.androidserver.www.dao;

import java.util.ArrayList;

import com.androidserver.www.Member;

/**
 * 게시판 관련 Mybatis 사용 메서드
 */
public interface MemberMapper {
	public int insertMember(Member member);
	public ArrayList<Member> selectMember();

}
