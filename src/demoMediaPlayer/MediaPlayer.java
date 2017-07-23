/**
 * the event handler for playing a song
 *
 * @author Steffan Van Hoesen & Anthony Middleton
 */
package demoMediaPlayer;

public class MediaPlayer {

	public static void playSong(EOSListener waitForEnd, String MP3Name) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				Thread mp3Player = new MP3Player(MP3Name);
				((MP3Player) mp3Player).addEOSListener(waitForEnd);
				mp3Player.start();
			}
		});
	}
}