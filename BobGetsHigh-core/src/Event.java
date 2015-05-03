//Story events
public class Event {
	//Instance Variables
	private int eventId;
	private String eventText;
	private String [] eventOptions;
	private String [] eventOptionResults;
	private int [] sobrietyResults;
	
	public Event(int eId, String eText, String [] eOptions, String [] eOptionResults, int [] eSobrietyResults)
	{
		eventId = eId;
		eventText = eText;
		eventOptions = eOptions;
		eventOptionResults = eOptionResults;
		sobrietyResults = eSobrietyResults;
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
	public String getOptions(int index)
	{
		return eventOptions[index];
	}
	
	//Event Option Results Getter
	public String getOptionResults(int index)
	{
		return eventOptionResults[index];
	}
	
	public int getSobrietyResults(int index)
	{
		return sobrietyResults[index];
	}
	
	public String toString()
	{
		return (this.eventText);
	}
	
}
