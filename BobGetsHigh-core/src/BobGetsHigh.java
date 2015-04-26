
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = (JPanel)frame.getContentPane();
        JLabel emptyLabel = new JLabel("");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        frame.setSize((int)dimension.getWidth(), (int)dimension.getHeight());
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setBackground(Color.BLACK);
        frame.setLocation(x, y);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setVisible(true);
	}
	
	public void generateMap()
	{
	//	Map map = new Map();
		Area aptIn, aptOut, fratHouse;
		aptIn = new Area("Inside the Apartment", null);
		aptOut = new Area("Outside the Apartment", null);
		fratHouse = new Area("At the Frat House", null);
		
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

