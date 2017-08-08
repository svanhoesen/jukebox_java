package controller_view;

import java.io.Serializable;

import com.sun.org.glassfish.external.statistics.Statistic;
import com.sun.xml.internal.bind.v2.model.core.ID;

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

public class Iteration2Controller extends Application implements Serializable{

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
	private Student curStud;
	private StudentCollection studCollect;
	private SongCollection album;
	private Song songToPlay;
	private TrackList list;
	private static ObservableList<Song> songsForList = FXCollections.observableArrayList();
	private static ListView<Song> listViewSongs;
	private String remainingTime = "";
	private int usedTime;
	private int userPlays = 0;

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

		GridPane.setConstraints(listViewSongs, 2, 6);
		grid.getChildren().add(listViewSongs);

		// action methods
		login();
		setUpHandler();
		logOut();
		all.setCenter(grid);

		// Don't forget to show the running application:
		primaryStage.show();
	}

	private void login() {
		login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				name = textFieldAccn.getText();
				passW = textFieldPW.getText();

				if (studCollect.validateStudent(name, passW)) {
					curStud = studCollect.get(name);
					logFirts.setText(curStud.getPlayedToday() + "       " + curStud.getTimeAllowed());
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Message");
					alert.setContentText("Please sign in!");
					alert.showAndWait();
				}
			}
		});
	}

	private void setUpHandler() {
		buttonGo.setOnAction(event -> {
			if (curStud == null) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("Please sign in!");
				alert.showAndWait();
				logFirts.setText("PLEASE LOG IN!");
			} else if (curStud == null || (curStud.getPlayedToday() >= 3)) {
				logFirts.setText(curStud.getPlayedToday() + "     " + curStud.getTimeAllowed());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("User has reached the limit!");
				alert.showAndWait();
			} else {
				logFirts.setText(curStud.getPlayedToday() + "         " + curStud.getTimeAllowed());
				// Determine which row is selected
				Song selectedSong = (Song) songViewer.getSelectionModel().getSelectedItem();
				// Pass the selected song
				songToPlay = selectedSong;
				// Get song length
				songToPlay.setPlays(songToPlay.getPlays() + 1);
				songViewer.refresh();
				if (songToPlay.getPlays() > 3) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Message");
					alert.setContentText("Reached the limit!");
					alert.showAndWait();
				} else {
					// Add song to queue list
					list.queueSong(songToPlay);
					curStud.setPlayedToday(curStud.getPlayedToday() + 1);
					logFirts.setText(curStud.getPlayedToday() + "   " + curStud.updateTimeAllowed(songToPlay));

					songsForList.add(songToPlay);
					listViewSongs.refresh();
				}
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
		listViewSongs = new ListView<Song>();
		listViewSongs.setItems(songsForList);
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
		curStud = new Student(name, passW);
		studCollect = new StudentCollection();
		album = new SongCollection();
		list = new TrackList();
		curStud = null;
		songToPlay = null;
	}

	public static ObservableList<Song> getSongsForList() {
		return songsForList;
	}

	public static ListView<Song> getListViewSongs() {
		return listViewSongs;
	}

}