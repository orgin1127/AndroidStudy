package com.wordmemorize.www.VO;

public class CustomWord extends Word {
	private String addedMemberID;

	public String getAddedMemberID() {
		return addedMemberID;
	}

	public void setAddedMemberID(String addedMemberID) {
		this.addedMemberID = addedMemberID;
	}
	
	public CustomWord(){}

	public CustomWord(String addedMemberID) {
		super();
		this.addedMemberID = addedMemberID;
	}

	public CustomWord(String word, String yomigana, String meaning, String wordLevels,String linkAddress,String addedMemberID) {
		super(word, yomigana, meaning, wordLevels, linkAddress);
		this.addedMemberID = addedMemberID;
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
		result = prime * result + ((addedMemberID == null) ? 0 : addedMemberID.hashCode());
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
		CustomWord other = (CustomWord) obj;
		if (addedMemberID == null) {
			if (other.addedMemberID != null)
				return false;
		} else if (!addedMemberID.equals(other.addedMemberID))
			return false;
		return true;
	}

	
}
