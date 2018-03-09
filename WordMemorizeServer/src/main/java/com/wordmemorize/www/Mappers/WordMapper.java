package com.wordmemorize.www.Mappers;

import java.util.ArrayList;
import java.util.HashMap;

import com.wordmemorize.www.VO.CustomWord;
import com.wordmemorize.www.VO.Word;

public interface WordMapper {
	public int insertCustomWord(CustomWord cw);
	public int searchDuplicateCustomWord(CustomWord cw);
	public ArrayList<Word> getWordList(String wordLevel);
	public ArrayList<CustomWord> getCustomWordList(CustomWord cw);
	public ArrayList<Word> getSearchWord(HashMap<String, String>searchMap);
}
