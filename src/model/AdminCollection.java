package model;

/*
 * Author: Steffan Van Hoesen and Anthony Middleton
 * 
 * Class StudentCollection: This is the class student names. Contain all the information that a student should have.
 */
import java.io.Serializable;
import java.util.HashMap;

public class AdminCollection extends HashMap<String, Student> implements Serializable {

	public AdminCollection() {
		this.put("Admin", new Student("Admin", "123"));
		}

	public boolean validateAdmin(String userName, String password) {
		return get(userName).getPassword().equals(password);
	}
}