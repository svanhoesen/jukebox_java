/**
 * Interface for listening for the end of the song
 *
 * @author Steffan Van Hoesen & Anthony Middleton
 */
package demoMediaPlayer;

public interface EOSListener {
	public void songHasEnded(EOSEvent eventWithFileNameAndDateEnded);
}
