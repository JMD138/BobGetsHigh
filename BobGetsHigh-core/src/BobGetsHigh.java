
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
import org.json.simple.parser.ParseException;



public class BobGetsHigh // implements KeyListener 
{
	private Location currentLocation;
    private ArrayList<Event> events;
    private int sobrietyLevel = 100;
    private JFrame frame;
    private JLabel sobrietyMeter;
    private JPanel content;
    private JTextArea storyText;
    private BufferedImage image;
    private Event currentEvent;
    private int button2X;
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 40; 
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
        storyText = new JTextArea(currentEvent.getEventText()); //Set text equal to current event text
        storyText.setForeground(Color.WHITE); //Set text color
        storyText.setBackground(Color.MAGENTA);
        storyText.setLineWrap(true);
        storyText.setWrapStyleWord(true);
        storyText.setEditable(false);
        storyText.setBounds(frame.getWidth()/2,frame.getHeight()/2,100,100);
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
		generateEvents();
		generateLocations();
	}
		
	public ArrayList<Location> generateLocations()
	{
		
		Location location1, location2, location3, location4, location5, 
		location6, location7, location8, location9, location10;
		
		// TO ADD: switch block to create the events necessary
		// for the specific locations
		location1 = new Location(1, "Inside the Apartment", /*[events.get(0), events.get(1), events.get(2)]*/null);
		location2 = new Location(2, "Outside the Apartment", null);
		location3 = new Location(3, "Frat House", null);
		location4 = new Location(4, "Class", null);
		location5 = new Location(5, "Cafeteria", null);
		location6 = new Location(6, "Gym", null);
		location7 = new Location(7, "Stadium", null);
		location8 = new Location(8, "Garage", null);
		location9 = new Location(9, "Bar", null);
		location10 = new Location(10, "Car", null);
		
		ArrayList<Location> s = new ArrayList<Location>();
		s.add(location1);
		s.add(location2);
		s.add(location3);
		s.add(location4);
		s.add(location5);
		s.add(location6);
		s.add(location7);
		s.add(location8);
		s.add(location9);
		s.add(location10);
		
		return s;
	}
		
	public void generateEvents()
	{
		ArrayList<Event> e = new ArrayList<Event>();
			JSONParser parser = new JSONParser();
			 JSONArray a = null;
			try {
				a = (JSONArray) parser.parse(new FileReader("assets/events.json"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			for (Object o : a)
			  {
			    JSONObject event = (JSONObject) o;
			    
			    String eventText = (String) event.get("eventText");
			    String [] options = ((String) event.get("options")).split(",");
				String [] optionResults = ((String) event.get("optionResults")).split(",");
				String [] sobrietyResults = ((String) event.get("sobrietyResults")).split(",");
			    e.add(new Event(eventText, options, optionResults, sobrietyResults));
			  }
		currentEvent = e.get(0);
		events = e;
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
		if (sobrietyLevel <= 0)
		{
			sobrietyMeter.setText("Sobriety Level: 0%");
			gameOver();
		}
		else
		{
			sobrietyMeter.setText("Sobriety Level: " + sobrietyLevel + "%");
			updateStoryText("This is a test of the emergency broadcast system");
			frame.repaint();
		}
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
		    	//result.setText(currentEvent.getOptionResults(0));
		    	updateSobrietyLevel(currentEvent.getSobrietyResults(0));
		    	updateStoryText(currentEvent.getOptionResults(0));

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
		    	//result.setText(currentEvent.getOptionResults(1));
		    	updateSobrietyLevel(currentEvent.getSobrietyResults(1));
		    	updateStoryText(currentEvent.getOptionResults(1));
		    }
		});
		
		//Invisible Button 
		JButton invisibleButton = new JButton();
		invisibleButton.setBounds(0, 0, 0, 0); //int x, int y, int width, int height
		invisibleButton.setVisible(false);
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
	public void gameOver()
	{
		updateStoryText("You got arrested. You must have been REALLY high..."
						+ " Press ESC to exit");
		frame.repaint();
	}
}
