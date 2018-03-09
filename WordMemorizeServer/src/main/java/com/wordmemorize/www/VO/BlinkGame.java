package com.wordmemorize.www.VO;

public class BlinkGame extends Word {
	 private int blinkGameNumber;
     private String memberID;
     private int highScore;
     private int maxCombo;
     private int playCount;
	public int getBlinkGameNumber() {
		return blinkGameNumber;
	}
	public void setBlinkGameNumber(int blinkGameNumber) {
		this.blinkGameNumber = blinkGameNumber;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public int getHighScore() {
		return highScore;
	}
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	public int getMaxCombo() {
		return maxCombo;
	}
	public void setMaxCombo(int maxCombo) {
		this.maxCombo = maxCombo;
	}
	public int getPlayCount() {
		return playCount;
	}
	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}
	public BlinkGame(int blinkGameNumber, String memberID, int highScore, int maxCombo, int playCount) {
		super();
		this.blinkGameNumber = blinkGameNumber;
		this.memberID = memberID;
		this.highScore = highScore;
		this.maxCombo = maxCombo;
		this.playCount = playCount;
	}
	public BlinkGame(String word, String yomigana, String meaning, String wordLevels, String linkAddress
					, int blinkGameNumber, String memberID, int highScore, int maxCombo, int playCount) {
		super(word, yomigana, meaning, wordLevels, linkAddress);
		this.blinkGameNumber = blinkGameNumber;
		this.memberID = memberID;
		this.highScore = highScore;
		this.maxCombo = maxCombo;
		this.playCount = playCount;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + blinkGameNumber;
		result = prime * result + highScore;
		result = prime * result + maxCombo;
		result = prime * result + ((memberID == null) ? 0 : memberID.hashCode());
		result = prime * result + playCount;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlinkGame other = (BlinkGame) obj;
		if (blinkGameNumber != other.blinkGameNumber)
			return false;
		if (highScore != other.highScore)
			return false;
		if (maxCombo != other.maxCombo)
			return false;
		if (memberID == null) {
			if (other.memberID != null)
				return false;
		} else if (!memberID.equals(other.memberID))
			return false;
		if (playCount != other.playCount)
			return false;
		return true;
	}
    
}
