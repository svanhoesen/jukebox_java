package model;

/*
 * Author: Steffan Van Hoesen and Anthony Middleton
 * 
 * Class Song: This is the class song information. Contain all the information that a song should have.
 */
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.io.Serializable;

public class Song implements Serializable{
	private String title;
	private int seconds;
	private String artist;
	private String fileName;
	private GregorianCalendar lastDate;
	private GregorianCalendar curDate;
	private int plays;
	private int maxPlay = 3;

	public Song(String title, int seconds, String artist, String fileName) {
		this.title = title;
		this.seconds = seconds;
		this.artist = artist;
		this.fileName = fileName;
		this.plays = 0;
		this.lastDate = new GregorianCalendar();
	}

	public String getTitle() {
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
	
	public int getPlays(){
		return plays;
	}

	public void PlayMe() {
		this.curDate = new GregorianCalendar(GregorianCalendar.YEAR, GregorianCalendar.MONTH,
				GregorianCalendar.DAY_OF_MONTH);
		lastDate = curDate;
		plays++;
	}

	public int timePlayedToday() {
		return maxPlay - plays;
	}

	public void pretendItsTomorrow() {
		lastDate.add(Calendar.DATE, 1);
		plays = maxPlay;
	}

	public boolean canBePlayedToday() {
		if (plays == 3) {
			return false;
		}
		return true;
	}

}
