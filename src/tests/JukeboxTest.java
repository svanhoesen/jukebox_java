package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Song;
import model.Student;
import model.TrackList;

public class JukeboxTest {

	@Test
	public void test() {
		fail("Not yet implemented");
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
