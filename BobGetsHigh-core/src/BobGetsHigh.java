
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



public class BobGetsHigh
{
	private Area currentArea;
    private ArrayList<String> inventory;
    private int sobrietyLevel = 100;
    private JFrame frame;
    private JPanel content;
    private BufferedImage image;
    private int currentEvent = 0;
   
    
	public static void main(String[] args)
	{
		BobGetsHigh game = new BobGetsHigh();
	}
		
	
	public BobGetsHigh()
	{
		generateEvents();
		generateMap();
		makeFrame();
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
	
	public void generateMap()
	{
	//	Map map = new Map();
		Area area1, area2, area3;
		area1 = new Area("Inside the Apartment", null);
		area2 = new Area("Outside the Apartment", null);
		area3 = new Area("At the Frat House", null);
		System.out.println(area1.name + ", " + area2.name + ", " + area3.name);
	}
	
	public void generateEvents()
	{
		String [] options = {"Yes", "No"};
		String [] optionResults = {"You Lose", "You Win"};
		Event event1 = new Event(0,"Want to do some drugs Bob?", options, optionResults);
	}
	
	private void closeWindow(){
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
	
	private void windowedMode(){
		frame.setUndecorated(false);
	}
	

	public void updateSobrietyLevel(int num){
		sobrietyLevel += num;
	}
}

