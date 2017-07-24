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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class MP3Player extends Thread {

	private String fileName;

	private ArrayList<EOSListener> myListeners = new ArrayList<EOSListener>();

	public MP3Player(String mp3FileName) {
		fileName = mp3FileName;
	}

	public void addEOSListener(EOSListener listener) {
		if (listener != null)
			this.myListeners.add(listener);
	}

	@Override
	public void run() {
		play();
	}

	public void play() {
		AudioFormat decodeForm = null;
		try {
			File file = new File(fileName);
			AudioInputStream basicIn = AudioSystem.getAudioInputStream(file);
			AudioInputStream decodedIn = null;
			AudioFormat basicForm = basicIn.getFormat();

			decodeForm = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, basicForm.getSampleRate(), 16,
					basicForm.getChannels(), basicForm.getChannels() * 2, basicForm.getSampleRate(), false);

			decodedIn = AudioSystem.getAudioInputStream(decodeForm, decodedIn);
			playRawForm(decodeForm, decodedIn);
			basicIn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void playRawForm(AudioFormat tarForm, AudioInputStream decodedIn) {
		SourceDataLine source = null;
		try {
			byte[] byteMyData = new byte[4096];
			try {
				source = getSourceLine(tarForm);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (source != null) {
				source.start();
				int numOBytesRead = 0;
				@SuppressWarnings("unused")
				int numOBytesWritten = 0;
				while (numOBytesRead != -1) {
					numOBytesRead = decodedIn.read(byteMyData, 0, byteMyData.length);
					if (numOBytesRead != -1)
						numOBytesWritten = source.write(byteMyData, 0, numOBytesRead);
				}
				// source.flush();
				source.drain();
				source.stop();
				source.close();
				decodedIn.close();

				try {
					Thread.sleep(3000); // this being in increments of
										// milliseconds was confusing for a
										// while
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (EOSListener listen : myListeners) {
					EOSEvent endOfSong = new EOSEvent(fileName, LocalDate.now(), LocalTime.now());
					if (!EventQueue.isDispatchThread()) {
						try {
							EventQueue.invokeAndWait(new EndOfSongHandler(endOfSong, listen));
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						listen.songHasEnded(endOfSong);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private SourceDataLine getSourceLine(AudioFormat songFormat) throws LineUnavailableException {
		SourceDataLine reSource = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, songFormat);
		reSource = (SourceDataLine) AudioSystem.getLine(info);
		reSource.open(songFormat);
		return reSource;
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
