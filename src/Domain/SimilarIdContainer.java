package Domain;

import java.util.Map;

public class SimilarIdContainer 
{
	private final Map<String, String> similarId;

	public SimilarIdContainer(Map<String, String> similarId) 
	{
		this.similarId = similarId;
	}

	public Map<String, String> getSimilarId() 
	{
		return similarId;
	}
}
