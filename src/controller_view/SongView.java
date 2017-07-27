package controller_view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Song;
import model.SongCollection;

public class SongView extends TableView {

	private ObservableList<Song> song;
	private SongCollection songCollection;

	@SuppressWarnings("unchecked")
	public SongView() {
		// Add columns and rows
		TableColumn<Song, Integer> plays = new TableColumn<>("Plays");
		TableColumn<Song, String> title = new TableColumn<>("title");
		TableColumn<Song, String> artist = new TableColumn<>("Artist");
		TableColumn<Song, String> time = new TableColumn<>("Time");

		plays.setCellValueFactory(new PropertyValueFactory<Song, Integer>("plays"));
		title.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
		artist.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
		time.setCellValueFactory(new PropertyValueFactory<Song, String>("time"));

		this.getColumns().addAll(plays, title, artist, time);

		plays.setPrefWidth(60);
		title.setPrefWidth(100);
		artist.setPrefWidth(100);
		time.setPrefWidth(60);
		this.setWidth(350);

		// Set the model for this TableView
		songCollection = new SongCollection();
		song = FXCollections.observableArrayList();
		for (int i = 0; i < songCollection.size(); i++) {
			song.add(songCollection.get(i));
		}
		this.setItems(song);

	}

	public SongCollection getList() {
		return songCollection;
	}
}
