/**
 * The track of songs that are able to be played
 *
 * @author Steffan Van Hoesen & Anthony Middleton
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SongCollection extends ArrayList<Song> implements TableModel, Serializable {

	public static String baseDirect = System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "songfiles" + System.getProperty("file.separator");

	public SongCollection() {
		this.add(new Song("Pokemon Capture", 5, "Pikachu", baseDirect + "Capture.mp3"));
		this.add(new Song("Danse Macabre", 34, "Kevin MacLeod", baseDirect + "DanseMacabreViolinHook.mp3"));
		this.add(new Song("Determined Tumbao", 20, "FreePlay Music", baseDirect + "DeterminedTumbao.mp3"));
		this.add(new Song("Loping Sting", 5, "Kevin MacLeod", baseDirect + "LopingSting.mp3"));
		this.add(new Song("Swing Cheese", 15, "FreePlay Music", baseDirect + "SwingCheese.mp3"));
		this.add(new Song("The Curtain Rises", 28, "Kevin MacLeod", baseDirect + "TheCurtainRises.mp3"));
		this.add(new Song("Untameable Fire", 282, "Pierre Langer", baseDirect + "UntameableFire.mp3"));
	}

	public void recover(SongCollection songs) {
		this.clear();
		for (Song song : songs)
			this.add(song);
	}

	@Override
	public int getRowCount() {
		return this.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int columnIndex) {
		String[] columnName = { "Artist", "Title", "Seconds" };
		return columnName[columnIndex];
	}

	@Override
	public Class getColumnClass(int columnIndex) {
		if (columnIndex == 0 || columnIndex == 1)
			return String.class;
		else
			return Integer.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Song currentSong = this.get(rowIndex);
		if (columnIndex == 0)
			return currentSong.getArtist();
		else if (columnIndex == 1)
			return currentSong.getSongTitle();
		else
			return currentSong.getTime();
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

	}

	@Override
	public void addTableModelListener(TableModelListener l) {

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {

	}
}