
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class BobGetsHigh // implements KeyListener 
{
    private ArrayList<Event> events;
    private ArrayList<Location> locations;
    private int sobrietyLevel = 100;
    private JFrame frame;
    private JLabel sobrietyMeter;
    private JPanel content;
    private JTextArea storyText;
    private BufferedImage image;
	private Location currentLocation;
    private Event currentEvent;
    private int button2X;
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 40; 
    private int buttonY;
    private int button1X;
    private JButton b1;
    private JButton b2;
    private static String OS = System.getProperty("os.name").toLowerCase();
    
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
        storyText = new JTextArea(currentEvent.getEventText()); //Set text equal to current event text
        storyText.setForeground(Color.WHITE); //Set text color
        storyText.setBackground(Color.BLACK);
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
		
		sobrietyMeter = new JLabel("Sobriety Level: " + sobrietyLevel + "%");
		sobrietyMeter.setVerticalTextPosition(AbstractButton.CENTER);
		sobrietyMeter.setHorizontalTextPosition(AbstractButton.LEADING);
		sobrietyMeter.setFont(new Font("Arial", 1, 18));
		sobrietyMeter.setBounds(frame.getWidth() - 200, 0, 200, 30);
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
		
	public void generateLocations()
	{
		
		Location location0, location1, location2, location3, location4, location5, 
		location6, location7, location8;
		
		// TO ADD: switch block to create the events necessary
		// for the specific locations
		location0 = new Location("Your Apartment", new ArrayList<Event>(
						Arrays.asList(events.get(0))));
		
		location1 = new Location("The Frat House", new ArrayList<Event>(
						Arrays.asList(events.get(4))));
		
		location2 = new Location("Class", new ArrayList<Event>(
						Arrays.asList(events.get(1))));
		
		location3 = new Location("The Cafeteria", new ArrayList<Event>(
						Arrays.asList(events.get(2))));
		
		location4 = new Location("The Gym", new ArrayList<Event>(
						Arrays.asList(events.get(3))));
		
		location5 = new Location("The Stadium", new ArrayList<Event>(
						Arrays.asList(events.get(5))));
		
		location6 = new Location("The Garage", new ArrayList<Event>(
						Arrays.asList(events.get(6))));
		
		location7 = new Location("The Bar", new ArrayList<Event>(
						Arrays.asList(events.get(7))));
		
		location8 = new Location("The Car", new ArrayList<Event>(
						Arrays.asList(events.get(8))));
		
		locations = new ArrayList<Location>
		(
			Arrays.asList
			(
				location0, location1, location2, location3, location4, location5,
				location6, location7, location8
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
			    String [] options = ((String) event.get("options")).split(",");
				String [] optionResults = ((String) event.get("optionResults")).split(",");
				String [] sobrietyResults = ((String) event.get("sobrietyResults")).split(",");
			    events.add(new Event(eventText, options, optionResults, sobrietyResults));
			  }
		currentEvent = events.get(0);
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
		
		b1 = new JButton(text1);
		b1.setVerticalTextPosition(AbstractButton.CENTER);
		b1.setHorizontalTextPosition(AbstractButton.LEADING);
		b1.setBounds(button1X,buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		frame.add(b1);
		frame.repaint();
		
		b2 = new JButton(text2);
		b2.setVerticalTextPosition(AbstractButton.CENTER);
		b2.setHorizontalTextPosition(AbstractButton.LEADING);
		b2.setBounds(button2X, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT); //int x, int y, int width, int height
		frame.add(b2);
		frame.repaint();
		
		b1.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) 
		    {
		    	frame.remove(b1);
		    	frame.remove(b2);
		    	updateSobrietyLevel(currentEvent.getSobrietyResults(0));
		    	updateStoryText(currentEvent.getOptionResults(0));

		    }
		});
	
		
		b2.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) 
		    {
		    	frame.remove(b1);
		    	frame.remove(b2);
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

	public void gameOver()
	{
		updateStoryText("You got arrested. You must have been REALLY high..."
						+ " Press ESC to exit");
		frame.repaint();
	}
	
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
 
}
