package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Song;
import model.Student;
import model.TrackList;
import model.JukeBox;

public class JukeboxTest {
	public static String baseDirect = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");

	Song a = (new Song("Pokemon Capture", 5, "Pikachu", baseDirect +"Capture.mp3"));
	Song b = (new Song("Danse Macabre", 34, "Kevin MacLeod", baseDirect +"DanseMacabreViolinHook.mp3"));
	Song c = (new Song("Determined Tumbao", 20, "FreePlay Music", baseDirect + "DeterminedTumbao.mp3"));

	@Test
	public void testValidate() {
		JukeBox box = new JukeBox();
		assertTrue(box.validate("Chris", "1"));
		assertFalse(box.validate("CHRis", "1"));
		assertFalse(box.validate("Chris", "2"));
	}

	@Test
	public void testPlay() {
		JukeBox box = new JukeBox();
		box.addSong(a);
		box.addSong(b);
		box.addSong(c);
		box.play();
}	
	  @Test
	  public void testCanPlay() {
	    Song song = new Song ("Pokemon Capture", 5, "Pikachu", "Capture.mp3");
	    song.PlayMe();
	    song.PlayMe();
	    assertEquals(2, song.timePlayedToday());
	    assertTrue(song.canBePlayedToday());
	    song.PlayMe();
	    assertFalse(song.canBePlayedToday());
	   
	    song.pretendItsTomorrow();
	    assertEquals(0, song.timePlayedToday());
	    assertTrue(song.canBePlayedToday());
	    song.PlayMe();
	    song.PlayMe();
	    song.PlayMe();
	    assertEquals(3, song.timePlayedToday());
	    assertFalse(song.canBePlayedToday()); 
	  }
	  
	  @Test
	  public void testStudnet() {
		  Student student = new Student("River", "333");
	  }
	  
	  @Test
	  public void testTrackList() {
		  TrackList trackList = new TrackList();
	  }

}
