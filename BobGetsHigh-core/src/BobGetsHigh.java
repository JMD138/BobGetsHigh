
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
    private JLabel sobrietyMeter;
    private JPanel content;
    private JLabel storyText;
    private BufferedImage image;
    private Event currentEvent;
    private int button2X;
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 50; 
    private int buttonY;
    private int button1X;
    
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
		makeButton(currentEvent.getOptions(0),currentEvent.getOptions(1));
		
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
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2); // X center
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);  //Y center
        frame.getContentPane().setBackground(Color.BLACK);
        
        //Create a style story text
        storyText = new JLabel(currentEvent.getEventText(), JLabel.CENTER); //Set text equal to current event text
        storyText.setForeground(Color.WHITE); //Set text color
        storyText.setLocation((frame.getWidth()/2),frame.getHeight()/2);
        frame.add(storyText); //Add to the frame
        
        frame.setLocation(x, y); //Set Frame Location
        frame.setResizable(false); //Frame is not resizable
        frame.setUndecorated(true);  //No decoration for the frame
        frame.setVisible(true); //Make visible
		
        // create an 'exit' button
		JButton exitButton = new JButton("X");
		exitButton.setVerticalTextPosition(AbstractButton.CENTER);
		exitButton.setHorizontalTextPosition(AbstractButton.LEADING);
		exitButton.setBounds(0, 25, 50, 30);
		    
		// create a new input in the InputMap with a paired Action in the ActionMap
		// so that when the ESCAPE key is pressed, the program ends. If we don't want
		// an exit button, this code can be transferred to another JComponent (that
		// might require some slight alteration).
		
		Action exit = new AbstractAction()
		{
			public void actionPerformed (ActionEvent e)
			{
				System.exit(0);
        	}
		};
				
		exitButton.addActionListener(exit);
		
		exitButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "quit");
		exitButton.getActionMap().put("quit", exit);

		
		frame.add(exitButton);
		frame.repaint();
		
		sobrietyMeter = new JLabel("Sobriety Level: " + sobrietyLevel + "%");
		sobrietyMeter.setVerticalTextPosition(AbstractButton.CENTER);
		sobrietyMeter.setHorizontalTextPosition(AbstractButton.LEADING);
		sobrietyMeter.setFont(new Font("Arial", 1, 18));
		sobrietyMeter.setBounds(frame.getWidth() - 200, 25, 200, 30);
		sobrietyMeter.setForeground(Color.WHITE);
		
		frame.add(sobrietyMeter);
		frame.repaint();
		
		buttonY = (int)(frame.getHeight()/4*3);
		button1X = (int)(frame.getWidth()/3-(BUTTON_WIDTH/2));
		button2X = (int)((frame.getWidth()/3)*2-(BUTTON_WIDTH/2));
	}
	
        
	

	public void generateGame()
	{
		sobrietyLevel = 100;
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
		int [] sobrietyResults = {-100, 0};
		Event event1, event2, event3;
		
		// TO ADD: switch block to create the Events necessary for the specific subArea (identified by int n)
		event1 = new Event(0,"Want to do some drugs Bob?", options, optionResults, sobrietyResults);
		event2 = new Event(1,"Event 2", options, optionResults, sobrietyResults);
		event3 = new Event(2,"Event 3", options, optionResults, sobrietyResults);
		
		ArrayList<Event> e = new ArrayList<Event>();
		e.add(event1);
		e.add(event2);
		e.add(event3);
		
		currentEvent = event1;
		return e;
	}

	
	private void closeWindow(){
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		System.exit(0);
	}
	
	private void windowedMode(){
		frame.setUndecorated(false);
	}
	

	public void updateSobrietyLevel(int num)
	{
		sobrietyLevel += num;
		sobrietyMeter.setText("Sobriety: " + sobrietyLevel + "%");
		frame.repaint();
	}
	
	public void updateStoryText(String text){
		storyText.setText(text);
	}


	public void makeButton(String text1, String text2)
	{
		//ImageIcon leftButtonIcon = createImageIcon("images/right.gif");
		//ImageIcon rightButtonIcon = createImageIcon("images/right.gif");
		
		JButton b1 = new JButton(text1);
		b1.setVerticalTextPosition(AbstractButton.CENTER);
		b1.setHorizontalTextPosition(AbstractButton.LEADING);
		b1.setBounds(button1X,buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		frame.add(b1);
		frame.repaint();
		
		b1.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) 
		    {
		    	System.out.println("Mouse clicked button1");
		    	//result.setText(currentEvent.getOptionResults(0));
		    	updateSobrietyLevel(-10);
		    	updateStoryText("This is a test of the emergency broadcast system");
		    }
		});
	
		JButton b2 = new JButton(text2);
		b2.setVerticalTextPosition(AbstractButton.CENTER);
		b2.setHorizontalTextPosition(AbstractButton.LEADING);
		b2.setBounds(button2X, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT); //int x, int y, int width, int height
		frame.add(b2);
		frame.repaint();
		
		b2.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) 
		    {
		    	System.out.println("Mouse clicked button2");
		    	//result.setText(currentEvent.getOptionResults(1));
		    }
		});
		
		//Invisible Button 
		JButton invisibleButton = new JButton();
		invisibleButton.setBounds(0, 0, 0, 0); //int x, int y, int width, int height
		invisibleButton.setBorderPainted(false);
		frame.add(invisibleButton);
		frame.repaint();
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
