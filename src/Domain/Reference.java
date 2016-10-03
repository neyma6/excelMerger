package Domain;

import java.util.Collections;
import java.util.List;

public class Reference 
{
	public static final Reference EMPTY_REFERENCE = new Reference("", "", "", Collections.emptyList());
	private final String id;
	private final String type;
	private final String description;
	private final List<String> additionalData;
		
	public Reference(String id, String type, String description, List<String> additionalData) 
	{
		this.id = id;
		this.type = type;
		this.description = description;
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
