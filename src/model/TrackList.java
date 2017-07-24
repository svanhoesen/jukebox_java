/**
 * The tracklist for songs that are queued
 *
 * @author Steffan Van Hoesen & Anthony Middleton
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

public class TrackList extends ArrayList<Song> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3548446576180871358L;

	public TrackList(){
		
	}

	public boolean queueSong(Song song) {
		if (song.canBePlayedToday()) {
			song.PlayMe();
			return this.add(song);
		}
		else 
			return false;
	}

	public String play() {
		return this.get(0).getSongTitle();
	}
}