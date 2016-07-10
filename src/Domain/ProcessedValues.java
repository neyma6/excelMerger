package Domain;

public class ProcessedValues 
{
	private final String filePath;
	private final int validProductNumber;
	
	public ProcessedValues(String filePath, int validProductNumber)
	{
		this.filePath = filePath;
		this.validProductNumber = validProductNumber;
	}

	public String getFilePath() 
	{
		return filePath;
	}

	public int getValidProductNumber()
	{
		return validProductNumber;
	}

	@Override
	public String toString() {
		return "file path = " + filePath
				+ ", valid processed products = " + validProductNumber + "\n";
	}

	
}
