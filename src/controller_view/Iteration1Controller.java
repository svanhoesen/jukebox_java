package controller_view;

import java.awt.TextField;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Iteration1Controller extends Application {

  public static void main(String[] args) {
    launch(args);
  }
  private Button buttonSong1 = new Button("Select song 1");
  private Button buttonSong2 = new Button("Select song 2");
  private Label accontName = new Label("Accont Name");
  private Label pasword = new Label("Pasword");
  private TextField textFieldAccN = new TextField("You can edit this text");
  private TextField textFieldPW= new TextField("You can edit this text");
  private Button login = new Button("Login");
  private Label logFirts = new Label("Login first");
  private Button logOut = new Button("Log out");


  @Override
  public void start(Stage primaryStage) throws Exception {

    BorderPane all = new BorderPane();
    Scene scene = new Scene(all, 300, 230);
    primaryStage.setScene(scene);
    
	HBox hbox = new HBox();
	hbox.setPadding(new Insets(15, 12, 15, 12));
	hbox.setSpacing(10);
	
	hbox.getChildren().addAll(buttonSong1, buttonSong2);

	all.setTop(hbox);
	
	HBox hbox2 = new HBox();
	hbox.setPadding(new Insets(15, 12, 15, 12));
	hbox.setSpacing(10);
		

    // Don't forget to show the running application:
    primaryStage.show();
  }
}