package Domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MergedProduct 
{
	private final String id;
	private String description;
	private String type;
	private List<String> additionalData;
	private List<Float> prices;
	private int cheapestIndex;
	
	public MergedProduct(String id, String description)
	{
		this.id = id;
		this.description = description;
		cheapestIndex = 0;
		prices = new ArrayList<>();
		type = "";
		additionalData = Collections.emptyList();
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public String getId()
	{
		return id;
	}

	public String getDescription() 
	{
		return description;
	}

	public List<Float> getPrices() 
	{
		return prices;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}
	

	public int getCheapestIndex() 
	{
		return cheapestIndex;
	}

	public void setCheapestIndex(int cheapestIndex)
	{
		this.cheapestIndex = cheapestIndex;
	}
	
	public List<String> getAdditionalData() 
	{
		return additionalData;
	}

	public void setAdditionalData(List<String> additionalData) 
	{
		this.additionalData = additionalData;
	}


	@Override
	public boolean equals(Object obj) 
	{
		MergedProduct other = (MergedProduct)obj;		
		return this.id.equals(other.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	

}
