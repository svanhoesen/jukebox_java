/**
 * The tracklist for songs that are queued
 *
 * @author Steffan Van Hoesen & Anthony Middleton
 */
package model;

import java.io.File;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class TrackList extends ArrayList<Song> implements Serializable {
	private LinkedList<Song> list;
	private static final long serialVersionUID = -3548446576180871358L;

	public TrackList() {
		list = new LinkedList<Song>();
	}

	public void queueSong(Song song) throws Exception {
		if (song.canBePlayedToday() || !list.isEmpty()) {
			list.add(song);
			playSong(song);
		}
		if (list.getLast() == null) {
			list.add(song);
			playSong(song);
		}
		if (list.getLast() == null) {
			list.add(song);
			playSong(song);
		}
		return;
	}

	private class EndOfSongHandler implements Runnable {
		@Override
		public void run() {
			list.remove(list.peek());
			if (list.isEmpty()) {
				return;
			} else {
				try {
					playSong(list.peek());
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}

	public void playSong(Song song) throws Exception {

		File file = new File(song.getLocation());
		URI uri = file.toURI();
		System.out.println(uri);
		Media media = new Media(uri.toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.play();

		mediaPlayer.setOnEndOfMedia(new EndOfSongHandler());
	}
}