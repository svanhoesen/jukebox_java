package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.TrackList;
import model.Song;

public class TrackListTest {

	public static String baseDirect = "songfiles/";

//	@Test
//	public void testLimitOfSongsEachDay()  {
//		TrackList list = new TrackList();
//		Song song = new Song("Pokemon Capture", 5, "Pikachu", baseDirect + "Capture.mp3");
//		
//		list.queueSong(song);
//		list.queueSong(song);
//		list.queueSong(song);
//		assertEquals(3, list.size());
//		list.queueSong(song);
//		assertEquals(3, list.size());
//	}

	@Test
	public void testIsEmpty() {
		TrackList list = new TrackList();
		assertTrue(list.isEmpty());
	}
//
//	@Test
//	public void testPlay() {
//		TrackList list = new TrackList();
//		Song song = new Song("Untameable Fire", 282, "Pierre Langer", baseDirect + "UntameableFire.mp3");
//		list.queueSong(song);
//		assertEquals("Untameable Fire", list.play());
//	}

}
