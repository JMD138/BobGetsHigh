package temp.gourd;

import java.util.HashMap;
import java.util.ArrayList;

/*
 * A simple description of a room in the simple sample game.
 * 
 * Last updated: 2015-04-15 (tax day!)
 * Original author: Dr. Jean Gourd
 * Author: *****
 */
public class Room
{
    private String name;
    private String image;
    // exits map directions (e.g., "north") to rooms
    private HashMap<String,Room> exits;
    // items map items (e.g., "table") to their description
    private HashMap<String,String> items;
    // grabbables are essentially items that can be picked up and stored as inventory
    private ArrayList<String> grabbables;

    // constructor
    public Room(String n, String i)
    {
        name = n;
        image = i;
        exits = new HashMap<String,Room>();
        items = new HashMap<String,String>();
        grabbables = new ArrayList<String>();
    }
    
    // getters and setters
    public String getImage()
    {
        return image;
    }
    
    public HashMap<String,Room> getExits()
    {
        return exits;
    }
    
    public void addExit(String e, Room r)
    {
        exits.put(e, r);
    }
    
    public HashMap<String,String> getItems()
    {
        return items;
    }
    
    public void addItem(String i, String d)
    {
        items.put(i, d);
    }
    
    public ArrayList<String> getGrabbables()
    {
        return grabbables;
    }
    
    public void addGrabbable(String g)
    {
        grabbables.add(g);
    }
    
    public void removeGrabbable(String g)
    {
        grabbables.remove(g);
    }
    
    // what to display when outputting a room
    public String toString()
    {
        String s = "You are in " + name + ".\n\n";
        
        s += "You see: ";
        for (String item : items.keySet())
        {
            s += item + " ";
        }
        s += "\n\n";
        
        s += "Exits: ";
        for (String exit : exits.keySet())
        {
            s += exit + " ";
        }
        s += "\n";
        
        return s;
    }
}