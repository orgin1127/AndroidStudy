package com.wordmemorize.www.VO;

public class Member {
	
	private int memberNumber;
	private String memberID;
	private String password;
	private String memberName;
	private String registeredDate;
	private int memberLevel;
	private int blinkGameCount;
	
	public int getMemberNumber() {return memberNumber;}
	public void setMemberNumber(int memberNumber) {this.memberNumber = memberNumber;}
	public String getMemberID() {return memberID;}
	public void setMemberID(String memberID) {this.memberID = memberID;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public String getMemberName() {return memberName;}
	public void setMemberName(String memberName) {this.memberName = memberName;}
	public String getRegisteredDate() {return registeredDate;}
	public void setRegisteredDate(String registeredDate) {this.registeredDate = registeredDate;}
	public int getMemberLevel() {return memberLevel;}
	public void setMemberLevel(int memberLevel) {this.memberLevel = memberLevel;}
	public int getBlinkGameCount() {return blinkGameCount;}
	public void setBlinkGameCount(int blinkGameCount) {this.blinkGameCount = blinkGameCount;}
	
	public Member() {}
	
	public Member(String memberID, String password, String memberName) {
		this.memberID = memberID;
		this.password = password;
		this.memberName = memberName;
	}
	
	public Member(int memberNumber, String memberID, String password, String memberName, String registeredDate,
			int memberLevel, int blinkGameCount) {
		this.memberNumber = memberNumber;
		this.memberID = memberID;
		this.password = password;
		this.memberName = memberName;
		this.registeredDate = registeredDate;
		this.memberLevel = memberLevel;
		this.blinkGameCount = blinkGameCount;
	}
	@Override
	public String toString() {
		return "Member [memberNumber=" + memberNumber + ", memberID=" + memberID + ", password=" + password
				+ ", memberName=" + memberName + ", registeredDate=" + registeredDate + ", memberLevel=" + memberLevel
				+ ", blinkGameCount=" + blinkGameCount + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + blinkGameCount;
		result = prime * result + ((memberID == null) ? 0 : memberID.hashCode());
		result = prime * result + memberLevel;
		result = prime * result + ((memberName == null) ? 0 : memberName.hashCode());
		result = prime * result + memberNumber;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((registeredDate == null) ? 0 : registeredDate.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (blinkGameCount != other.blinkGameCount)
			return false;
		if (memberID == null) {
			if (other.memberID != null)
				return false;
		} else if (!memberID.equals(other.memberID))
			return false;
		if (memberLevel != other.memberLevel)
			return false;
		if (memberName == null) {
			if (other.memberName != null)
				return false;
		} else if (!memberName.equals(other.memberName))
			return false;
		if (memberNumber != other.memberNumber)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (registeredDate == null) {
			if (other.registeredDate != null)
				return false;
		} else if (!registeredDate.equals(other.registeredDate))
			return false;
		return true;
	}
	
}
