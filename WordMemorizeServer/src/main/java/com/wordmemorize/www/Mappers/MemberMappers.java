package com.wordmemorize.www.Mappers;

import com.wordmemorize.www.VO.Member;

public interface MemberMappers {
	
	public int insertMember(Member m);
	
	public String searchMember(String id);
	
	public Member loginMember(Member m);
}
