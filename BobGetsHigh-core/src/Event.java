//Story events
public class Event {
	//Instance Variables
	private String eventText;
	private String [] eventOptions;
	private String [] eventOptionResults;
	private String [] sobrietyResults;
	
	public Event(String eText, String [] eOptions, String [] eOptionResults, String [] eSobrietyResults)
	{
		eventText = eText;
		eventOptions = eOptions;
		eventOptionResults = eOptionResults;
		sobrietyResults = eSobrietyResults;
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
		return Integer.parseInt(sobrietyResults[index]);
	}
	
	public String toString()
	{
		return (this.eventText);
	}
	
}
