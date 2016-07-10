package Domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MergedContainer 
{
	private List<String> fileNames;
	private List<MergedProduct> mergedProducts;
	
	public MergedContainer(List<String> fileNames, Set<MergedProduct> mergedProducts) 
	{
		this.fileNames = fileNames;
		this.mergedProducts = new ArrayList<>();
		this.mergedProducts.addAll(mergedProducts);
	}

	public List<String> getFileNames() 
	{
		return fileNames;
	}

	public List<MergedProduct> getMergedProducts()
	{
		return mergedProducts;
	}

}
