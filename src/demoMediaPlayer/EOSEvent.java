/**
 * The Event handler for when the song ends
 *
 * @author Steffan Van Hoesen & Anthony Middleton
 */

package demoMediaPlayer;

import java.time.LocalDate;
import java.time.LocalTime;


public class EOSEvent {

	private String fileName;
	private LocalDate currDate;
	private LocalTime currTime;

	/**
	 * Construct a new EndOfSongEvent with the file name and time of the song that just finished playing
	 * 
	 * @param fileName
	 * @param currTime
	 */
	public EOSEvent(String fileName, LocalDate currDate, LocalTime currTime) {
		this.fileName = fileName;
		this.currDate = currDate;
		this.currTime = currTime;
	}

	/**
	 * access to the name of the audio file that just finished.
	 * 
	 * @return the name of the file
	 */
	public String fileName() {
		return fileName;
	}

	/**
	 *  access to Date of this EOSEvent
	 * 
	 * @return
	 */
	public LocalDate endDate() {
		return currDate;
	}

	/**
	 * Provide access to time of this EOSEvent
	 * 
	 * @return
	 */
	public LocalTime endTime() {
		return currTime;
	}
}