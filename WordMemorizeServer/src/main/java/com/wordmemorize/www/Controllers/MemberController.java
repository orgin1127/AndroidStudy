package com.wordmemorize.www.Controllers;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.wordmemorize.www.DAO.MemberDAO;
import com.wordmemorize.www.VO.Member;
import com.wordmemorize.www.Validator.MemberValidator;

@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberDAO mDAO;
	@Autowired
	MemberValidator mVali;
	
	@ResponseBody
	@RequestMapping(value = "insertMember", method = RequestMethod.POST, produces="application/text; charset=utf8")
	public String insertMember(Member m, String password2) {
		
		logger.debug(m.toString() + password2);
		int result = 0;
		String msg = "";
		if(!mVali.validator(m, password2).equals("")){
			msg = mVali.validator(m, password2);
			return msg;
		}
		result = mDAO.insertMember(m);
		if(result == 1){
			msg = "회원가입 성공";
		}else{
			msg = "회원가입 실패";
		}
		
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(Member m, HttpSession session) {
		logger.debug(m.toString());
		String loginMember = "";
		Member loginResult = mDAO.loginMember(m);
		if(loginResult != null) {
			Gson gson = new Gson();
			loginMember = gson.toJson(loginResult);
		}
		return loginMember;
	}
}
