

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class ReadDriver extends ReadWriteExcel{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		 Logger log = Logger.getLogger(ReadDriver.class.getName());
		ArrayList<Row> ExecutableRow = new ArrayList<Row>();
		String log4jConfPath = "C:\\Users\\Shyam\\eclipse-workspace\\Selenium\\src\\main\\java\\log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		log.info("Startng the execution");
		
		ReadWriteExcel rw = new ReadWriteExcel();
		ReadConfig readCon = new ReadConfig();
		
		
		ExecutableRow = rw.readDriverToExecute();	
		//HashMap<String, String> co = readCon.readConfigFile();
		log.info("Created the object");
		
		//Loop through Each Product
		for (Row row : ExecutableRow)
		{
			//System.out.println("");
			String strProduct = getCellValue(row.getCell(0),new XSSFWorkbook(new FileInputStream(new File("C:\\\\Sellenium\\\\Tet.xlsx"))));
			
			
			//String getWorkBookName = co.get(strProduct);
			
			rw.ReadExcelToHashMap(strProduct);
			//System.out.println(getWorkBookName);
			
			//Read 
			
		}
		
		
	}

}
