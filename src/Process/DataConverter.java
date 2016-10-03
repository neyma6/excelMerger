package Process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Domain.MergedContainer;
import Domain.MergedProduct;
import Domain.Product;
import Domain.Reference;
import Domain.ReferenceContainer;
import Domain.SimilarIdContainer;

public class DataConverter
{
	private static int TYPE_COLUMN_NUMBER = 0;
	private static int ADDITIONAL_COLUMN_NUMBER = 1;
	private static int ID_COLUMN_NUMBER = 6;
	private static int DESCRIPTION_COLUMN_NUMBER = 7;
	private static int CHEAPEST_COLUMN_NUMBER = 8;
	private static int PRICE_COLUMN_NUMBER = 9;
	
	private static String TYPE_COLUMN_NAME = "Típus";
	private static String ADDITIONAL_COLUMN_NAME = "Plusz infó";
	private static String ID_COLUMN_NAME = "Gyári kód";
	private static String DESCRIPTION_COLUMN_NAME = "Termék";
	private static String CHEAPEST_COLUMN_NAME = "Legolcsóbb";
	
	private static final short[] COLOR_INDECIES = {
		IndexedColors.BLUE_GREY.getIndex(),
		IndexedColors.GREY_25_PERCENT.getIndex(),
		IndexedColors.GREY_40_PERCENT.getIndex(),
		IndexedColors.GREY_50_PERCENT.getIndex(),
		IndexedColors.INDIGO.getIndex()
	};
	
	public List<Product> convertSheetToList(Sheet sheet, int idColumn, int descColumn, int priceColumn, SimilarIdContainer similarIdContainer)
	{
		List<Product> products = new ArrayList<>();
		int lastRow = sheet.getLastRowNum();
		for (int i = 0; i <= lastRow; i ++)
		{
			Row row = sheet.getRow(i);
			if (row == null)
			{
				continue;
			}
			addDataToProductList(row, products, idColumn, descColumn, priceColumn, similarIdContainer);
		}
		return products;
	}
	
	public Workbook convertListToWorkBook(MergedContainer mergedContainer)
	{
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row titleRow = sheet.createRow(0);
		fillMainRow(titleRow, mergedContainer.getFileNames());
		List<MergedProduct> products = mergedContainer.getMergedProducts();
		for (int i = 0; i < products.size(); i ++)
		{
			Row row = sheet.createRow(i + 1);
			MergedProduct mp = products.get(i);
			fillRow(row, mp, workbook);
		}
		
		sheet.autoSizeColumn(TYPE_COLUMN_NUMBER);
		autosizeAdditionalCells(sheet);
		sheet.autoSizeColumn(ID_COLUMN_NUMBER);
		sheet.autoSizeColumn(DESCRIPTION_COLUMN_NUMBER);
		sheet.autoSizeColumn(CHEAPEST_COLUMN_NUMBER);
		
		for (int i = 0; i < mergedContainer.getFileNames().size(); i++)
		{
			sheet.autoSizeColumn(PRICE_COLUMN_NUMBER + i);
		}
		
		return workbook;
	}
	
	public ReferenceContainer convertSheetToReferences(Sheet sheet)
	{
		Set<Reference> references = new HashSet<>();
		
		int lastRow = sheet.getLastRowNum();
		for (int i = 0; i <= lastRow; i ++)
		{
			Row row = sheet.getRow(i);
			if (row == null)
			{
				continue;
			}
			addDataToReferences(row, references);
		}
		return new ReferenceContainer(references);
	}
	
	/*
	 * Need to refactor this
	 */
	public SimilarIdContainer convertSheetToSimilarIdConverter(Sheet sheet)
	{
		Map<String, String> similarIds = new HashMap<>();
		int lastRow = sheet.getLastRowNum();
		for (int i = 0; i <= lastRow; i ++)
		{
			Row row = sheet.getRow(i);
			if (row == null)
			{
				continue;
			}
			List<String> values = new ArrayList<>();
			for (Iterator<Cell> it = row.cellIterator(); it.hasNext();)
			{
				String id = getIdCellValue(it.next()).toUpperCase();
				id = removeSpaceFromTheBegining(removeSpacesFromTheEnd(id));
				values.add(id);
			}
			for (int j = 1; j < values.size(); j++)
			{
				similarIds.put(values.get(j), values.get(0));
			}
		}
		return new SimilarIdContainer(similarIds);
	}
	
	private boolean isValidData(Cell id, Cell desc, Cell price)
	{
		return id != null && desc != null && price != null &&
				!id.toString().isEmpty() && !desc.toString().isEmpty() &&
				!price.toString().isEmpty();
	}
	
	private boolean isValidAdditionalData(Cell additional)
	{
		return additional != null && !additional.toString().isEmpty();
	}
	
	private boolean isValidReferenceData(Cell id, Cell type)
	{
		return id != null && type != null &&
				!id.toString().isEmpty() && !type.toString().isEmpty();
	}
	
	private void fillMainRow(Row row, List<String> cellValues)
	{
		row.createCell(TYPE_COLUMN_NUMBER).setCellValue(TYPE_COLUMN_NAME);
		createAdditionalCells(row);
		row.createCell(ID_COLUMN_NUMBER).setCellValue(ID_COLUMN_NAME);
		row.createCell(DESCRIPTION_COLUMN_NUMBER).setCellValue(DESCRIPTION_COLUMN_NAME);
		row.createCell(CHEAPEST_COLUMN_NUMBER).setCellValue(CHEAPEST_COLUMN_NAME);
		for (int i = 0; i < cellValues.size(); i++)
		{
			row.createCell(PRICE_COLUMN_NUMBER + i).setCellValue(cellValues.get(i).toString());
		}
	}
	
	private void createAdditionalCells(Row row) {
		for (int i = ADDITIONAL_COLUMN_NUMBER; i < ID_COLUMN_NUMBER; i++) {
			row.createCell(i).setCellValue(ADDITIONAL_COLUMN_NAME + " " + i);
		}
	}
	
	private void autosizeAdditionalCells(Sheet sheet) {
		for (int i = ADDITIONAL_COLUMN_NUMBER; i < ID_COLUMN_NUMBER; i++) {
			sheet.autoSizeColumn(i);
		}
	}
	
	private void fillRow(Row row, MergedProduct mergedProduct, Workbook workbook)
	{
		row.createCell(TYPE_COLUMN_NUMBER).setCellValue(mergedProduct.getType());
		fillAdditionalData(row, mergedProduct);
		row.createCell(ID_COLUMN_NUMBER).setCellValue(mergedProduct.getId());
		row.createCell(DESCRIPTION_COLUMN_NUMBER).setCellValue(mergedProduct.getDescription());
		List<String> prices = convertFloatToString(mergedProduct.getPrices());
		String cheapest = prices.get(mergedProduct.getCheapestIndex());
		row.createCell(CHEAPEST_COLUMN_NUMBER).setCellValue(cheapest.equals("-1") ? "" : cheapest);
		for (int i = 0; i < prices.size(); i++)
		{
			Cell cell = row.createCell(PRICE_COLUMN_NUMBER + i);
			String cellValue = prices.get(i).toString();
			cell.setCellValue(cellValue.equals("-1") ? "" : cellValue);
			
			CellStyle style = workbook.createCellStyle();
			if (mergedProduct.getCheapestIndex() == i && !cellValue.equals("-1"))
			{
				style.setFillForegroundColor(IndexedColors.RED.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cell.setCellStyle(style);
			}
			else
			{
				style.setFillForegroundColor(COLOR_INDECIES[i % 5]);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cell.setCellStyle(style);
			}
		}
	}
	
	private void fillAdditionalData(Row row, MergedProduct mergedProduct) {
		int sizeOfAdditionalCells = mergedProduct.getAdditionalData().size() < 5 
				? mergedProduct.getAdditionalData().size() : ID_COLUMN_NUMBER;
		for (int i = 0; i < sizeOfAdditionalCells ; i++) {
			row.createCell(ADDITIONAL_COLUMN_NUMBER + i).setCellValue(mergedProduct.getAdditionalData().get(i));
		}
	}
	
	private void addDataToReferences(Row row, Set<Reference> references)
	{
		Cell id = row.getCell(0);
		Cell type = row.getCell(1);
		if (isValidReferenceData(id, type))
		{	
			String description = getData(row.getCell(2));
			List<String> additional = extractAdditionalData(row, 3);
			String idS = removeSpaceFromTheBegining(removeSpacesFromTheEnd(getIdCellValue(id).toUpperCase()));
			references.add(new Reference(idS, type.toString(), description, additional));
		}
	}
	
	//6. goal
	private List<String> extractAdditionalData(Row row, int startIndex) {
		List<String> additionalData = new ArrayList<>();
		int lastCellNumber = row.getLastCellNum();
		for (int i = startIndex; i < lastCellNumber; i++) {
			additionalData.add(getData(row.getCell(i)));
		}
		return additionalData;
	}
	
	private void addDataToProductList(Row row, List<Product> products, int idColumn, int descColumn,
			int priceColumn, SimilarIdContainer similarIdContainer)
	{
		Cell id = row.getCell(idColumn);
		Cell desc = row.getCell(descColumn);
		Cell price = row.getCell(priceColumn);
		if (isValidData(id, desc, price))
		{
			try
			{
				float priceInFloat = Float.parseFloat(price.toString());
				//1. goal from specification.txt
				if (priceInFloat == 0.0) {
					return;
				}
				
				String idS = getIdCellValue(id).toUpperCase();
				idS = removeSpaceFromTheBegining(removeSpacesFromTheEnd(idS));
				String similarId = similarIdContainer.getSimilarId().get(idS);
				if (similarId != null)
				{
					idS = similarId;
				}
				String description = removeSpaceFromTheBegining(desc.toString());
				Product product = new Product.ProductBuilder(idS,
						description)
				.withPrice(priceInFloat)
				.build();
				products.add(product);
			}
			catch(NumberFormatException ex)
			{
				return;
			}
		}
	}
	
	private String getData(Cell additional)
	{
		return isValidAdditionalData(additional) ? additional.toString() : "";
	}
	
	private List<String> convertFloatToString(List<Float> floatArray)
	{
		List<String> stringArray = new ArrayList<>();
		
		floatArray.forEach((element)->{
			stringArray.add(String.valueOf(Math.round(element)));
		});
		
		return stringArray;
	}
	
	private String getIdCellValue(Cell cell)
	{
		String id;		
		switch(cell.getCellType())
		{
			case Cell.CELL_TYPE_NUMERIC:
				id = String.valueOf(new Double(cell.getNumericCellValue()).longValue());
				break;
			case Cell.CELL_TYPE_STRING:	
				id = cell.toString();
				break;
			default: 
				id = ""; 
				break;
		}
		
		return id;
	}
	
	private String removeSpaceFromTheBegining(String str)
	{
		return str.replaceAll("^\\s+", "");
	}
	
	private String removeSpacesFromTheEnd(String str)
	{
		return str.replaceAll("\\s+$", "");
	}
}
