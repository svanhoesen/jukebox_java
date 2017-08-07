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
	// Sets the amount of times the song has been played
	public void setPlays(int plays) {
		this.plays = plays;
	}
	//Gets the title of the song
	public String getTitle() {
		return title;
	}
	//gets the name of the song's artist 
	public String getArtist() {
		return artist;
	}
	// gets the duration of the song
	public int getTime() {
		return seconds;
	}
	// sets the the location of where the song  is stored.
	public String getLocation() {
		return fileName;
	}
	// gets the number of times the song has been played.
	public int getPlays(){
		return plays;
	}
	// updates the last time the song was played to the current time and date, 
	// and the number of times the song was played is incremented up by 1.
	public void PlayMe() {
		this.curDate = new GregorianCalendar(GregorianCalendar.YEAR, GregorianCalendar.MONTH,
				GregorianCalendar.DAY_OF_MONTH);
		lastDate = curDate;
		plays++;
	}
	// checks the remaining number of times that a song can be played
	public int timePlayedToday() {
		return maxPlay - plays;
	}
	// moves up the date by 1; therefore resetting the amount of times a song can be played.
	public void pretendItsTomorrow() {
		lastDate.add(Calendar.DATE, 1);
		plays = maxPlay;
	}
	//checks if the song can be played on the current date.
	public boolean canBePlayedToday() {
		if (plays == 3) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return title;
	}

}
