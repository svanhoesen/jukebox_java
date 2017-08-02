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

import controller_view.Iteration2Controller;
import controller_view.JukeBoxGUI;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class TrackList extends ArrayList<Song> implements Serializable {
	private LinkedList<Song> list;
	private static final long serialVersionUID = -3548446576180871358L;
	private MediaPlayer mediaPlayer;

	public TrackList() {
		list = new LinkedList<Song>();
	}

	public int size() {
		return list.size();
	}

	public void queueSong(Song song) {
		if (list.isEmpty()) {
			list.add(song);
			playSong(song);
		} else {
			list.add(song);
		}
	}

	private class EndOfSongHandler implements Runnable {
		@Override
		public void run() {
			Iteration2Controller.getSongsForList().remove(list.peek());
			Iteration2Controller.getListViewSongs().refresh();
			list.remove();
			if (!list.isEmpty())
				playSong(list.peek());
		}
	}

	public void playSong(Song song) {
		File file = new File(song.getLocation());
		URI uri = file.toURI();
		Media media = new Media(uri.toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.play();

		mediaPlayer.setOnEndOfMedia(new EndOfSongHandler());
	}
}