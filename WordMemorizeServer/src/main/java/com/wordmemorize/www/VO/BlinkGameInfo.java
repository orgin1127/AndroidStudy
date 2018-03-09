package com.wordmemorize.www.VO;

public class BlinkGameInfo extends Word {
	private int blinkGameInfoNumber;
    private String incollectBGameWord;
    private String memberID;
    private int incollectingCount;
	public int getBlinkGameInfoNumber() {
		return blinkGameInfoNumber;
	}
	public void setBlinkGameInfoNumber(int blinkGameInfoNumber) {
		this.blinkGameInfoNumber = blinkGameInfoNumber;
	}
	public String getIncollectBGameWord() {
		return incollectBGameWord;
	}
	public void setIncollectBGameWord(String incollectBGameWord) {
		this.incollectBGameWord = incollectBGameWord;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public int getIncollectingCount() {
		return incollectingCount;
	}
	public void setIncollectingCount(int incollectingCount) {
		this.incollectingCount = incollectingCount;
	}
	public BlinkGameInfo() {
		super();
	}
	public BlinkGameInfo(int blinkGameInfoNumber, String incollectBGameWord, String memberID, int incollectingCount) {
		super();
		this.blinkGameInfoNumber = blinkGameInfoNumber;
		this.incollectBGameWord = incollectBGameWord;
		this.memberID = memberID;
		this.incollectingCount = incollectingCount;
	}
	public BlinkGameInfo(String word, String yomigana, String meaning, String wordLevels, String linkAddress
						, int blinkGameInfoNumber, String incollectBGameWord, String memberID, int incollectingCount) {
		super(word, yomigana, meaning, wordLevels, linkAddress);
		this.blinkGameInfoNumber = blinkGameInfoNumber;
		this.incollectBGameWord = incollectBGameWord;
		this.memberID = memberID;
		this.incollectingCount = incollectingCount;
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
		result = prime * result + blinkGameInfoNumber;
		result = prime * result + ((incollectBGameWord == null) ? 0 : incollectBGameWord.hashCode());
		result = prime * result + incollectingCount;
		result = prime * result + ((memberID == null) ? 0 : memberID.hashCode());
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
		BlinkGameInfo other = (BlinkGameInfo) obj;
		if (blinkGameInfoNumber != other.blinkGameInfoNumber)
			return false;
		if (incollectBGameWord == null) {
			if (other.incollectBGameWord != null)
				return false;
		} else if (!incollectBGameWord.equals(other.incollectBGameWord))
			return false;
		if (incollectingCount != other.incollectingCount)
			return false;
		if (memberID == null) {
			if (other.memberID != null)
				return false;
		} else if (!memberID.equals(other.memberID))
			return false;
		return true;
	}
}
