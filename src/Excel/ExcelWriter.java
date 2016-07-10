package Excel;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;

public class ExcelWriter {

	public void writeToFile(Workbook workbook, String name) throws Exception
	{
		FileOutputStream fileOut = new FileOutputStream(name);
		workbook.write(fileOut);
	    fileOut.close();
	}
}
