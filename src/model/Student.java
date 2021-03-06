/**
 * The class for Students keeps track of username, password, number of plays, and time remaining
 *
 * @author Steffan Van Hoesen & Anthony Middleton
 */

package model;
/*
 * Author: Steffan Van Hoesen and Anthony Middleton
 * 
 * Class Student: This is the class student names. Contain all the information that a student should have.
 */
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Student implements Serializable {

	private static final long serialVersionUID = -5458928616300964957L;
	// The student can select up to three song everyday.
	public final static int MAX_NUM_O_PLAYS = 3;
	public final static int MAX_TIME = 1500 * 60;

	private String userName;
	private String password;
	LocalDate lastQueueTime;
	private int timeAllowed;
	private int playedToday;
	private ArrayList<Song> hasBeenPlayedToday;

	public Student(String username, String password) {
		this.userName = username;
		this.password = password;
		hasBeenPlayedToday = new ArrayList<Song>();
		lastQueueTime = LocalDate.now();
		timeAllowed = MAX_TIME;
		playedToday = 0;

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getTimeAllowed(){
		return timeAllowed;
	}

	public String updateTimeAllowed(Song song) {
		timeAllowed -= song.getTime(); 

//		int h = usedTime % secSong;
//		int min = usedTime % secSong;
//		int sec = secSong / 60;
		
		return ""+timeAllowed;//""+ h + ":" + min + ":" + sec;
	}

	public void addPlayed(Song song) {
		if (canPlay()) {
			hasBeenPlayedToday.add(song);
			timeAllowed -= song.getTime();
		}
	}

	public boolean canPlay() {
		LocalDate today = LocalDate.now();
		if (!today.equals(lastQueueTime)) {
			lastQueueTime = today;
			hasBeenPlayedToday.clear();
		}
		return hasBeenPlayedToday.size() < MAX_NUM_O_PLAYS;

	}

	public void makeDateChange() {
		if (canPlay())
			return;
		else {
			lastQueueTime = LocalDate.now().plusDays(1);
		}
	}

	public int getPlayedToday() {
		return playedToday;
	}

	public void setPlayedToday(int playedToday) {
		this.playedToday = playedToday;
	}
	
}