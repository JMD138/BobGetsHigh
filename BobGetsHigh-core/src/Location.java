import java.util.ArrayList;

public class Location
{
	int id;
	String name;
	ArrayList<Event> events;
	
	public Location(int i, String n, ArrayList<Event> e)
	{
		id = i;
		name = n;
		events = e;
	}
	
	// getter for SubArea Id
	public int getSubAreaId()
	{
		return id;
	}
	
	// getter for SubArea name
	public String getSubAreaName()
	{
		return name;
	}
	
	public String toString()
	{
		return (this.name + ". Events: " + events.toString() + ".\n");
	}
}
