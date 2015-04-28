import java.util.ArrayList;

public class SuperArea 
{
	int id;
	String name;
	ArrayList<SubArea> subareas;
	
	public SuperArea(int i, String n, ArrayList<SubArea> a)
	{
		id = i;
		name = n;
		subareas = a;
	}
	
	// getter for SuperArea Id
	public int getSuperAreaId()
	{
		return id;
	}
	
	// getter for SuperArea name
	public String getSuperAreaName()
	{
		return name;
	}
}
