package com.wordmemorize.www.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wordmemorize.www.Mappers.BlinkGameMapper;
import com.wordmemorize.www.VO.BlinkGame;
import com.wordmemorize.www.VO.BlinkGameInfo;

@Repository
public class BlinkGameDAO {
	
	@Autowired
	SqlSession session;
	
	public int insertBlinkGame(BlinkGame bg) {
		BlinkGameMapper bgm = session.getMapper(BlinkGameMapper.class);
		int result = bgm.insertBlinkGame(bg);
		return result;
	}
	
	public int insertIncollectGameWord(BlinkGameInfo bgi){
		BlinkGameMapper bgm = session.getMapper(BlinkGameMapper.class);
		int result = bgm.insertIncollectGameWord(bgi);
		return result;
	}
	
	public BlinkGame searchBlinkGameMember(String memberID) {
		BlinkGameMapper bgm = session.getMapper(BlinkGameMapper.class);
		BlinkGame bg = bgm.searchBlinkGameMember(memberID);
		return bg;
	}
	
	public BlinkGameInfo searchBlinkGameInfo(BlinkGameInfo bgi) {
		BlinkGameMapper bgm = session.getMapper(BlinkGameMapper.class);
		BlinkGameInfo reBGI = bgm.searchBlinkGameInfo(bgi);
		return reBGI;
	}
	
	public int updateBlinkGame(BlinkGame bg) {
		BlinkGameMapper bgm = session.getMapper(BlinkGameMapper.class);
		int result = bgm.updateBlinkGame(bg);
		return result;
	}
	
	public int updateIncollectGameWord(BlinkGameInfo bgi) {
		BlinkGameMapper bgm = session.getMapper(BlinkGameMapper.class);
		int result = bgm.updateIncollectGameWord(bgi);
		return result;
	}
	
}
