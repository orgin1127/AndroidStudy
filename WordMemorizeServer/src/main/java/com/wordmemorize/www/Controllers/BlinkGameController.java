package com.wordmemorize.www.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.wordmemorize.www.DAO.BlinkGameDAO;
import com.wordmemorize.www.VO.BlinkGame;
import com.wordmemorize.www.VO.BlinkGameInfo;

@Controller
public class BlinkGameController {
	
	private static Logger logger = LoggerFactory.getLogger(BlinkGameController.class);
	
	@Autowired
	BlinkGameDAO bDAO;
	
	@ResponseBody
	@RequestMapping(value="insertBlinkGame", method=RequestMethod.POST,produces="application/text; charset=utf8")
	public String insertBlinkGame(BlinkGame bg){
		String result = "";
		String memberID = bg.getMemberID();
		BlinkGame selectBG = bDAO.searchBlinkGameMember(memberID);
		if(selectBG == null) {			
			int check = bDAO.insertBlinkGame(bg);
			if(check == 1) {
				Gson gson = new Gson();
				result = gson.toJson(bDAO.searchBlinkGameMember(memberID));
			}
			else {
				result = "등록실패";
			}
		}
		else {
			Gson gson = new Gson();
			result = gson.toJson(selectBG);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="insertAndUpdateIncollectBlinkGameWord", method=RequestMethod.POST,produces="application/text; charset=utf8")
	public String insertAndUpdateIncollectBlinkGameWord(BlinkGameInfo bgi) {
		String result = "";
		BlinkGameInfo bgi2 = bDAO.searchBlinkGameInfo(bgi);
		if(bgi2 != null) {
			bgi2.setIncollectingCount(bgi2.getIncollectingCount()+1);
			int check = bDAO.updateIncollectGameWord(bgi2);
			if (check ==1){
				result = "업데이트 성공";
			}
			else {
				result = "업데이트 실패";
			}
		}
		else if(bgi2 == null) {
			int check = bDAO.insertIncollectGameWord(bgi);
			if (check ==1){
				result = "업데이트 성공";
			}
			else {
				result = "업데이트 실패";
			}
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "updateBlinkGame", method = RequestMethod.POST,produces="application/text; charset=utf8")
	public String updateBlinkGame(BlinkGame bg) {
		String result = "";
		int check = 0;
		check = bDAO.updateBlinkGame(bg);
		if (check == 1){
			result = "업데이트 성공";
		}
		else {
			result = "업데이트 실패";
		}
		return result;
	}
	
	
	
}
