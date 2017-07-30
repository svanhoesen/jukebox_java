package controller_view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.JukeBox;
import model.Song;
import model.SongCollection;
import model.Student;

public class JukeBoxGUI extends JFrame implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6884721064676675258L;

	private JLabel idLabel, pwdLabel, userAvailableTimeLabel, userAvailableSongsLabel, currentPlayingLabel,
			selectionPromptLabel;
	private JTextField idTextField;
	private JPasswordField pwdTextField;
	private JButton loginBtn, logoutBtn, addButton;
	private JTable table;
	private DefaultListModel<String> playingSongs;
	private JList<String> displayList;

	private JukeBox box = new JukeBox();
	private SongCollection songCollection = new SongCollection();

	public JukeBoxGUI(int decision) {
		if (decision == JOptionPane.YES_OPTION)
			readInputFile();

		idLabel = new JLabel();
		pwdLabel = new JLabel();
		userAvailableTimeLabel = new JLabel();
		userAvailableSongsLabel = new JLabel();
		currentPlayingLabel = new JLabel();
		selectionPromptLabel = new JLabel();
		idTextField = new JTextField();
		pwdTextField = new JPasswordField();
		loginBtn = new JButton();
		logoutBtn = new JButton();

		initialization();
		registerListener();
	}

	private void initialization() {
		setTitle("Juke Box");
		idLabel.setText("Student ID:");
		pwdLabel.setText("Password:");
		loginBtn.setText("Login");
		logoutBtn.setText("Logout");
		currentPlayingLabel.setText("Current Playing:");
		selectionPromptLabel.setText("Select song below and press green arrow to play");
		setSize(1250, 700);

		setLocationRelativeTo(null);

		setLayout(null);

		// Locate ID Label
		idLabel.setSize(100, 20);
		idLabel.setLocation(20, 550);
		add(idLabel);

		// Locate ID TextField
		idTextField.setSize(150, 20);
		idTextField.setLocation(100, 550);
		add(idTextField);

		// Locate password Label
		pwdLabel.setSize(100, 20);
		pwdLabel.setLocation(20, 580);
		add(pwdLabel);

		// Locate password textField
		pwdTextField.setSize(150, 20);
		pwdTextField.setLocation(100, 580);
		add(pwdTextField);

		// Locate login button
		loginBtn.setSize(227, 20);
		loginBtn.setLocation(20, 610);
		add(loginBtn);

		// Locate logout button
		logoutBtn.setSize(227, 20);
		logoutBtn.setLocation(20, 610);
		logoutBtn.setVisible(false);
		add(logoutBtn);

		userAvailableSongsLabel.setSize(300, 20);
		userAvailableSongsLabel.setLocation(20, 550);
		add(userAvailableSongsLabel);

		userAvailableTimeLabel.setSize(500, 20);
		userAvailableTimeLabel.setLocation(20, 580);
		add(userAvailableTimeLabel);

		currentPlayingLabel.setSize(300, 20);
		currentPlayingLabel.setLocation(20, 20);
		add(currentPlayingLabel);

		selectionPromptLabel.setSize(400, 20);
		selectionPromptLabel.setLocation(675, 20);
		add(selectionPromptLabel);

		setUpSongCollectionJTable();
		setUpSongCollectionScrollPane();
		setUpPlayButton();

		setUpJListPlayList();
	}

	public void registerListener() {
		loginBtn.addActionListener(new LoginButtonListener());
		logoutBtn.addActionListener(new LogoutButtonListener());
		addButton.addActionListener(new PlayingButtonListener());
		addWindowListener(new CloseListener());
		box.addObserver(this);
	}

	public void setUpSongCollectionJTable() {
		table = new JTable(songCollection);
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(songCollection);
		table.setRowSorter(sorter);
	}

	public void setUpSongCollectionScrollPane() {
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setSize(550, 400);
		scrollPane.setLocation(675, 45);
		add(scrollPane, BorderLayout.CENTER);
	}

	public void setUpPlayButton() {
		addButton = new JButton();
		addButton.setSize(40, 32);
		addButton.setLocation(540, 140);
		ImageIcon img = new ImageIcon("play.png");
		addButton.setIcon(img);
		addButton.setBorderPainted(false);
		addButton.setContentAreaFilled(false);
		addButton.setFocusPainted(false);
		addButton.setOpaque(false);
		add(addButton);
	}

	private void setUpJListPlayList() {
		playingSongs = new DefaultListModel<String>();
		for (Song song : box.getTrackList())
			playingSongs.addElement(song.toString());

		displayList = new JList<String>();
		displayList.setModel(playingSongs);

		displayList.setSize(400, 400);
		displayList.setLocation(20, 45);
		add(displayList);

//		if (!box.getTrackList().isEmpty())
//			box.play();
	}

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
	public void update(Observable o, Object arg) {
		updatePlayList();
	}

	public void showMe() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	public void updatePlayList() {
		playingSongs.clear();
		for (Song song : box.getTrackList())
			playingSongs.addElement(song.toString());
		displayList.setModel(playingSongs);
	}

	private class PlayingButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
//			if (idTextField.isEnabled())
//				JOptionPane.showMessageDialog(null, "Please login");
//			else {
//				int viewRow = table.getSelectedRow();
//				if (viewRow == -1)
//					JOptionPane.showMessageDialog(null, "Please select song");
//				else {
//					Song selected = ((SongCollection) songCollection).get(table.convertRowIndexToModel(viewRow));
//					if (!box.canStudentPlay()) {
//						JOptionPane.showMessageDialog(null, "User can play 3 songs at most each day");
//					} else if (!box.addSong(selected))
//						JOptionPane.showMessageDialog(null, "This song has been chosen 3 times today");
//					else {
//						updatePlayList();
//						updateUserStatus(selected);
//						if (playListIsEmpty())
//							box.play();
//					}
//				}
//
//			}
		}

//		private void updateUserStatus(Song song) {
//			box.getCurrentStudent().addPlayed(song);
//			userAvailableSongsLabel.setText(box.getCurrentStudent().getSongAllowed());
//			userAvailableTimeLabel.setText(box.getCurrentStudent().getTimeAllowed());
//		}

		private boolean playListIsEmpty() {
			return box.getTrackList().size() == 1;
		}
	}

	private class LoginButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
//			if (idTextFieldIsEmpty())
//				JOptionPane.showMessageDialog(null, "Please enter Student ID");
//			else if (passwordFieldIsEmpty())
//				JOptionPane.showMessageDialog(null, "Please enter password");
//			else if (loginMatched()) {
//				box.setCurrentUserName(idTextField.getText());
//				showUserInfor();
//				hideLogin();
//			} else
//				JOptionPane.showMessageDialog(null, "Wrong ID or passwrod, please check");
		}
		
		private boolean idTextFieldIsEmpty(){
			return idTextField.getText().equalsIgnoreCase("");
		}
		
		private boolean passwordFieldIsEmpty(){
			return pwdTextField.getPassword().length == 0;
		}

		private boolean loginMatched() {
			return box.validate(idTextField.getText(), new String(pwdTextField.getPassword()));
		}

//		private void showUserInfor() {
//			userAvailableTimeLabel.setVisible(true);
//			userAvailableSongsLabel.setVisible(true);
//			userAvailableSongsLabel.setText(box.getCurrentStudent().getSongAllowed());
//			userAvailableTimeLabel.setText(box.getCurrentStudent().getTimeAllowed());
//		}

		private void hideLogin() {
			idTextField.setText("");
			pwdTextField.setText("");
			idLabel.setVisible(false);
			idTextField.setVisible(false);
			idTextField.setEnabled(false);
			pwdLabel.setVisible(false);
			pwdTextField.setVisible(false);
			pwdTextField.setEnabled(false);
			loginBtn.setVisible(false);
			logoutBtn.setVisible(true);
		}

	}

	private class LogoutButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			hideUserInfo();
			showLogin();
			idTextField.setText("");
			pwdTextField.setText("");
			idTextField.setEnabled(true);
			pwdTextField.setEnabled(true);

		}

		private void hideUserInfo() {
			userAvailableTimeLabel.setText("");
			userAvailableSongsLabel.setText("");
			userAvailableTimeLabel.setVisible(false);
			userAvailableSongsLabel.setVisible(false);

		}

		private void showLogin() {
			idLabel.setVisible(true);
			idTextField.setVisible(true);
			idTextField.setEnabled(true);
			pwdLabel.setVisible(true);
			pwdTextField.setVisible(true);
			pwdTextField.setEnabled(true);
			loginBtn.setVisible(true);
			logoutBtn.setVisible(false);
		}
	}

	private class CloseListener implements WindowListener {

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

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				int decision = JOptionPane.showConfirmDialog(null, "Recover Last Stage?");
				new JukeBoxGUI(decision).showMe();
			}
		});
	}

}
