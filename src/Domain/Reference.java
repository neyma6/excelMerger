package Domain;

public class Reference 
{
	public static final Reference EMPTY_REFERENCE = new Reference("", "", "", "", "");
	private final String id;
	private final String type;
	private final String description;
	private final String additionalData1;
	private final String additionalData2;
		
	public Reference(String id, String type, String description, String additionalData1, String additionalData2) 
	{
		this.id = id;
		this.type = type;
		this.description = description;
		this.additionalData1 = additionalData1;
		this.additionalData2 = additionalData2;
	}
	
	public String getId() 
	{
		return id;
	}
	
	public String getType()
	{
		return type;
	}

	public String getAdditionalData1() 
	{
		return additionalData1;
	}

	public String getAdditionalData2() 
	{
		return additionalData2;
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
