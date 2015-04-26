
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
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
    private int sobrietyLevel;
    private JFrame frame;
    private JPanel content;
    
	public static void main(String[] args)
	{
		BobGetsHigh game = new BobGetsHigh();
	}
	
	public BobGetsHigh()
	{
		generateMap();
		generateArea();
		generateEvents();
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = (JPanel)frame.getContentPane();
	}
	
	public void generateMap()
	{
		
	}
	
	public void generateArea()
	{
		
	}
	public void generateEvents()
	{
		String [] options = {"Yes", "No"};
		String [] optionResults = {"You Lose", "You Win"};
		Event event1 = new Event(0,"Want to do some drugs Bob?", options, optionResults);
	}

}

