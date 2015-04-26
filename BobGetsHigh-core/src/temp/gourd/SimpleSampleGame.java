package temp.gourd;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/*
 * A simple sample game to show how to create GUIs and process text-based user input.
 * 
 * Last updated: 2015-04-15 (tax day!)
 * Original author: Dr. Jean Gourd
 * Author: *****
 */
public class SimpleSampleGame
{
    private static final String GAME_TITLE = "Dr. Gourd's Simple Sample Game";

    // the GUI components
    private JFrame frame;
    private JPanel content;
    private JLabel image_pane;
    private BufferedImage image;
    private JTextArea text_pane;
    private JTextField input_pane;

    // state
    private Room current_room;
    private ArrayList<String> inventory;
    
    // constructor
    public SimpleSampleGame()
    {
        // initialize an empty inventory, add the rooms, make the GUI components, set the current room, and start the game
        inventory = new ArrayList<String>();
        addRooms();
        makeFrame();
        setRoom();
        startGame();
    }

    // adds the rooms
    private void addRooms()
    {
        Room room1, room2, room3, room4;
        
        // set the names and images
        room1 = new Room("Room 1", "images/room1.jpg");
        room2 = new Room("Room 2", "images/room2.jpg");
        room3 = new Room("Room 3", "images/room3.jpg");
        room4 = new Room("Room 4", "images/room4.jpg");
       
        // add the exits, items, and grabbables
        room1.addExit("east", room2);
        room1.addExit("south", room3);
        room1.addGrabbable("key");
        room1.addItem("chair", "It is made of wicker and no one is sitting on it.");
        room1.addItem("table", "It is made of oak.  A golden key rests on it.");
        
        room2.addExit("west", room1);
        room2.addExit("south", room4);
        room2.addItem("rug", "It is nice and Indian.  It also needs to be vacuumed.");
        room2.addItem("fireplace", "It is full of ashes.");
        
        room3.addExit("north", room1);
        room3.addExit("east", room4);
        room3.addGrabbable("book");
        room3.addItem("bookshelves", "They are empty.  Go figure.");
        room3.addItem("statue", "There is nothing special about it.");
        room3.addItem("desk", "The statue is resting on it.  So is a book.");
        
        room4.addExit("north", room2);
        room4.addExit("west", room3);
        room4.addExit("south", null);
        room4.addGrabbable("6-pack");
        room4.addItem("brew_rig", "Gourd is brewing some sort of oatmeal stout on the brew rig.  A 6-pack is resting beside it.");
        
        // set the current room (room1 initially)
        current_room = room1;
    }
    
    // sets the current room (image and text)
    private void setRoom()
    {
        // set the description
        setDescription("");
        
        // set the image
        try
        {
            // death means the skull
            if (current_room == null)
            {
                image = ImageIO.read(new File("images/skull.jpg"));
            }
            // otherwise, load the appropriate room image
            else
            {
                image = ImageIO.read(new File(current_room.getImage()));
            }
            
            image_pane.setIcon(new ImageIcon(image));
        }
        catch (Exception e)
        {}        
        
        // pack the frame so the new image is rendered and the window is resized
        frame.pack();
    }
    
    // set the room description
    private void setDescription(String s)
    {
        // death means death
        if (current_room == null)
        {
            text_pane.setText("You are dead.");
        }
        else
        {
            text_pane.setText(current_room + "\nYou are carrying: " + inventory + "\n\n" + s);
        }
    }
    
    // starts the game
    private void startGame()
    {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        
        // position the window in the center of the screen, make it visible, and give the input_pane focus
        frame.setLocation(d.width / 2 - frame.getWidth() / 2, d.height / 2 - frame.getHeight() / 2);
        frame.setVisible(true);
        input_pane.requestFocus();
    }
    
    // makes the GUI components
    private void makeFrame()
    {
        // the frame and main content pane
        frame = new JFrame(GAME_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = (JPanel)frame.getContentPane();
        
        // the image pane
        image_pane = new JLabel();
        image_pane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        content.add(image_pane, BorderLayout.LINE_START);
        
        // the text pane
        text_pane = new JTextArea(20, 19);
        text_pane.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));
        text_pane.setEditable(false);
        text_pane.setLineWrap(true);
        text_pane.setFont(new Font("Arial", 1, 20));
        content.add(text_pane, BorderLayout.LINE_END);
        
        /// the input pane
        input_pane = new JTextField();
        input_pane.setFont(new Font("Courier new", 1, 20));
        input_pane.setColumns(35);
        input_pane.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
        content.add(input_pane, BorderLayout.PAGE_END);
        
        // add a listener to the input pane so that actions can occur when the user presses enter
        input_pane.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // process the input and clear the input pane
                process(input_pane.getText());
                input_pane.setText("");
            }
        });
    }
    
    // process user input from the input pane
    private void process(String s)
    {
        String sl = s.toLowerCase().trim(); // the lowercase version of the user input
        String[] words;                     // user input split into words
        String verb;                        // the specified verb
        String noun;                        // the specified noun
        String response = "I don't understand.  Try:\n<verb> <noun>\nValid <verb>: go look take";
        
        // handle quitting
        if (sl.equals("quit") || sl.equals("exit") || sl.equals("bye"))
        {
            System.exit(0);
        }
 
        // if we've already died, simply get out of here
        // this allows the user to still quit, exit, or bye above
        if (current_room == null)
        {
            return;
        }
        
        // split the input into words
        words = sl.split(" ");
        
        // only accept two words (verb and noun)
        if (words.length == 2)
        {
            verb = words[0];
            noun = words[1];

            // act based on the verb
            if (verb.equals("go"))
            {
                // set a default response
                response = "Invalid exit.";
                
                // iterate through the keys and values of exits in the current room
                for (Map.Entry<String,Room> entry : current_room.getExits().entrySet())
                {
                    // get the exit
                    String exit = entry.getKey();
                    
                    // if it's the one chosen
                    if (noun.equals(exit))
                    {
                        // /change the current room and set it on the GUI
                        current_room = entry.getValue();
                        setRoom();
                        response = "";
                        
                        break;
                    }
                }
            }
            else if (verb.equals("look"))
            {
                response = "I don't see that item.";
             
                // iterate through the keys and values of the items in the current room
                for (Map.Entry<String,String> entry : current_room.getItems().entrySet())
                {
                    // get the item
                    String item = entry.getKey();
                    
                    // if it's the one chosen
                    if (noun.equals(item))
                    {
                        // set the response to the item's description
                        response = entry.getValue();
                        
                        break;
                    }
                }
            }
            else if (verb.equals("take"))
            {
                response = "I don't see that item.";
                
                // iterate through the grabbables in the current room
                for (String grabbable : current_room.getGrabbables())
                {
                    // if it's the one chosen
                    if (noun.equals(grabbable))
                    {
                        // add it to the inventory
                        inventory.add(grabbable);
                        // remove it from the room's grabbables
                        current_room.removeGrabbable(grabbable);
                        response = "Item grabbed.";
                        
                        break;
                    }
                }
            }
        }
        
        // update the description based on the response generated from the user's input
        setDescription(response);
    }
}