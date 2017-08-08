/**
 * The JukeBox class handles the coordination between students login, songs played, etc.
 *
 * @author Steffan Van Hoesen & Anthony Middleton
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import demoMediaPlayer.EOSEvent;
import demoMediaPlayer.EOSListener;
import demoMediaPlayer.MediaPlayer;

public class JukeBox extends Observable implements Serializable{

	public static String baseDirect = "songfiles/";
	private String userName;
	private StudentCollection studentList;
	private TrackList list = new TrackList();

	public JukeBox() {
		studentList = new StudentCollection();
	}
	
	/**
	 * setters
	 */
	public void setCurrentUserName(String userName) {
		this.userName = userName;
	}

	public void setTrackList(ArrayList<Song> readObject) {
		for (Song song : readObject)
			list.add(song);
	}

	public void setStudentList(HashMap<String, Student> readObject) {
		studentList.clear();
		for (Map.Entry<String, Student> entry : readObject.entrySet()) {
			studentList.put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * getters
	 */
	public Student getCurrentStudent() {
		return studentList.get(userName);
	}

	public StudentCollection getStudentList() {
		return studentList;
	}

	public TrackList getTrackList() {
		return list;
	}
	
	/**
	 * other functions
	 */
	
	public boolean validate(String userName, String password) {
		if (!studentList.containsKey(userName))
			return false;
		else
			return studentList.validateStudent(userName, password);
	}

	public boolean canStudentPlay() {
		return getCurrentStudent().canPlay();
	}


//	public boolean addSong(Song song) throws Exception {
//		return list.queueSong(song);
//	}

//	public void play() {
//		MediaPlayer.playSong(new WaitForSongToEnd(), list.play());
//	}

//	private class WaitForSongToEnd implements EOSListener {
//
//		public void songHasEnded(EOSEvent eosEvent) {
//			list.remove(0);
//			setChanged();
//			notifyObservers();
//			if (!list.isEmpty()) {
//				MediaPlayer.playSong(new WaitForSongToEnd(), list.play());
//			}
//		}
//	}

}