
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class BobGetsHigh
{
	private Area currentArea;
    private ArrayList<String> inventory;
    private int sobrietyLevel;
    
	public static void main(String[] args)
	{
		BobGetsHigh game = new BobGetsHigh();
	}
	
	public BobGetsHigh()
	{
		generateMap();
		generateArea();
		generateEvents();
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

