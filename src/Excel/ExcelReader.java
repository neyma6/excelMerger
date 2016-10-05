package Excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import Exception.FileReadException;

public class ExcelReader {

	private String path;
	private Workbook workbook = null;
	
	public ExcelReader(String path)
	{
		this.path = path;
	}
	
	public void readExcelFile() throws FileReadException, InvalidFormatException
	{
		System.out.println("path " + path);
		InputStream inp;
		try 
		{
			inp = new FileInputStream(path);
			workbook = WorkbookFactory.create(inp);
		} 
		catch (IOException e) 
		{
			throw new FileReadException(path);
		}
	}
	
	public Sheet getSheet(int sheetNumber) throws NullPointerException
	{
		if (workbook == null)
		{
			throw new NullPointerException();
		}
		
		return workbook.getSheetAt(sheetNumber);
	}
	
	public String getFileName()
	{
		return path.substring(path.lastIndexOf("\\") + 1);
	}
	
	public String getPath()
	{
		return path;
	}

	public Workbook getWorkbook() {
		return workbook;
	}
	
	
}
