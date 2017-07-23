package controller_view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
  private Button login = new Button("Login");
  private Label logFirts = new Label("Login first");
  private Button logOut = new Button("Log out");


  @Override
  public void start(Stage primaryStage) throws Exception {

    BorderPane all = new BorderPane();
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10, 10, 10, 10));
    grid.setVgap(5);
    grid.setHgap(5);
    
    Scene scene = new Scene(all, 300, 230);
    primaryStage.setScene(scene);
    
	HBox hbox = new HBox();
	hbox.setPadding(new Insets(15, 12, 15, 12));
	hbox.setSpacing(10);
	
	hbox.getChildren().addAll(buttonSong1, buttonSong2);
	all.setTop(hbox);
	
    GridPane.setConstraints(accontName, 0, 0);
    grid.getChildren().add(accontName);
    
    final TextField textFieldAccn = new TextField();
    textFieldAccn.setPromptText("Enter your name.");
    GridPane.setConstraints(textFieldAccn, 1, 0);
    grid.getChildren().add(textFieldAccn);
    
    GridPane.setConstraints(pasword, 0, 1);
    grid.getChildren().add(pasword);
    
    final TextField textFieldPW = new TextField();
    textFieldPW.setPromptText("Enter your password.");
    GridPane.setConstraints(textFieldPW, 1, 1);
    grid.getChildren().add(textFieldPW);

    GridPane.setConstraints(login, 1, 2);
    grid.getChildren().add(login);
    
    GridPane.setConstraints(logFirts, 1, 3);
    grid.getChildren().add(logFirts);
    
    GridPane.setConstraints(logOut, 1, 4);
    grid.getChildren().add(logOut);
    
	all.setCenter(grid);

    // Don't forget to show the running application:
    primaryStage.show();
  }
}