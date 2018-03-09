package com.wordmemorize.www.VO;

public class Word {
	
	private int wordNumber; 
    private String word;
    private String yomigana;
    private String meaning;
    private String wordLevels;
    private String linkAddress;
    
	
	
	public int getWordNumber() {
		return wordNumber;
	}


	public void setWordNumber(int wordNumber) {
		this.wordNumber = wordNumber;
	}


	public String getWord() {
		return word;
	}


	public void setWord(String word) {
		this.word = word;
	}


	public String getYomigana() {
		return yomigana;
	}


	public void setYomigana(String yomigana) {
		this.yomigana = yomigana;
	}


	public String getMeaning() {
		return meaning;
	}


	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}


	public String getWordLevels() {
		return wordLevels;
	}


	public void setWordLevels(String wordLevels) {
		this.wordLevels = wordLevels;
	}


	public String getLinkAddress() {
		return linkAddress;
	}


	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}


	public Word() {}
	
	
	
	public Word(String word, String yomigana, String meaning, String wordLevels, String linkAddress) {
		this.word = word;
		this.yomigana = yomigana;
		this.meaning = meaning;
		this.wordLevels = wordLevels;
		this.linkAddress = linkAddress;
	}


	public Word(int wordNumber, String word, String yomigana, String meaning, String wordLevels, String linkAddress) {
		this.wordNumber = wordNumber;
		this.word = word;
		this.yomigana = yomigana;
		this.meaning = meaning;
		this.wordLevels = wordLevels;
		this.linkAddress = linkAddress;
	}


	@Override
	public String toString() {
		return "Word [wordNumber=" + wordNumber + ", word=" + word + ", yomigana=" + yomigana + ", meaning=" + meaning
				+ ", wordLevels=" + wordLevels + ", linkAddress=" + linkAddress + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((linkAddress == null) ? 0 : linkAddress.hashCode());
		result = prime * result + ((meaning == null) ? 0 : meaning.hashCode());
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		result = prime * result + ((wordLevels == null) ? 0 : wordLevels.hashCode());
		result = prime * result + wordNumber;
		result = prime * result + ((yomigana == null) ? 0 : yomigana.hashCode());
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
		Word other = (Word) obj;
		if (linkAddress == null) {
			if (other.linkAddress != null)
				return false;
		} else if (!linkAddress.equals(other.linkAddress))
			return false;
		if (meaning == null) {
			if (other.meaning != null)
				return false;
		} else if (!meaning.equals(other.meaning))
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		if (wordLevels == null) {
			if (other.wordLevels != null)
				return false;
		} else if (!wordLevels.equals(other.wordLevels))
			return false;
		if (wordNumber != other.wordNumber)
			return false;
		if (yomigana == null) {
			if (other.yomigana != null)
				return false;
		} else if (!yomigana.equals(other.yomigana))
			return false;
		return true;
	}
	
	
    
}
