package Domain;

public class Product 
{
	public static Product EMPTY_PRODUCT = new Product("", "");
	private final String id;
	private final String description;
	private float price;
	
	private Product(String id, String description)
	{
		assert(id != null);
		assert(description != null);
		this.id = id;
		this.description = description;
	}
		
	public float getPrice() 
	{
		return price;
	}



	public String getId() 
	{
		return id;
	}

	public String getDescription() 
	{
		return description;
	}
	
	@Override
	public String toString() 
	{
		return "Product [id=" + id + ", description=" + description
				+ ", price=" + price + "]";
	}

	public static class ProductBuilder
	{
		private Product product;
		
		public ProductBuilder(String id, String description)
		{
			product = new Product(id, description);
		}
		
		public ProductBuilder(Product product)
		{
			this.product = product;
		}
		
		public ProductBuilder withPrice(float price)
		{
			product.price = price;
			return this;
		}
		
		public Product build()
		{
			return product;
		}
	}
	
}
