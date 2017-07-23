package model;
/*
 * Author: Steffan Van Hoesen and Anthony Middleton
 * 
 * Class Song: This is the class song information. Contain all the information that a song should have.
 */

public class Song {
	private String title;
	private int seconds;
	private String artist;
	private String fileName;
	private int played;
	private int maxPlay = 3;


	public Song(String title, int seconds, String artist, String fileName) {
		this.title = title;
		this.seconds = seconds;
		this.artist = artist;
		this.fileName = fileName;
		played = maxPlay;
	}
	
	public String getSongTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public int getTime() {
		return seconds;
	}

	public String getLocation() {
		return fileName;
	}

	public void PlayMe() {
		played--;
	}

	public Object timePlayedToday() {
		// TODO Auto-generated method stub
		return null;
	}

	public void pretendItsTomorrow() {
		// TODO Auto-generated method stub
	}

	public boolean canBePlayedToday() {
		if (played == 0){
			return false;
		}
		return true;
	}

}
