//Story events
public class Event {
	//Instance Variables
	private int eventId;
	private String eventText;
	private String [] eventOptions;
	private String [] eventOptionResults;
	
	public Event(int eId, String eText, String [] eOptions, String [] eOptionResults)
	{
		eventId = eId;
		eventText = eText;
		eventOptions = eOptions;
		eventOptionResults = eOptionResults;
	}
	
	//Event Id Getter
	public int getEventId()
	{
		return eventId;
	}
	
	//Event Text Getter
	public String getEventText()
	{
		return eventText;
	}
	
	//Event Options Getter
	public String [] getOptions()
	{
		return eventOptions;
	}
	
	//Event Option Results Getter
	public String [] getOptionResults()
	{
		return eventOptionResults;
	}
	
	public String toString()
	{
		return (this.eventText);
	}
}
