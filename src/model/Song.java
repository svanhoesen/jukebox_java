package model;

public class Song {
	private String title;
	private int seconds;
	private String artist;
	private String fileName;

	public Song(String songTitle, int songLenght, String arhtur, String fileName) {
		this.title = songTitle;
		this.seconds = songLenght;
		this.artist = arhtur;
		this.fileName = fileName;
	}

	public void PlayMe() {
		// TODO Auto-generated method stub	
	}

	public Object timePlayedToday() {
		// TODO Auto-generated method stub
		return null;
	}

	public void pretendItsTomorrow() {
		// TODO Auto-generated method stub
		
	}

	public boolean canBePlayedToday() {
		// TODO Auto-generated method stub
		return false;
	}

}
