
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
		generateEvents();
		generateMap();
	}
	
	public void generateMap()
	{
		Map map = new Map();
		Area aptIn, aptOut, fratHouse;
		aptIn = new Area("Inside the Apartment", null);
		aptOut = new Area("Outside the Apartment", null);
		fratHouse = new Area("At the Frat House", null);
		
	}
	
	public void generateEvents()
	{
	}

}

