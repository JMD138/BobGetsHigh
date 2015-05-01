
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import java.net.*;

import javax.imageio.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class BobGetsHigh // implements KeyListener 
{
	private SubArea currentSubArea;
	private SuperArea currentSuperArea;
    private ArrayList<String> inventory;
    private int sobrietyLevel = 100;
    private JFrame frame;
    private JPanel content;
    private BufferedImage image;
    private int currentEvent = 0;
//    private JTextArea backgroundPane;
//    private Color backgroundColor;
    
	public static void main(String[] args)
	{
		BobGetsHigh game = new BobGetsHigh();
	}
		
	
	public BobGetsHigh()
	{
		generateGame();
		makeFrame();
		makeButton();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Bob Gets High");
		frame.setTitle("Bob Gets High");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = (JPanel)frame.getContentPane();
        JLabel emptyLabel = new JLabel("");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        frame.setSize((int)dimension.getWidth(), (int)dimension.getHeight());
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLocation(x, y);
        
//        backgroundPane = new JTextArea(0, 100);
//        backgroundColor = new Color(0,0,0);
//        backgroundPane.setBackground(backgroundColor);
//        backgroundPane.setEditable(false);
//        content.add(backgroundPane, BorderLayout.LINE_START);
        
        //initialize KeyListener
//        backgroundPane.addKeyListener(this);
        frame.setResizable(false);
        frame.setUndecorated(true); 
        frame.setVisible(true);
      //initialize KeyListener
        frame.addKeyListener(new KeyListener() 
        {
        	public void keyPressed(KeyEvent e) {}
        	public void keyTyped(KeyEvent e) {}

        	@Override
        	public void keyReleased(KeyEvent e) 
        	{
        		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        		{
        			System.exit(0);
        		}
        	}
        });
	}

	public void generateGame()
	{
		SuperArea superA1, superA2, superA3;
		superA1 = new SuperArea(0, "Bob Gets High, Part 1", generateSubAreas(1));
		superA2 = new SuperArea(1, "Bob Gets High, Part 2", generateSubAreas(2));
		superA3 = new SuperArea(2, "Bob Gets High, Part 3", generateSubAreas(3));
	}
		
	public ArrayList<SubArea> generateSubAreas(int n)
	{
		SubArea subA1, subA2, subA3;
		
		// TO ADD: switch block to create the SubAreas necessary for the specific SuperArea (identified by int n)
		subA1 = new SubArea(0, "Inside the Apartment", generateEvents(1));
		subA2 = new SubArea(1, "Outside the Apartment", generateEvents(2));
		subA3 = new SubArea(2, "At the Frat House", generateEvents(3));
		
		ArrayList<SubArea> s = new ArrayList<SubArea>();
		s.add(subA1);
		s.add(subA2);
		s.add(subA3);
		
		return s;
	}
		
	public ArrayList<Event> generateEvents(int n)
	{
		String [] options = {"Yes", "No"};
		String [] optionResults = {"You Lose", "You Win"};
		Event event1, event2, event3;
		
		// TO ADD: switch block to create the Events necessary for the specific subArea (identified by int n)
		event1 = new Event(0,"Want to do some drugs Bob?", options, optionResults);
		event2 = new Event(1,"Event 2", options, optionResults);
		event3 = new Event(2,"Event 3", options, optionResults);
		
		ArrayList<Event> e = new ArrayList<Event>();
		e.add(event1);
		e.add(event2);
		e.add(event3);
		
		return e;
	}

	
	private void closeWindow(){
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		System.exit(0);
	}
	
	private void windowedMode(){
		frame.setUndecorated(false);
	}
	

	public void updateSobrietyLevel(int num){
		sobrietyLevel += num;
	}


	public void makeButton()
	{
		//ImageIcon leftButtonIcon = createImageIcon("images/right.gif");
		//ImageIcon rightButtonIcon = createImageIcon("images/right.gif");
		int buttonWidth = 100;
		int buttonHeight = 50;
		int buttonY = (int)(frame.getHeight()/4*3);
		int button1X = (int)(frame.getWidth()/3-(buttonWidth/2));
		int button2X = (int)((frame.getWidth()/3)*2-(buttonWidth/2));
		
		JButton b1 = new JButton("Option1");
		b1.setVerticalTextPosition(AbstractButton.CENTER);
		b1.setHorizontalTextPosition(AbstractButton.LEADING);
		b1.setBounds(button1X,buttonY, buttonWidth, buttonHeight);
<<<<<<< HEAD
<<<<<<< HEAD
		panel1.add(b1);
		panel1.repaint();
=======
		frame.add(b1);
		frame.repaint();
		
>>>>>>> origin/master
=======
		frame.add(b1);
		
>>>>>>> parent of 07bbddf... tried to add buttons to JPanel
		
		JButton b2 = new JButton("Option2");
		b2.setVerticalTextPosition(AbstractButton.CENTER);
		b2.setHorizontalTextPosition(AbstractButton.LEADING);
		b2.setBounds(button2X, buttonY, buttonWidth, buttonHeight); //int x, int y, int width, int height
<<<<<<< HEAD
<<<<<<< HEAD
		panel2.add(b2);
		panel2.repaint();
=======
		frame.add(b2);
		frame.repaint();
>>>>>>> origin/master
=======
		frame.add(b2);
>>>>>>> parent of 07bbddf... tried to add buttons to JPanel
	}
	
	/*public static ImageIcon createImageIcon(String path) {
		URL imgURL = JButton.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find image in system: " + path);
			return null;
		}*/
	
	
}
