package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.StudentCollection;

public class StudentCollectionTest {


	@Test
	public void testValidateStudents() {
		StudentCollection uc = new StudentCollection();
		assertTrue(uc.validateStudent("Chris", "1"));
	}
	
	@Test
	public void testGetUserByID(){
		StudentCollection uc = new StudentCollection();
		assertEquals("Chris", uc.get("Chris").getUserName());
	}
}