package model;

import java.io.Serializable;
/**
  * Author: Steffan Van Hoesen and Anthony Middleton
  * 
  * Class AdminCollection: Contains the login information for the current set of admins.
  */
import java.util.HashMap;

public class AdminCollection extends HashMap<String, Student> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3771200739228547925L;

	public AdminCollection() {
		this.put("Alex", new Student("Alex", "12345"));
	}
	//allows for the admin to be add another admin
	public void adminAdd(String name, String passW){
		this.put(name, new Student(name, passW));
	}
	// allows for the admin to be remove another admin
	public void adminRemove(String name){
		this.remove(name);
	}
	//validates the current login info
	public boolean validateAdmin(String userName, String password) {
		return get(userName).getPassword().equals(password);
	}
}