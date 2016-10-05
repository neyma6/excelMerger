package Domain;

import java.util.Collections;
import java.util.List;

//should create a builder
public class Reference 
{
	public static final Reference EMPTY_REFERENCE = new Reference("", "", "", "", "", Collections.emptyList());
	private final String webId;
	private final String id;
	private final String type;
	private final String description;
	private final String timestamp;
	private final List<String> additionalData;
		
	public Reference(String webId, String id, String type, String description, String timestamp, List<String> additionalData) 
	{
		this.webId = webId;
		this.id = id;
		this.type = type;
		this.description = description;
		this.timestamp = timestamp;
		this.additionalData = additionalData;
	}
	
	public String getId() 
	{
		return id;
	}
	
	public String getType()
	{
		return type;
	}

	public List<String> getAdditionalData() 
	{
		return additionalData;
	}

	public String getDescription() 
	{
		return description;
	}
	

	public String getWebId() {
		return webId;
	}
	
	public String getTimestamp() {
		return timestamp;
	}

	@Override
	public boolean equals(Object arg0) 
	{
		Reference other = (Reference)arg0;
		return this.id.equals(other.id);
	}

	@Override
	public int hashCode() 
	{
		return this.id.hashCode();
	}
	
	
}
