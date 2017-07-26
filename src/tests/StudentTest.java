package tests;

	import static org.junit.Assert.*;

	import org.junit.Test;

	import model.Song;
	import model.Student;

	public class StudentTest {
		public static String baseDirect = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
				+ System.getProperty("file.separator");

		@Test
		public void testIDSetterAndGetter() {
			Student student = new Student();
			student.setUserName("Chris");
			assertEquals("Chris", student.getUserName());
		}

		@Test
		public void testPasswordSetterAndGetter() {
			Student student = new Student();
			student.setPassword("1");
			assertEquals("1", student.getPassword());
		}

		@Test
		public void testCanPlay() {
			Student student = new Student();
			Song song = new Song("The Curtain Rises", 28, "Kevin MacLeod", baseDirect + "TheCurtainRises.mp3");
			student.addPlayed(song);
			student.addPlayed(song);
			assertTrue(student.canPlay());
			
			student.addPlayed(song);
			assertFalse(student.canPlay());
		}
		
		@Test
		public void testCanPlayNewDay(){
			Student student = new Student();
			Song song = new Song("Swing Cheese", 15, "FreePlay Music", baseDirect + "SwingCheese.mp3");
			student.addPlayed(song);
			student.addPlayed(song);
			student.addPlayed(song);
			
			student.makeDateChange();
			student.canPlay();
		}
		
//		@Test
//		public void testGetAvailableTime(){
//			Student student = new Student();
//			Song song = new Song("Determined Tumbao", 20, "FreePlay Music", baseDirect + "DeterminedTumbao.mp3");
//			assertEquals("remaining playing time is: 25 hours 0 minutes 0 seconds", student.getTimeAllowed(song));
//			
//			student.addPlayed(song);
//			assertEquals("remaining playing time is: 24 hours 59 minutes 40 seconds", student.getTimeAllowed(song));
//		}

	}