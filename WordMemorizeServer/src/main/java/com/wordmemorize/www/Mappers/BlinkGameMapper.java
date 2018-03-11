package com.wordmemorize.www.Mappers;

import com.wordmemorize.www.VO.BlinkGame;
import com.wordmemorize.www.VO.BlinkGameInfo;

public interface BlinkGameMapper {
	public int insertBlinkGame(BlinkGame bg);
	public int updateBlinkGame(BlinkGame bg);
	public int insertIncollectGameWord(BlinkGameInfo bgi);
	public int updateIncollectGameWord(BlinkGameInfo bgi);
	public BlinkGameInfo searchBlinkGameInfo(BlinkGameInfo bgi);
	public BlinkGame searchBlinkGameMember(String memberID);
}
