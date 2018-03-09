package com.wordmemorize.www.Validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wordmemorize.www.DAO.MemberDAO;
import com.wordmemorize.www.VO.Member;

@Repository
public class MemberValidator {
	
	@Autowired MemberDAO mDAO;
	
	public String validator(Member m, String password2) {
		String result = "";
		
		//ID 유효성 검사
		if(m.getMemberID() != null) {
			String searchResult = mDAO.searchMember(m.getMemberID());
			if(!Pattern.matches("^[a-zA-Z0-9]{4,10}$", m.getMemberID())) {
				result = "ID는 영문, 숫자로 4~10자리를 입력하여 주세요";
				return result;
			}
			else if(!searchResult.equals("")){
				result = "ID가 중복입니다. duplicate";
				return result;
			}
		}
		
		//Password 유효성 검사
		if(m.getPassword() != null) {
			if(!Pattern.matches("^[a-zA-Z0-9]{5,12}$", m.getPassword())) {
				result = "Password는 영문, 숫자로 5~12자리를 입력하여 주세요";
				return result;
			}
		}
		
		//Password 재입력 유효성 검사
		if(!password2.equals("")){
			if(!m.getPassword().equals(password2)){
				result = "password 재입력이 틀립니다. password2 invalidate";
				return result;
			}
		}
		return result;
	}

}
