package Process;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import Domain.MergedContainer;
import Domain.MergedProduct;
import Domain.Product;
import Domain.ProductContainer;


public class MergingPool 
{
	private static final String[] FILE_PREFICIES = {
		""
	};
	
	public MergedContainer merge(List<ProductContainer> productContainers, float spread, float VAT)
	{
		MergedContainer mergedContainer = mergeContainers(productContainers);
		
		mergedContainer.getMergedProducts().forEach((mergedProduct)->{
			productContainers.forEach((productContainer)->{
				float price;
				Product product = productContainer.getProduct(mergedProduct.getId());
				if (product != Product.EMPTY_PRODUCT)
				{
					price = product.getPrice() * spread * VAT;
				}
				else
				{
					price = -1.0f;
				}
				mergedProduct.getPrices().add(price);
			});
			calculateCheapest(mergedProduct);
		});
		
		return mergedContainer;
	}
	
	private MergedContainer mergeContainers(List<ProductContainer> productContainers)
	{
		List<String> fileNames = new ArrayList<>();
		Set<MergedProduct> mergedProducts = new HashSet<>();
		
		productContainers.forEach((container)->{
			fileNames.add(container.getFileName());
			container.getProducts().forEach((product)->{
				mergedProducts.add(new MergedProduct(product.getId(), product.getDescription()));
			});
			
		});
		cutTheBeginingOfTheDescIfNecessary(mergedProducts);
		return new MergedContainer(fileNames, mergedProducts);
	}
	
	private void cutTheBeginingOfTheDescIfNecessary(Set<MergedProduct> mergedProducts)
	{
		
	}
	
	private void calculateCheapest(MergedProduct mergedProduct)
	{
		float cheapest = mergedProduct.getPrices().stream().min((value1, value2)->{
				if (value2 == -1.0f || value2 == 0.0f)
				{
					return -1;
				}
				if (value1 == -1.0f || value1 == 0.0f)
				{
					return 1;
				}
				return value1.compareTo(value2);}).get();
		int cheapestIndex = mergedProduct.getPrices().indexOf(cheapest);
		mergedProduct.setCheapestIndex(cheapestIndex);
	}
	
}
