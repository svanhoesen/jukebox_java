package controller_view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Song;
import model.SongCollection;
import model.Student;
import model.StudentCollection;
import model.TrackList;

public class Iteration2Controller extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private SongView songViewer;
	private Button buttonGo;
	private Label accontName;
	private Label pasword;
	private TextField textFieldAccn;
	private PasswordField textFieldPW;
	private Label labelTitle;
	private Label labelCurrPlay;
	private Label howToPlay;
	private Button login;
	private Label logFirts;
	private Label currPlay;
	private Button logOut;
	private int songCount = 0;
	private String name = "";
	private String passW = "";
	private Student stud = new Student(name, passW);
	private StudentCollection studCollect;
	private SongCollection album;
	private Song song;
	private TrackList list;
	private ObservableList<Song> songsForList;
	private ListView<String> listViewSongs;

	@Override
	public void start(Stage primaryStage) throws Exception {

		setup();
		BorderPane all = new BorderPane();
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		Scene scene = new Scene(all, 700, 600);
		primaryStage.setScene(scene);

		GridPane.setConstraints(accontName, 0, 0);
		grid.getChildren().add(accontName);

		textFieldAccn.setPromptText("Enter your name.");
		GridPane.setConstraints(textFieldAccn, 1, 0);
		grid.getChildren().add(textFieldAccn);

		GridPane.setConstraints(pasword, 0, 1);
		grid.getChildren().add(pasword);

		textFieldPW.setPromptText("Enter your password.");
		GridPane.setConstraints(textFieldPW, 1, 1);
		grid.getChildren().add(textFieldPW);

		GridPane.setConstraints(login, 1, 2);
		grid.getChildren().add(login);

		GridPane.setConstraints(logFirts, 1, 3);
		grid.getChildren().add(logFirts);

		GridPane.setConstraints(logOut, 1, 4);
		grid.getChildren().add(logOut);

		GridPane.setConstraints(labelTitle, 0, 5);
		labelTitle.setFont(new Font("Arial", 16));
		grid.getChildren().add(labelTitle);
		
		GridPane.setConstraints(howToPlay, 1, 5);
		grid.getChildren().add(howToPlay);
		
		GridPane.setConstraints(labelCurrPlay, 2, 5);
		labelCurrPlay.setFont(new Font("Arial", 16));
		grid.getChildren().add(labelCurrPlay);

		GridPane.setConstraints(songViewer, 0, 6);
		grid.getChildren().add(songViewer);

		GridPane.setConstraints(buttonGo, 1, 6);
		grid.getChildren().add(buttonGo);

		GridPane.setConstraints(currPlay, 2, 6);
		grid.getChildren().add(currPlay);
		
		// action methods
		login();
		setUpHandler();
		logOut();

		stud.setUserName(textFieldAccn.getText());
		stud.setPassword(textFieldPW.getText());

		all.setCenter(grid);
		// Show current playlist
//		all.setBottom(listViewSongs);

		// Don't forget to show the running application:
		primaryStage.show();
	}

	private void login() {
		login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				stud.setUserName(textFieldAccn.getText());
				stud.setPassword(textFieldPW.getText());
				name = textFieldAccn.getText();
				passW = textFieldPW.getText();

				if (textFieldAccn.getText() == null || textFieldPW.getText() == null) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Message");
					alert.setContentText("Please sign in!");
					alert.showAndWait();
				} else if (studCollect.validateStudent(name, passW)) {
					logFirts.setText(list.size() + "    25:00:00");
				} else {
					logFirts.setText("Try Again");
				}
			}
		});
	}

	private void setUpHandler() {
		buttonGo.setOnAction(event -> {
			if (studCollect.validateStudent(name, passW) && (songCount < 3)) {
				logFirts.setText(list.size() + "    25:00:00");
				// Determine which row is selected
				Song selectedSong = (Song) songViewer.getSelectionModel().getSelectedItem();
				// Pass the selected song
				Song songToPlay = selectedSong;
				System.out.println("this is a test for the song class " + songToPlay.getTitle());
				list.queueSong(songToPlay);
				songToPlay.PlayMe();
				songCount++;
				logFirts.setText(list.size() + "   " + stud.getTimeAllowed(songToPlay));
				
				currPlay.setText(songToPlay.getTitle());

				// Get info from selected list
//				SongCollection songCollection = songViewer.getList();

				// Get info from selected list
//				songsForList = FXCollections.observableArrayList(songCollection.get(0).getTitle());
//				ListView<Song> listView = new ListView<Song>(songsForList);
//				listView.setCellFactory(param -> new ListCell<Song>() {
//		            @Override
//		            protected void updateItem(Song item, boolean empty) {
//		                super.updateItem(item, empty);
//
//		                if (empty || item == null || item.getTitle() == null) {
//		                    setText(null);
//		                } else {
//		                    setText(item.getTitle());
//		                }
//		            }
//		        });
//				songViewer.refresh();

			} else if (songCount == 3 || stud.canPlay() == false || song.canBePlayedToday() == false) {
				logFirts.setText("3     " + stud.getTimeAllowed(song));
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("User has reached the limit!");
				alert.showAndWait();
			} else {
				logFirts.setText("Try Again");
			}
		});
	}

	private void logOut() {
		logOut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				textFieldAccn.clear();
				textFieldPW.clear();
				logFirts.setText("Login first");
				currPlay.setText("Waiting to que song...");
				songCount = 0;
			}
		});
	}

	private void setup() {
		// TODO Auto-generated method stub
		labelTitle = new Label("Song List");
		labelCurrPlay = new Label("Current Playlist");
		howToPlay = new Label("Select then add to list");
		songViewer = new SongView();
		buttonGo = new Button("Add to Playlist");
		textFieldPW = new PasswordField();
		textFieldAccn = new TextField();
		accontName = new Label("Accont Name");
		pasword = new Label("Pasword");
		login = new Button("Login");
		logFirts = new Label("Login first");
		currPlay = new Label("Waiting to queue song...");
		logOut = new Button("Log out");
		stud = new Student(name, passW);
		studCollect = new StudentCollection();
		album = new SongCollection();
		list = new TrackList();
	}

}