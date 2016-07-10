package Excel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import Domain.FileConfiguration;
import Domain.MergedContainer;
import Domain.MergedProduct;
import Domain.Product;
import Domain.ProductContainer;
import Domain.Reference;
import Domain.ReferenceContainer;
import Domain.SimilarIdContainer;
import Exception.FileReadException;
import Process.DataConverter;
import Process.MergingPool;
import View.Labels;


public class ExcelMerger {

	private List<FileConfiguration> configurations;	
	private List<ProductContainer> productContainers;
	private DataConverter dataConverter;
	private Workbook workbook;
	private String referenceFilePath;
	private String similarFilePath;
	private float spread;
	private float VAT; 
	
	private static List<String> log = new ArrayList<>();
	
	
	public ExcelMerger(List<FileConfiguration> configurations, String referenceFilePath,
			String similarFilePath, float spread, float VAT) 
	{
		this.configurations = configurations;
		this.referenceFilePath = referenceFilePath;
		this.similarFilePath = similarFilePath;
		this.spread = spread;
		this.VAT = VAT;
		productContainers = new ArrayList<>();
		dataConverter = new DataConverter();
		workbook = null;
	}
	
	public void process() throws InvalidFormatException, FileReadException
	{
		log.clear();
		productContainers.clear();
		
		ExcelReader refReader = new ExcelReader(referenceFilePath);
		refReader.readExcelFile();
		ReferenceContainer referenceContainer = dataConverter.convertSheetToReferences(refReader.getSheet(0));
		
		log.add(String.format(Labels.LOG_REFERENCE_SIZE, referenceContainer.getReferences().size()));
		
		ExcelReader similarReader = new ExcelReader(similarFilePath);
		similarReader.readExcelFile();
		SimilarIdContainer similarIdContainer = dataConverter.convertSheetToSimilarIdConverter(similarReader.getSheet(0));
		
		log.add(String.format(Labels.LOG_SIMILAR_SIZE, similarIdContainer.getSimilarId().size()));
		log.add(String.format(Labels.LOG_SPREAD, spread));
		log.add(String.format(Labels.LOG_VAT, VAT));
		
		for(FileConfiguration config : configurations)
		{
			ExcelReader reader = new ExcelReader(config.getFilePath());
		    reader.readExcelFile();
			
		    List<Product> products = dataConverter.convertSheetToList(reader.getSheet(0), config.getIdColumnNumber(),
		    		config.getDescriptionColumnNumber(), config.getPriceColumnNumber(), similarIdContainer);
		    
		    log.add(String.format(Labels.LOG_FILE_READED, reader.getPath()));

			log.add(String.format(Labels.LOG_COLUMN_NUMBERS, config.getIdColumnNumber() + 1,
					config.getDescriptionColumnNumber() + 1, config.getPriceColumnNumber() + 1));
			
			log.add(String.format(Labels.LOG_PRODUCT_NUMBERS, products.size()));
						
			productContainers.add(new ProductContainer(products, reader.getFileName()));
		}
		MergingPool mergingPool = new MergingPool();
		MergedContainer mergedContainer = mergingPool.merge(productContainers, spread, VAT);
		
		log.add(String.format(Labels.LOG_AFTER_MERGER, mergedContainer.getMergedProducts().size()));
			
		setTypeToProducts(mergedContainer, referenceContainer);
		sortList(mergedContainer.getMergedProducts());
		workbook = dataConverter.convertListToWorkBook(mergedContainer);
	}

	public static List<String> getLog() 
	{
		return log;
	}

	public void writeToFile(String filePath) throws Exception
	{
		if (workbook != null)
		{
			ExcelWriter excelWriter = new ExcelWriter();
			excelWriter.writeToFile(workbook, filePath);
		}
	}
	
	private void sortList(List<MergedProduct> mergedProducts)
	{
		mergedProducts.sort((product1, product2)->
			product1.getDescription().compareTo(product2.getDescription())
		);
		
		mergedProducts.sort((product1, product2)->{
			if (product2.getType() == "")
			{
				return -1;
			}
			if (product1.getType() == "")
			{
				return 1;
			}
			return product1.getType().compareTo(product2.getType());
		});
	}
	
	private void setTypeToProducts(MergedContainer mergedContainer, ReferenceContainer referenceContainer)
	{
		mergedContainer.getMergedProducts().forEach((mergedProduct)->{
			Reference reference = referenceContainer.getReference(mergedProduct.getId());
			if (reference != Reference.EMPTY_REFERENCE)
			{
				mergedProduct.setType(reference.getType());
				mergedProduct.setAdditionalData1(reference.getAdditionalData1());
				mergedProduct.setAdditionalData2(reference.getAdditionalData2());
				if (reference.getDescription().length() > 0)
				{
					mergedProduct.setDescription(reference.getDescription());
				}
			}
		});
	}
}
