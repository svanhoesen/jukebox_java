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
	private Button login;
	private Label logFirts;
	private Button logOut;
	private int songCount = 0;
	private String name = "";
	private String passW = "";
	private Student stud = new Student(name, passW);
	private StudentCollection studCollect;
	private SongCollection album;
	private Song song;
	private TrackList list;
	private ObservableList<String> songsForList;
	private ListView<String> listViewSongs;

	@Override
	public void start(Stage primaryStage) throws Exception {

		setup();
		BorderPane all = new BorderPane();
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		Scene scene = new Scene(all, 450, 450);
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

		GridPane.setConstraints(songViewer, 0, 6);
		grid.getChildren().add(songViewer);

		GridPane.setConstraints(buttonGo, 1, 6);
		grid.getChildren().add(buttonGo);

		// action methods
		setUpHandler();
		logOut();

		stud.setUserName(textFieldAccn.getText());
		stud.setPassword(textFieldPW.getText());

		all.setCenter(grid);
		//Show current playlist
		all.setBottom(listViewSongs);

		// Don't forget to show the running application:
		primaryStage.show();
	}

	private void setUpHandler() {
		login.setOnAction(event -> {
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

				// Determine which row is selected
				Song selectedSong = (Song) songViewer.getSelectionModel().getSelectedItem();
				// Pass the selected song
				song = selectedSong;
				songViewer.refresh();
				//Pass the list of all songs
				SongCollection songCollection = songViewer.getList();
				for (int i = 0; i < songCollection.size(); i++) {
					System.out.println(songCollection.get(i).getSongTitle());//just for test
				}
				//Get info from selected list
				songsForList = FXCollections.observableArrayList(songCollection.get(0).getSongTitle());
				ListView<String> listView = new ListView<String>(songsForList);
				listVeiwSong(listView);
				//Handle song media play
				name = textFieldAccn.getText();
				passW = textFieldPW.getText();

				if (studCollect.validateStudent(name, passW) && (songCount < 3)) {
					song = selectedSong;
					list.queueSong(song);
					songCount++;
					logFirts.setText(list.size() + "   " + stud.getTimeAllowed(song));
				}
				if (songCount == 3 || stud.canPlay() == false || song.canBePlayedToday() == false) {
					logFirts.setText("3     " + stud.getTimeAllowed(song));
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Message");
					alert.setContentText("User has reached the limit!");
					alert.showAndWait();
				}
			} else {
				logFirts.setText("Try Again");
			}
		});
	}

	private void listVeiwSong(ListView<String> listViewSongs) {
		listViewSongs.setCellFactory(param -> new ListCell<String>() {
			protected void updateItem(Song song, boolean empty) {
				if (empty || song == null || song.getSongTitle() == null) {
					setText(null);
				} else {
					setText(song.getSongTitle());
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
				songCount = 0;
			}
		});
	}

	private void setup() {
		// TODO Auto-generated method stub
		labelTitle = new Label("Song List");
		songViewer = new SongView();
		buttonGo = new Button("->");
		textFieldPW = new PasswordField();
		textFieldAccn = new TextField();
		accontName = new Label("Accont Name");
		pasword = new Label("Pasword");
		login = new Button("Login");
		logFirts = new Label("Login first");
		logOut = new Button("Log out");
		stud = new Student(name, passW);
		studCollect = new StudentCollection();
		album = new SongCollection();
		list = new TrackList();
	}

}