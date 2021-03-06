package model;

/**
  * Author: Steffan Van Hoesen and Anthony Middleton
  * 
  * Class Admin: This is the class that handles the admin functions. 
  */
import java.io.Serializable;

import java.time.LocalDate;
import java.util.ArrayList;
import controller_view.SongView;
import controller_view.StudentViewer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Admin extends Application implements Serializable {
	public Admin() {

	}

	public static void main(String[] args) {
		launch(args);
	}

	private StudentViewer studentViewer;
	private StudentCollection studCollect;
	private Button buttonAdd;
	private Button buttonRemove;
	private Label accontName;
	private Label pasword;
	private TextField textFieldAccn;
	private PasswordField textFieldPW;
	private Label labelTitle;
	private String name = "";
	private String passW = "";
	private Stage primaryStage;
	private ObservableList<Student> studentList = FXCollections.<Student>observableArrayList();
    private ListView<Student> studentListView = new ListView<>(studentList);

	//sets up the admin popup window
	@Override
	public void start(Stage primaryStage) throws Exception {
		setup();

		BorderPane all = new BorderPane();
		
		Scene scene = new Scene(all, 500, 400);
		primaryStage.setScene(scene);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		GridPane.setConstraints(accontName, 0, 0);
		grid.getChildren().add(accontName);

		textFieldAccn.setPromptText("Enter new users name.");
		GridPane.setConstraints(textFieldAccn, 1, 0);
		grid.getChildren().add(textFieldAccn);

		GridPane.setConstraints(pasword, 0, 1);
		grid.getChildren().add(pasword);

		textFieldPW.setPromptText("Enter new uers password.");
		GridPane.setConstraints(textFieldPW, 1, 1);
		grid.getChildren().add(textFieldPW);

		GridPane.setConstraints(buttonAdd, 1, 2);
		grid.getChildren().add(buttonAdd);

		GridPane.setConstraints(buttonRemove, 1, 4);
		grid.getChildren().add(buttonRemove);

		GridPane.setConstraints(labelTitle, 0, 5);
		labelTitle.setFont(new Font("Arial", 16));
		grid.getChildren().add(labelTitle);

		GridPane.setConstraints(studentViewer, 0, 6);
		grid.getChildren().add(studentViewer);

	      // Show that a change to the Student object in the ObserverableList
	      // also change the Student object in StudentCollection.
	      StudentCollection studentCollection = studentViewer.getList();
	      System.out.println(studentCollection.get(0));	 
	      
		// action methods
		setUpHandlerAdd();
		setUpHandlerRemove();
		all.setCenter(grid);

		// Don't forget to show the running application:
		primaryStage.show();
	}

	public Stage getStage() {
		return primaryStage;
	}

	// Event handler for adding students
	private void setUpHandlerAdd() {
		buttonAdd.setOnAction(event -> {
			name = textFieldAccn.getText();
			passW = textFieldPW.getText();
			if (!studCollect.validateStudent(name, passW)) {
				studCollect.studAdd(name, passW);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("User has been added!");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("User already added!");
				alert.showAndWait();
			}
		});
	}
	
	// Event handler for removing students
	private void setUpHandlerRemove() {
		buttonRemove.setOnAction(event -> {
			name = textFieldAccn.getText();
			passW = textFieldPW.getText();
			if (studCollect.validateStudent(name, passW)) {
				studCollect.studRemove(name);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("User has been removed!");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("User not removed!");
				alert.showAndWait();
			}
		});
	}
	
	// setup for the fields
	private void setup() {
		// TODO Auto-generated method stub
		labelTitle = new Label("Student List");
		studentViewer = new StudentViewer();
		buttonAdd = new Button("Add User");
		buttonRemove = new Button("Remove User");
		textFieldPW = new PasswordField();
		textFieldAccn = new TextField();
		accontName = new Label("Accont Name");
		pasword = new Label("Pasword");
		new Label("Waiting to queue song...");
		studCollect = new StudentCollection();
	}

}