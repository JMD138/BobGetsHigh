import java.util.ArrayList;
public class Area extends Map
{
	String name;
	ArrayList<Event> events;
	
	public Area(String n, ArrayList<Event> e)
	{
		name = n;
		events = e;
	}
}
