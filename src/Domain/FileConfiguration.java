package Domain;

public class FileConfiguration 
{
	private final String filePath;
	private final int idColumnNumber;
	private final int descriptionColumnNumber;
	private final int priceColumnNumber;
	
	public FileConfiguration(String filePath, int idColumnNumber, 
			int descriptionColumnNumber, int priceColumnNumber)
	{
		this.filePath = filePath;
		this.idColumnNumber = idColumnNumber;
		this.descriptionColumnNumber = descriptionColumnNumber;
		this.priceColumnNumber = priceColumnNumber;
	}

	public String getFilePath() 
	{
		return filePath;
	}

	public int getIdColumnNumber()
	{
		return idColumnNumber;
	}

	public int getDescriptionColumnNumber() 
	{
		return descriptionColumnNumber;
	}

	public int getPriceColumnNumber()
	{
		return priceColumnNumber;
	}
	
}
