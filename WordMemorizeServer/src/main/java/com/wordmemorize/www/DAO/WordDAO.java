package com.wordmemorize.www.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wordmemorize.www.Mappers.WordMapper;
import com.wordmemorize.www.VO.CustomWord;
import com.wordmemorize.www.VO.Word;

@Repository
public class WordDAO {
	
	private Logger logger = LoggerFactory.getLogger(WordDAO.class);
	
	@Autowired
	SqlSession session;
	
	public ArrayList<Word> getWordList(String wordLevel) {
		WordMapper wm = session.getMapper(WordMapper.class);
		logger.debug(wordLevel);
		ArrayList<Word> wordList = wm.getWordList(wordLevel);
		logger.debug(wordLevel.length()+"");
		return wordList;
	}
	
	public ArrayList<CustomWord> getCustomWordList(CustomWord cw){
		WordMapper wm = session.getMapper(WordMapper.class);
		ArrayList<CustomWord> customWordList = wm.getCustomWordList(cw);
		return customWordList;
	}
	
	public ArrayList<Word> getSearchWordList(HashMap<String, String>searchMap) {
		WordMapper wm = session.getMapper(WordMapper.class);
		ArrayList<Word> wordList = wm.getSearchWord(searchMap);
		return wordList;
	}
	
	public int insertCustomWord(CustomWord cw) {
		WordMapper wm = session.getMapper(WordMapper.class);
		return wm.insertCustomWord(cw);
	}
	
	public int deleteCustomWord(CustomWord cw) {
		WordMapper wm = session.getMapper(WordMapper.class);
		return wm.deleteCustomWord(cw);
	}
	
	public int searchDuplicateCustomWord(CustomWord cw) {
		WordMapper wm = session.getMapper(WordMapper.class);
		int i = wm.searchDuplicateCustomWord(cw);
		
		return i;
	}
}
