package com.wordmemorize.www.Controllers;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.wordmemorize.www.DAO.WordDAO;
import com.wordmemorize.www.VO.BlinkGameInfo;
import com.wordmemorize.www.VO.CustomWord;
import com.wordmemorize.www.VO.Word;

@Controller
public class WordController {
	
	private Logger logger = LoggerFactory.getLogger(WordController.class);
	
	@Autowired
	WordDAO wDAO;
	
	@ResponseBody
	@RequestMapping(value="viewWord", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public String getWordList (String wordLevel
							,@RequestParam(value="memberID", defaultValue="") String memberID) {
		ArrayList<Word> wordList;
		ArrayList<CustomWord> customWordList;
		ArrayList<BlinkGameInfo> incollectWordList;
		String gsonWordList = "";
		logger.debug(wordLevel+", check");
		logger.debug(memberID);
		if(wordLevel.equals("customWord") && !memberID.isEmpty()){
			CustomWord cw = new CustomWord(memberID);
			customWordList = wDAO.getCustomWordList(cw);
			Gson gson = new Gson();
			gsonWordList = gson.toJson(customWordList);
		}
		else if(wordLevel.equals("incollectWord") && !memberID.isEmpty()){
			incollectWordList = wDAO.getIncorllectWordList(memberID);
			Gson gson = new Gson();
			gsonWordList = gson.toJson(incollectWordList);
		}
		else {
			wordList = wDAO.getWordList(wordLevel);
			Gson gson = new Gson();
			gsonWordList = gson.toJson(wordList);
			logger.debug(wordList.get(0).toString());
		}
		
		return gsonWordList;
	}
	
	@ResponseBody
	@RequestMapping(value="searchWord", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public String searchWord(@RequestParam(value="searchType", defaultValue="") String searchType, 
							@RequestParam(value="searchText", defaultValue="") String searchText) {
		ArrayList<Word> wordList;
		HashMap<String, String> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchText", searchText);
		String gsonWordList = "";
		logger.debug(searchType+", "+searchText);
		wordList = wDAO.getSearchWordList(searchMap);
		if(wordList.isEmpty()) {
			logger.debug("fail to get wordList");
			gsonWordList = "FAIL";
		}
		else {			
			Gson gson = new Gson();
			gsonWordList = gson.toJson(wordList);
		}
		return gsonWordList;
	}
	
	@ResponseBody
	@RequestMapping(value="insertCustomWord", method = RequestMethod.POST, produces="application/text; charset=utf8")
	public String insertCustomWord(CustomWord cw) {
		String result = "";
		logger.debug(cw.toString());
		int check = wDAO.searchDuplicateCustomWord(cw);
	
		
		if (check==1) {
			result = "단어 중복입니다.";
			return result;
		}
		else {
			wDAO.insertCustomWord(cw);
			result = "단어 등록 성공";
			return result;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="deleteCustomWord", method = RequestMethod.POST, produces="application/text; charset=utf8")
	public String deleteCustomWord(CustomWord cw) {
		String result = "";
		logger.debug(cw.toString());
		int check = wDAO.deleteCustomWord(cw);
		if(check == 1) {
			result = "단어 삭제 성공";
			return result;
		}
		else {
			result = "단어 삭제 실패";
			return result;
		}
	}
}
