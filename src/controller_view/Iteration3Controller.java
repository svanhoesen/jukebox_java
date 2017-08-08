package controller_view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Admin;
import model.Song;
import model.SongCollection;
import model.Student;
import model.StudentCollection;
import model.TrackList;

public class Iteration3Controller extends Application implements Serializable {

	public static void main(String[] args) {
		launch(args);
	}

	private static SongView songViewer;
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
	private Admin admin;
	private Song songToPlay;
	private TrackList list;
	private static ObservableList<Song> songsForList = FXCollections.observableArrayList();
	private static ListView<Song> listViewSongs;
	private Song selectedSong;
	private Stage primaryS;
	private Path temp = new Path();

	@Override
	public void start(Stage primaryStage) throws Exception {
		setup();

		handleLoadIn();

		BorderPane all = new BorderPane();

		Scene scene = new Scene(all, 780, 500);
		primaryStage.setScene(scene);

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

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
		handleSave(primaryStage);
		setUpHandler();
		logOut();

		all.setCenter(grid);

		// Don't forget to show the running application:
		primaryStage.show();
	}

	private void handleLoadIn() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Start Up Option");
		alert.setHeaderText("Start with saved state?");
		alert.setContentText("Press ok load saved state.");
		Optional<ButtonType> result = alert.showAndWait();

		// TODO: Either read the saved student collection or start with default
		if (result.get().equals(ButtonType.OK)) {
			readCollection();
//			songsForList = read(temp);
		}
	}

	private void handleAdminPage() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Load User Page");
		alert.setHeaderText("Do you want to make admin changes?");
		alert.setContentText("Press ok load admin page.");
		Optional<ButtonType> result = alert.showAndWait();

		// TODO: Either read the saved student collection or start with default
		if (result.get().equals(ButtonType.OK)) {
			try {
				admin.start(primaryS);
				primaryS.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			curStud = studCollect.get(name);
			logFirts.setText(curStud.getPlayedToday() + "       " + curStud.getTimeAllowed());
		}
	}

	private void handleSave(Stage primaryStage) {
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				// Write the song collection to a file
				writeCollection();
//				write(songsForList);
			}
		});
	}

	private void login() {
		login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				name = textFieldAccn.getText();
				passW = textFieldPW.getText();

				if (studCollect.validateStudent(name, passW) && name.equals("Admin")) {
					handleAdminPage();
				} else if (studCollect.validateStudent(name, passW)) {
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
				selectedSong = (Song) songViewer.getSelectionModel().getSelectedItem();
				// Pass the selected song
				songToPlay = selectedSong;
				// Set count of times song played
				songToPlay.setPlays(songToPlay.getPlays() + 1);
				songViewer.refresh();
				// Check song played count
				if (songToPlay.getPlays() > 3) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Message");
					alert.setContentText("Reached the limit!");
					alert.showAndWait();
				} else {
					// Add song to queue list
					list.queueSong(songToPlay);
					curStud.setPlayedToday(curStud.getPlayedToday() + 1);
					logFirts.setText(curStud.getPlayedToday() + "      " + curStud.updateTimeAllowed(songToPlay));

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
				writeCollection();
				textFieldAccn.clear();
				textFieldPW.clear();
				logFirts.setText("Login first");
				currPlay.setText("Waiting to que song...");
				songCount = 0;
			}
		});
	}

	private void writeCollection() {
		try {
			FileOutputStream bytesToDisk = new FileOutputStream("persistentObjects");
			ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);
			// Save the modified student and song collection
			outFile.writeObject(studCollect);
			outFile.writeObject(album);
			outFile.writeObject(selectedSong);
			outFile.writeObject(list);
			outFile.writeObject(songToPlay);
			outFile.writeObject(songsForList);
			outFile.writeObject(listViewSongs);
			outFile.close(); // Always close the output file!
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	@SuppressWarnings("unchecked")
	private void readCollection() {
		try {
			FileInputStream rawBytes = new FileInputStream("persistentObjects");
			ObjectInputStream inFile = new ObjectInputStream(rawBytes);
			// Open the modified student and song collection
			studCollect = (StudentCollection) inFile.readObject();// student
																	// collection
			album.recover((SongCollection) inFile.readObject());// song
																// collection
																// list
			selectedSong = (Song) inFile.readObject();// selected song from song
														// collection
			list = (TrackList) inFile.readObject();// link list of songs to play
			songToPlay = (Song) inFile.readObject();// current song to play
			songsForList = (ObservableList<Song>) inFile.readObject();
			listViewSongs = (ListView<Song>) inFile.readObject();
			inFile.close(); // Always close the input file too! {
		} catch (Exception e) {

		}
	}

	private static void write(ObservableList<Song> songsListOb) {
		try {
			// write object to file
			FileOutputStream fos = new FileOutputStream("persistentObjects");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(new ArrayList<Song>(songsListOb));
			oos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ObservableList<Song> read(Path file) {
		try {
			FileInputStream in = new FileInputStream("persistentObjects");
			ObjectInputStream ois = new ObjectInputStream(in);
			List<Song> list = (List<Song>) ois.readObject();

			return FXCollections.observableList(list);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return FXCollections.emptyObservableList();
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
		admin = null;
		list = new TrackList();
		curStud = null;
		songToPlay = null;
		primaryS = new Stage();
	}

	public static ObservableList<Song> getSongsForList() {
		return songsForList;
	}

	public static ListView<Song> getListViewSongs() {
		return listViewSongs;
	}

}
