package model;

import java.io.Serializable;
/*
 * Author: Steffan Van Hoesen and Anthony Middleton
 * 
 * Class StudentCollection: This is the class student names. Contain all the information that a student should have.
 */
import java.util.HashMap;

public class AdminCollection extends HashMap<String, Student> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3771200739228547925L;

	public AdminCollection() {
		this.put("Admin", new Student("Admin", "1234"));
	}

	public boolean validateAdmin(String userName, String password) {
		return get(userName).getPassword().equals(password);
	}
}