//Thank you Subtle Patterns for our background image
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import java.lang.reflect.Method;
import java.net.*;

import javax.imageio.*;
import javax.swing.Timer;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import java.applet.*;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class BobGetsHigh
{
    private ArrayList<Event> events;
    private ArrayList<Location> locations;
    private ArrayList<JButton> locationButtons;
    private int sobrietyLevel = 100;
    private JFrame frame;
    private JLabel sobrietyMeter;
    private JPanel content;
    private JTextArea storyText;
    private BufferedImage image;
    private JLabel image_pane;
	private Location currentLocation;
    private Event currentEvent;
    private int button2X;
    private int button3X;
    private static final int BUTTON_WIDTH = 350;
    private static final int BUTTON_HEIGHT = 40; 
    private int buttonY;
    private int button1X;
    private JButton locationButton;
    private JButton b1;
    private JButton b2;
    private JButton nextButton;
    private JButton invisibleButton;
    private static String OS = System.getProperty("os.name").toLowerCase();
    private Pane pane;
    
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
		
	}
	
	public void makeFrame()
	{
		
		frame = new JFrame("Bob Gets High");
		GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        GraphicsDevice myDevice = ge.getDefaultScreenDevice();
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
      
        
        frame.setLocation(x, y); //Set Frame Location
        frame.setResizable(false); //Frame is not resizable
        frame.setAlwaysOnTop(true);
        frame.setUndecorated(false);
        frame.setVisible(false); //Make visible
        
        
        if(myDevice.isFullScreenSupported())
        {
        	frame.dispose();
        	frame.setUndecorated(true);
        	myDevice.setFullScreenWindow(frame);
        }
        frame.setVisible(true);
        
        //Create a style story text
        storyText = new JTextArea(""); //Set text equal to current event text
        storyText.setForeground(new Color (43,46,19,255)); //Set text color
        storyText.setBackground(new Color(0, 0, 0, 0));
        storyText.setOpaque(false);
        storyText.setLineWrap(true);
        storyText.setWrapStyleWord(true);
        storyText.setEditable(false);
        storyText.setFont(new Font("Arial", Font.BOLD, 16));
        storyText.setBounds(frame.getWidth()/4,frame.getHeight()/8*3,frame.getWidth()/2,frame.getHeight()/4);
        frame.add(storyText); //Add to the frame
        
        //Quit Event
		Action exit = new AbstractAction()
		{
			public void actionPerformed (ActionEvent e)
			{
				System.exit(0);
        	}
		};
		
		//Toggle Windowed and Fullscreen Event
		Action windowed = new AbstractAction()
		{
			public void actionPerformed (ActionEvent e)
			{
				frame.setVisible(false);
				if(myDevice.getFullScreenWindow() == frame)
				{
					frame.setAlwaysOnTop(false);	// this line NEEDS TO BE before frame.dispose();
					frame.dispose();
					frame.setLocationRelativeTo(null);
		        	frame.setUndecorated(false);
					
				}
				else
				{
					frame.dispose();
		        	frame.setUndecorated(true);
		        	frame.setAlwaysOnTop(true);
					myDevice.setFullScreenWindow(frame);
				}
				frame.setVisible(true);
				frame.repaint();
        	}
		};
				
		//Toggle Window Mode on ESC
		storyText.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "esc");
		storyText.getActionMap().put("esc", windowed);
		
		//Quit on Q
		storyText.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0), "quit");
		storyText.getActionMap().put("quit", exit);
		frame.repaint();
		
		buttonY = (int)(frame.getHeight()/4*3);
		button1X = (int)(frame.getWidth()/3-(BUTTON_WIDTH/2));
		button2X = (int)((frame.getWidth()/3)*2-(BUTTON_WIDTH/2));
		button3X = (int)((frame.getWidth()/2)-(BUTTON_WIDTH/2));
		
		
		//Code for adding splash screen image
		image_pane = new JLabel();
	    frame.add(image_pane);
        try
        {
            image = ImageIO.read(new File("assets/images/groupImage.png"));
            Image imageScaled = image.getScaledInstance(frame.getWidth(), frame.getWidth()*image.getHeight()/image.getWidth(),Image.SCALE_SMOOTH);
            image_pane.setIcon(new ImageIcon(imageScaled));
        }
        catch (Exception e)
        {}       

        
		//Center Image
       image_pane.setHorizontalAlignment((frame.getWidth()-image_pane.getWidth())/2);
       image_pane.setVerticalAlignment((frame.getHeight()-image_pane.getHeight())/2);
       frame.repaint();
		
       
       //Thank you:http://albertattard.blogspot.com/2008/09/practical-example-of-swing-timer.html
       //Timer code for splash screen
		ActionListener listener = new ActionListener(){
		  public void actionPerformed(ActionEvent event){
			  //When timer runs out remove image and add sobriety meter/set map
			  frame.remove(image_pane);
				storyText.setText("You are Bob, a computer science major at College Tech University. \nGo about your daily life and remember: \n\n\"Stay cool, Stay in School\"");
			  	pane = new Pane();
		        frame.add(pane);
		        frame.repaint();
		        ActionListener listener2 = new ActionListener(){
					  public void actionPerformed(ActionEvent event){
						  
						  storyText.setText("Bob Gets High");
					      storyText.setFont(new Font("Arial", Font.BOLD, 50));
						ActionListener listener2 = new ActionListener(){
							  public void actionPerformed(ActionEvent event){
								  storyText.setFont(new Font("Arial", Font.BOLD, 16));
									storyText.setText("");
									sobrietyMeter = new JLabel("Sobriety Level: " + sobrietyLevel + "%");
									sobrietyMeter.setVerticalTextPosition(AbstractButton.CENTER);
									sobrietyMeter.setHorizontalTextPosition(AbstractButton.LEADING);
									sobrietyMeter.setFont(new Font("Arial", 1, 18));
									sobrietyMeter.setBounds(20, 15, 200, 30);
									sobrietyMeter.setForeground(new Color (43,46,19,255));
									frame.add(sobrietyMeter);
								  setDisplay("Map"); 
							  }
						};
						//Timer for Intro Text
						Timer displayTimer2 = new Timer(2500, listener2); //set value timer here
						displayTimer2.start();
						displayTimer2.setRepeats(false);
						
					  }
				};
				
				//Timer for Title Text
				Timer displayTimer2 = new Timer(5000, listener2); //set value timer here
				displayTimer2.start();
				displayTimer2.setRepeats(false);
		  }
		};
		
		//Timer for Splash Screen
		Timer displayTimer = new Timer(3000, listener); //set value timer here
		displayTimer.start();
		displayTimer.setRepeats(false);
		
		
	}
    
	public void makeButtons()
	{
		
		b1 = new JButton(currentEvent.getOptions(0));
		b1.setForeground(new Color (43,46,19,255));
		b1.setVerticalTextPosition(AbstractButton.CENTER);
		b1.setHorizontalTextPosition(AbstractButton.LEADING);
		b1.setBounds(button1X,buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		frame.add(b1);
		frame.repaint();
		
		b2 = new JButton(currentEvent.getOptions(1));
		b2.setForeground(new Color (43,46,19,255));
		b2.setVerticalTextPosition(AbstractButton.CENTER);
		b2.setHorizontalTextPosition(AbstractButton.LEADING);
		b2.setBounds(button2X, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT); //int x, int y, int width, int height
		frame.add(b2);
		frame.repaint();
		
		b1.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) 
		    {
		    	updateButtons(0);
		    }
		});
	
		
		b2.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) 
		    {
		    	updateButtons(1);
		    }
		});

		
		//Invisible Button 
		invisibleButton = new JButton();
		invisibleButton.setBounds(0, 0, 0, 0); //int x, int y, int width, int height
		invisibleButton.setOpaque(false);
		invisibleButton.setVisible(false);
		frame.add(invisibleButton);
		frame.repaint();
	}
	
	public void makeMap(){
		locationButtons = new ArrayList<JButton>();
		int spacing = frame.getHeight()/2/locations.size();
		for(int i = 0; i < locations.size(); i++){
			Location selectedLocation = locations.get(i);
			Event selectedEvent =  locations.get(i).getEvents().get(randInt(0,locations.get(i).getEvents().size()-1));
			locationButtons.add(new JButton(locations.get(i).getLocationName()));
			locationButtons.get(i).setVerticalTextPosition(AbstractButton.CENTER);
			locationButtons.get(i).setHorizontalTextPosition(AbstractButton.LEADING);
			locationButtons.get(i).setForeground(new Color (43,46,19,255));
			locationButtons.get(i).setBounds(button3X,frame.getHeight()/4+(spacing*i), BUTTON_WIDTH, BUTTON_HEIGHT);
			locationButtons.get(i).addActionListener(new ActionListener() {          
				public void actionPerformed(ActionEvent e) 
			    {
			    	currentLocation = selectedLocation;
			    	currentEvent = selectedEvent;
			    	setDisplay("Event");
			    }
			});
			frame.add(locationButtons.get(i));
		}
		invisibleButton = new JButton();
		invisibleButton.setBounds(0, 0, 0, 0); //int x, int y, int width, int height
		invisibleButton.setVisible(false);
		
		frame.add(invisibleButton);
		frame.repaint();
	}

	
	
	public void generateGame()
	{
		sobrietyLevel = 100;
		generateEvents();
		generateLocations();
	}

	
	public void generateLocations()
	{
		
		Location location0, location1, location2, location3, 
		location4, location5, location6, location7, location8;
		
		// TO ADD: switch block to create the events necessary
		// for the specific locations
		location0 = new Location("Your Apartment", new ArrayList<Event>(
						Arrays.asList(events.get(0), events.get(9))));
		
		location1 = new Location("The Frat House", new ArrayList<Event>(
						Arrays.asList(events.get(4), events.get(12))));
		
		location2 = new Location("Class", new ArrayList<Event>(
						Arrays.asList(events.get(1), events.get(10))));
		
		location3 = new Location("The Cafeteria", new ArrayList<Event>(
						Arrays.asList(events.get(2), events.get(11))));
		
		location4 = new Location("The Gym", new ArrayList<Event>(
						Arrays.asList(events.get(3),events.get(18))));
		
		location5 = new Location("The Stadium", new ArrayList<Event>(
						Arrays.asList(events.get(5), events.get(13))));
		
		location6 = new Location("The Garage", new ArrayList<Event>(
						Arrays.asList(events.get(6), events.get(15))));
		
		location7 = new Location("The Bar", new ArrayList<Event>(
						Arrays.asList(events.get(7), events.get(14))));
		
		location8 = new Location("The Car", new ArrayList<Event>(
						Arrays.asList(events.get(8),events.get(17))));
		
		locations = new ArrayList<Location>
		(
			Arrays.asList
			(
				location0, location1, location2, location3, location4, 
				location5, location6, location7, location8
			)
		);
		currentLocation = locations.get(0);
	}
		
	public void generateEvents()
	{
		events = new ArrayList<Event>();
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
			    String [] options = ((String) event.get("options")).split("~");
				String [] optionResults = ((String) event.get("optionResults")).split("~");
				String [] sobrietyResults = ((String) event.get("sobrietyResults")).split("~");
			    events.add(new Event(eventText, options, optionResults, sobrietyResults));
			  }
		currentEvent = events.get(3);
	}
	
	public void updateSobrietyLevel(int num)
	{
		sobrietyLevel += num;
		if (gameOver())
		{
			sobrietyLevel = 0;
			sobrietyMeter.setText("Sobriety Level: 0%");
			gameOver();
		}
		else if(sobrietyLevel >= 100){
			sobrietyLevel = 100;
			sobrietyMeter.setText("Sobriety Level: 100%");
		}
		else
		{
			sobrietyMeter.setText("Sobriety Level: " + sobrietyLevel + "%");
			frame.repaint();
		}
	}
	
	public void updateStoryText(String text){
		storyText.setText(text);
	}

	public void updateButtons(int n)
	{	
		frame.remove(pane);
		if (n <= 1)
		{
			frame.remove(b1);
			frame.remove(b2);
			frame.remove(pane);
			
			updateStoryText(currentEvent.getOptionResults(n));
			updateSobrietyLevel(currentEvent.getSobrietyResults(n));
			
			if(!gameOver())
			{
				nextButton = new JButton("Continue");
				nextButton.setForeground(new Color (43,46,19,255));
				nextButton.setVerticalTextPosition(AbstractButton.CENTER);
				nextButton.setHorizontalTextPosition(AbstractButton.LEADING);
				nextButton.setBounds(button3X, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
			
				nextButton.addActionListener(new ActionListener() {          
			    	public void actionPerformed(ActionEvent e) 
			    	{
			    		updateButtons(2);
			    	}
				});
			
				frame.add(nextButton);
		        frame.repaint();
			}
			
			frame.remove(invisibleButton);
			invisibleButton = new JButton();
			invisibleButton.setBounds(0, 0, 0, 0); //int x, int y, int width, int height
			invisibleButton.setVisible(false);
			
			frame.add(invisibleButton);
			frame.repaint();
		}
		if(n == 2)
		{
			setDisplay("Map");
			frame.remove(nextButton);
			frame.remove(invisibleButton);
			invisibleButton = new JButton();
			invisibleButton.setBounds(0, 0, 0, 0); //int x, int y, int width, int height
			invisibleButton.setVisible(false);
			
			frame.add(invisibleButton);
			frame.repaint();
		}
		frame.add(pane);
		frame.repaint();
	}

	

	public boolean gameOver()
	{
		if(sobrietyLevel <= 0)
		{
			updateStoryText("Game Over"+"\n\nYou became addicted to drugs and stopped going to class."
						+ "\nPress 'Q' to quit");
			frame.repaint();
			return true;
		}
		return false;
	}
	
	
	//Method for setting what is displayed (Event/Map)
	public void setDisplay(String mode)
	{
		if(mode == "Event")
		{
			frame.remove(pane);
			//Remove Location Buttons
			for(int i = 0; i < locations.size(); i++){
				frame.remove(locationButtons.get(i));
			}
			//Reset Text bounds
	        storyText.setBounds(frame.getWidth()/4,frame.getHeight()/8*3,frame.getWidth()/2,frame.getHeight()/4);
			//Update Text to current event
	        updateStoryText(currentEvent.getEventText());
	        //Make the buttons
			makeButtons();
			sobrietyMeter.setBounds(20, 15, 200, 30);
//		  	pane = new Pane();
	        frame.add(pane);
			frame.repaint();
		}
		else if(mode == "Map"){
			frame.remove(pane);
			//Clear story Text
			updateStoryText("");
			//Remove Text
			frame.remove(storyText);
			//Add location buttons
			makeMap();
			frame.repaint();
			//Add back text
			frame.add(storyText);
			sobrietyMeter.setBounds(20, 15, 200, 30);
		  	//pane = new Pane();
	        frame.add(pane);
	        frame.repaint();
		}
		
	}
	
	//Thank you: http://www.mkyong.com/java/how-to-detect-os-in-java-systemgetpropertyosname/
	public static boolean isWindows() {
		 
		return (OS.indexOf("win") >= 0);
 
	}
 
	public static boolean isMac() {
 
		return (OS.indexOf("mac") >= 0);
 
	}
 
	public static boolean isUnix() {
 
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
 
	}
 
	public static boolean isSolaris() {
 
		return (OS.indexOf("sunos") >= 0);
 
	}
	
	//Thank you: http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
 
}
