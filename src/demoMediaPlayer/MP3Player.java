/**
 * Main player for the songs
 *
 * @author Steffan Van Hoesen & Anthony Middleton
 */

package demoMediaPlayer;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MP3Player extends Thread {

	private String fileName;

	private ArrayList<EOSListener> listeners = new ArrayList<EOSListener>();

	public MP3Player(String mp3FileName) {
		fileName = mp3FileName;
	}

	public void addEOSListener(EOSListener listener) {
		if (listener != null)
			this.listeners.add(listener);
	}

	@Override
	public void run() {
		play();
	}

	public void play() {
		try {
			File file = new File(fileName);
			 URI uri = file.toURI();
			 System.out.println(uri);
			    Media media = new Media(uri.toString());
			    MediaPlayer mediaPlayer = new MediaPlayer(media);
			    mediaPlayer.setAutoPlay(true);
			    mediaPlayer.play();
			    
			    mediaPlayer.setOnEndOfMedia(new EndOfSongHandler(null, null));


		} catch (Exception e) {
			System.out.println(e);
		}
	}


	private class EndOfSongHandler implements Runnable {

		private EOSEvent endEvent;
		private EOSListener endListener;

		public EndOfSongHandler(EOSEvent endEvent, EOSListener endListener) {
			this.endEvent = endEvent;
			this.endListener = endListener;
		}

		@Override
		public void run() {
			endListener.songHasEnded(endEvent);
		}
	}
}
