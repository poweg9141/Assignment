package guiLauncher;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import coreComponents.GameVariables;

public class OptionsMenu extends JFrame implements ItemListener {
	// here because it has to be
	private static final long serialVersionUID = 1L;

	// creates a string array of all the options for the combo boxes
	private String[] options = { "greenFields", "brickMaze", "bunker", "forest" };
	private String[] timeOfDay = { "night", "day" };
	private String[] player = { "player1", "player2", "player3", "player4", "player5" };
	private String[] difficulty = { "easy", "normal", "hard", "expert" };
	// initializes all the combo boxes
	private JComboBox<String> box, box2, box3, box4;
	//back button
	private JButton back;
	// initializes all the text fields
	JTextField txt1, txt2, txt3, txt4;

	// loads images of all the levels
	BufferedImage fields = loadImage("textures/floors/brownfloor.png");
	BufferedImage maze = loadImage("textures/floors/brownfloor.png");
	BufferedImage bunker = loadImage("textures/floors/brownfloor.png");
	BufferedImage forest = loadImage("textures/floors/brownfloor.png");

	// constructor called once an object of the class is made
	public OptionsMenu() {
		// puts all the options into the combo boxes
		box = new JComboBox<>(options);
		box2 = new JComboBox<>(timeOfDay);
		box3 = new JComboBox<>(player);
		box4 = new JComboBox<>(difficulty);
		back = new JButton();
		// sets values to all the text fields
		txt1 = new JTextField();
		txt2 = new JTextField();
		txt3 = new JTextField();
		txt4 = new JTextField();
		// sets layout to null so we can use absolute positioning
		setLayout(null);
		// gets the dimensions of the combo box
		Dimension boxSize = box.getPreferredSize();
		// sets the bounds of the combo box to its preffered size
		box.setBounds(10, 30, boxSize.width, boxSize.height);
		// sets the bounds of the text field to the preffered size of the
		// corresponding combo box
		txt1.setBounds(10, 10, boxSize.width, boxSize.height);
		// sets the text of the text field
		txt1.setText("Level Name:");
		// makes the text field not editable
		txt1.setEditable(false);
		// repeats above code for all combo boxes and text fields
		Dimension box2Size = box2.getPreferredSize();
		box2.setBounds(10, 95, box2Size.width, box2Size.height);
		txt2.setBounds(10, 70, boxSize.width, boxSize.height);
		txt2.setText("Time Of Day:");
		txt2.setEditable(false);
		Dimension box3Size = box3.getPreferredSize();
		box3.setBounds(10, 160, box3Size.width, box3Size.height);
		txt3.setBounds(10, 135, box3Size.width, box3Size.height);
		txt3.setText("Character:");
		txt3.setEditable(false);
		Dimension box4Size = box4.getPreferredSize();
		box4.setBounds(10, 325, box4Size.width, box4Size.height);
		txt4.setBounds(10, 300, box4Size.width, box4Size.height);
		txt4.setText("Difficulty:");
		txt4.setEditable(false);
		//sets all the back button stuff
		back.setText("back");
		back.setBounds(350, 500, 100, 50);
		back.setVisible(true);
		// adds all the combo boxes and text fields to the JFrame
		add(box);
		add(txt1);
		add(box2);
		add(txt2);
		add(box3);
		add(txt3);
		add(box4);
		add(txt4);
		add(back);
		// sets the size of the frame
		setSize(800, 600);
		setResizable(false);
		// centers the frame on the screen
		setLocationRelativeTo(null);
		// sets the title
		setTitle("Options");
		// sets the default close operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ads item listeners to all the combo boxes in the menu
		box3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getSource() == box3) {
					if(box3.getSelectedItem().equals("player1")){
						GameVariables.playerID = 1;
					}else if(box3.getSelectedItem().equals("player2")){
						GameVariables.playerID = 2;
					}else if(box3.getSelectedItem().equals("player3")){
						GameVariables.playerID = 3;
					}else if(box3.getSelectedItem().equals("player4")){
						GameVariables.playerID = 4;
					}else if(box3.getSelectedItem().equals("player5")){
                                                GameVariables.playerID = 5;
                                        }
				}
			}
		});
                
                box.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getSource() == box) {
					if(box.getSelectedItem().equals("greenFields")){
						GameVariables.level = 1;
					}else if(box.getSelectedItem().equals("brickMaze")){
						GameVariables.level = 2;
					}else if(box.getSelectedItem().equals("bunker")){
						GameVariables.level = 3;
					}else if(box.getSelectedItem().equals("forest")){
						GameVariables.level = 4;
					}
				}
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				new Launcher();
			}
		});
	}

	// method to display images to JFrame
	public void displayImage(BufferedImage img, Graphics g) {

	}

	// method to load images
	public BufferedImage loadImage(String file) {

		// creates a new buffered image
		BufferedImage img = null;

		// try's to load the image from the file passed to it
		try {
			img = ImageIO.read(ClassLoader.getSystemResourceAsStream(file));
			// if it can't, prints out an exception, but does not crash
		} catch (IOException e) {
			e.getStackTrace();
		}

		// returns the loaded image
		return img;
	}

	// in here because it needs to be, does nothing
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub

	}
}
