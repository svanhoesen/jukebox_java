/**
 * The list of students that can login
 *
 * @author Steffan Van Hoesen & Anthony Middleton
 */

package model;

import java.io.Serializable;
/*
 * Author: Steffan Van Hoesen and Anthony Middleton
 * 
 * Class StudentCollection: This is the class student names. Contain all the information that a student should have.
 */
import java.util.HashMap;

public class StudentCollection extends HashMap<String, Student> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3771200739228547925L;

	public StudentCollection() {
		this.put("Chris", new Student("Chris", "1"));
		this.put("Devon", new Student("Devon", "22"));
		this.put("River", new Student("River", "333"));
		this.put("Ryan", new Student("Ryan", "4444"));
		this.put("Admin", new Student("Admin", "1234"));
	}
	
	public void studAdd(String name, String passW){
		this.put(name, new Student(name, passW));
	}
	
	public void studRemove(String name){
		this.remove(name);
	}

	public boolean validateStudent(String userName, String password) {
		return get(userName).getPassword().equals(password);
	}
}