package persistence;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import model.Song;
import model.SongCollection;
import model.Student;
import model.JukeBox;

public class CloseListener implements WindowListener{

	private JukeBox box = new JukeBox();
	private SongCollection songCollection = new SongCollection();
	
	@SuppressWarnings("unchecked")
	public void readInputFile() {
		try {
			FileInputStream rawBytes = new FileInputStream("myPlayList");
			ObjectInputStream inFile = new ObjectInputStream(rawBytes);
			box.setTrackList((ArrayList<Song>) inFile.readObject());
			box.setStudentList((HashMap<String, Student>) inFile.readObject());
			songCollection.recover((SongCollection) inFile.readObject());
			inFile.close();
		} catch (Exception e) {
		}

	}
	
	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		int decision = JOptionPane.showConfirmDialog(null, "Save Current Stage?");
		if (decision == JOptionPane.YES_OPTION) {
			try {
				FileOutputStream bytesToDisk = new FileOutputStream("myPlayList");
				ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);
				// outFile understands the writeObject(Object o) message.
				outFile.writeObject(box.getTrackList());
				outFile.writeObject(box.getUserList());
				outFile.writeObject(songCollection);
				outFile.close(); // Always close the output file!
				System.exit(0);
			} catch (IOException ioe) {
				System.out.println("Writing objects failed");
			}

		} else if (decision == JOptionPane.NO_OPTION)
			System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

}