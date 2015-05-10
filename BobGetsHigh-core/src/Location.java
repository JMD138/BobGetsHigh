import java.util.ArrayList;

public class Location
{
	String name;
	ArrayList<Event> events;
	
	public Location(String n, ArrayList<Event> e)
	{
		name = n;
		events = e;
	}
	
	// getter for Location name
	public String getLocationName()
	{
		return name;
	}
	
	public String toString()
	{
		return (this.name + ". Events: " + events.toString() + ".\n");
	}
	
	public ArrayList<Event> getEvents(){
		return events;
	}
}
