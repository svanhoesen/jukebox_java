/**
 * The list of students that can login
 *
 * @author Steffan Van Hoesen & Anthony Middleton
 */

package model;

import java.io.Serializable;
import java.util.HashMap;

public class StudentCollection extends HashMap<String, Student> implements Serializable {

	public StudentCollection() {
		this.put("Chris", new Student("Chris", "1"));
		this.put("Devon", new Student("Devon", "22"));
		this.put("River", new Student("River", "333"));
		this.put("Ryan", new Student("Ryan", "4444"));
	}

	public boolean validateStudent(String userName, String password) {
		return get(userName).getPassword().equals(password);
	}
}