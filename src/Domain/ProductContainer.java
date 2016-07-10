package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ProductContainer 
{	
	private List<Product> products;
	private String fileName;
	
	public ProductContainer(List<Product> products, String fileName)
	{
		this.products = products;
		this.fileName = fileName;
	}
		
	public List<Product> getProducts()
	{
		return products;
	}
	
	public Product getProduct(String id)
	{
		Product selectedProduct;
		List<Product> productList = selectProduct(id);
		
		if (productList.size() == 0)
		{
			selectedProduct = Product.EMPTY_PRODUCT;
		}
		else
		{
			selectedProduct = productList.get(0);			
		}
		return selectedProduct;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	private List<Product> selectProduct(String id)
	{
		return products.stream()
				.filter((product)->{return product.getId().equals(id);}).collect(Collectors.toCollection(ArrayList::new));
	}
}
