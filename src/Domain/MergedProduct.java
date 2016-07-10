package Domain;

import java.util.ArrayList;
import java.util.List;


public class MergedProduct 
{
	private final String id;
	private String description;
	private String type;
	private String additionalData1;
	private String additionalData2;
	private List<Float> prices;
	private int cheapestIndex;
	
	public MergedProduct(String id, String description)
	{
		this.id = id;
		this.description = description;
		cheapestIndex = 0;
		prices = new ArrayList<>();
		type = "";
		additionalData1 = "";
		additionalData2 = "";
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
	
	public String getAdditionalData1() 
	{
		return additionalData1;
	}

	public void setAdditionalData1(String additionalData1) 
	{
		this.additionalData1 = additionalData1;
	}

	public String getAdditionalData2() 
	{
		return additionalData2;
	}

	public void setAdditionalData2(String additionalData2) 
	{
		this.additionalData2 = additionalData2;
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
