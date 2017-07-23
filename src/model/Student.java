package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Student implements Serializable {

	// The student can select up to three song everyday.
	public final static int MAX_NUM_O_PLAYS = 3;
	public final static int MAX_TIME = 1500 * 60;

	private String userName;
	private String password;
	LocalDate lastQueueTime;
	private int timeAllowed;
	private ArrayList<Song> hasBeenPlayedToday;

	public Student() {
		hasBeenPlayedToday = new ArrayList<Song>();
		lastQueueTime = LocalDate.now();
		lastQueueTime = lastQueueTime.minusYears(40);
		timeAllowed = MAX_TIME;
	}

	public Student(String username, String password) {
		this.userName = username;
		this.password = password;
		hasBeenPlayedToday = new ArrayList<Song>();
		lastQueueTime = LocalDate.now();
		lastQueueTime = lastQueueTime.minusYears(40);
		timeAllowed = MAX_TIME;
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

	public String getTimeAllowed() {

		final int MINUTES_PER_HOUR = 60;
		final int SECONDS_PER_MINUTE = 60;

		int sec = timeAllowed % SECONDS_PER_MINUTE;
		int totalMin = timeAllowed / SECONDS_PER_MINUTE;
		int min = totalMin % MINUTES_PER_HOUR;
		int h = totalMin / MINUTES_PER_HOUR;

		return "Total playing time left: " + h + " hours " + min + " minutes " + sec + " seconds";
	}

	public void addPlayed(Song song) {
		if (canPlay()) {
			hasBeenPlayedToday.add(song);
			timeAllowed -= song.gettime();
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

	public String getAvailableSong() {
		return userName + " can choose " + (3 - hasBeenPlayedToday.size()) + " song(s) today";
	}

}