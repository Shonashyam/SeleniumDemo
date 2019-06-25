

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteExcel {
	
	public HashMap<String,HashMap<String,HashMap<String,String>>> xpathDict = new HashMap<String,HashMap<String,HashMap<String,String>>>();
	public HashMap<String,HashMap<String,HashMap<String,String>>> colNameIndexDict = new HashMap<String,HashMap<String,HashMap<String,String>>>();
	public HashMap<String,HashMap<String,HashMap<String,String>>> objectTypeDict =new HashMap<String,HashMap<String,HashMap<String,String>>>();
	
	public HashMap<String,HashMap<String,HashMap<String,HashMap<String,String>>>> sbeDict= new HashMap<String,HashMap<String,HashMap<String,HashMap<String,String>>>>();
	
	
	//----
	public ArrayList<Row> readDriverToExecute()
	{
		ArrayList<Row> rows = new ArrayList<Row>();
		//Fetch the header row
		XSSFWorkbook wb = null;
			try {
				wb = new XSSFWorkbook(new FileInputStream(new File("C:\\Sellenium\\Tet.xlsx")));
				//wb = new XSSFWorkbook(new FileInputStream("C:\\Sellenium\\TestData.xlsx"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		XSSFSheet ws = wb.getSheet("Autmain");
	Row hdRow = getHeaderRow(ws); 
		int cellIndex = getCellIndex("Execute", ws);
		//based on the total row count apply the filter
		for (Row row : ws)		
		{
			if (getCellValue(row.getCell(cellIndex), wb).equalsIgnoreCase("yes"))
			{
				rows.add(row);
			}
		}
		try {
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wb = null;
		
		return rows;
		
	}

	
	public static String getCellValue(Cell cell, XSSFWorkbook wb)
	{
		XSSFFormulaEvaluator evaluator=new XSSFFormulaEvaluator(wb);
		String cellVal="";
		final int CELL_TYPE_STRING=1;
		final int CELL_TYPE_NUMERIC=0;
		final int CELL_TYPE_FORMULA=2;
		final int CELL_TYPE_BLANK=3;
		final int CELL_TYPE_BOOLEAN=4;
		
		if(cell!=null)
		{
			switch(cell.getCellType())
			{
//				case(CELL_TYPE_STRING):
//					cellVal=cell.getStringCellValue();
//			    break;
//				    
//				case(CELL_TYPE_NUMERIC):
//					cellVal=Double.toString(cell.getNumericCellValue());
//				    if(cellVal.endsWith(".0"))
//						{
//					      cellVal=cellVal.replace(".0","");		
//						}
//				    break;
//				    
//				case (CELL_TYPE_FORMULA):
//					//cellVal=evaluator.evaluateInCell(cell).toString();
//					cellVal = getCellValue(evaluator.evaluateInCell(cell),wb);
//					break;
//				
//				case(CELL_TYPE_BLANK):
//					cellVal="";
//					break;
//				
//				case(CELL_TYPE_BOOLEAN):
//					cellVal=Boolean.toString(cell.getBooleanCellValue());
//					break;
			}
			cellVal=cell.getStringCellValue();
		}
		
		return cellVal;
	}		

	
	//To find the Header Row based on the worksheet
	private static Row getHeaderRow(XSSFSheet worksheet)
	{
		Row row = null;
		System.out.println("SheetName - " + worksheet.getSheetName());
		for(Row rowLoop:worksheet)
		{
			try
			{
			     String strCellVal=getCellValue(rowLoop.getCell(0),worksheet.getWorkbook()); //(XSSFWorkbook) worksheet.getParent()
						if(strCellVal.equals("Iterations") || strCellVal.equals("Sr. No.") || strCellVal.equals("Product"))
						{
							row=rowLoop;
							break;
							
						}
			}
			catch(Exception ex){}
		}
		return row;
	}
	
	public static int getCellIndex(String colName, XSSFSheet worksheet)
	{
		int colIndex=-1;
		//Find Header row position
		Row hdrRow=getHeaderRow(worksheet);
		if(hdrRow!=null)
		{
			//Iterating through each column and checking if value is matching or not
			for(int i=0;i<=hdrRow.getLastCellNum();i++)
			{
				if(getCellValue(hdrRow.getCell(i),(XSSFWorkbook) worksheet.getParent()).equals(colName))
				{
					colIndex=i;
					break;					
				}
			}
		}
		return colIndex;	
	}
	
	public String productFilePath(String strProduct)
	{
		String strFilePath = "";
		
		switch(strProduct.toLowerCase())
		{
		case  "google" :
			strFilePath = "C:/Users/arjun/scrachpad/Scorpion/datafiles/Google.xlsx";
			break;
		case  "twitter" :
			strFilePath = "";
			break;
		case  "facebook" :
			strFilePath = "";
			break;
		case  "instagram" :
			strFilePath = "";
			break;
		default : 
			System.out.println("No Such Case Found");
		}
		return strFilePath;
	}
	
	public ArrayList<XSSFSheet> getAllSheetsBasedonWorkbook(String filePath)
	{
		
		ArrayList<XSSFSheet> wsName = new ArrayList<XSSFSheet>();
		XSSFWorkbook wb = null;
		try {
			File f=new File(filePath);
			boolean blnread = f.canRead();
			FileInputStream fis =null;
			if (f.exists())
			 fis = new FileInputStream(f);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int iSheetCount = wb.getNumberOfSheets();
		for (int i=0 ; i<iSheetCount; i++)
		{
			wsName.add(wb.getSheetAt(i));
		}
		return wsName;
	}
	
	public void ReadExcelToHashMap(String strProduct)
	{
		//Fetch the filePath based on the product
		String strFilePath = productFilePath(strProduct);
		ArrayList<XSSFSheet>allWorkSheets = getAllSheetsBasedonWorkbook(strFilePath);
		// hashmap for Product
		if (!strFilePath.equals(""))
		{
			
			if(!colNameIndexDict.containsKey(strProduct))
			{
				colNameIndexDict.put(strProduct, new HashMap<String,HashMap<String,String>>());
			}
			if(!xpathDict.containsKey(strProduct))
			{
				xpathDict.put(strProduct, new HashMap<String,HashMap<String,String>>());
			}
			if(!objectTypeDict.containsKey(strProduct))
			{
				objectTypeDict.put(strProduct, new HashMap<String,HashMap<String,String>>());
			}
			if(!sbeDict.containsKey(strProduct))
			{
				sbeDict.put(strProduct, new HashMap<String,HashMap<String,HashMap<String,String>>>());
			}
		}
		
		// hashmap for Sheet
		for (XSSFSheet sheet : allWorkSheets)
		{
			if(!colNameIndexDict.get(strProduct).containsKey(sheet.getSheetName()))
			{
				colNameIndexDict.get(strProduct).put(sheet.getSheetName(), new HashMap<String,String>());
			}
			if(!xpathDict.get(strProduct).containsKey(sheet.getSheetName()))
			{
				xpathDict.get(strProduct).put(sheet.getSheetName(), new HashMap<String,String>());
			}
			if(!objectTypeDict.get(strProduct).containsKey(sheet.getSheetName()))
			{
				objectTypeDict.get(strProduct).put(sheet.getSheetName(), new HashMap<String,String>());
			}
			
			if(!sbeDict.get(strProduct).containsKey(sheet.getSheetName()))
			{
				sbeDict.get(strProduct).put(sheet.getSheetName(), new HashMap<String,HashMap<String,String>>());
			}
			
			Row hdRow = getHeaderRow(sheet);
			{
				if(hdRow != null)
				{
					for(Cell cell : hdRow)
					{
						String strCellVal = getCellValue(cell, sheet.getWorkbook());
						if(!strCellVal.equals(""))
						{
							if(!colNameIndexDict.get(strProduct).get(sheet.getSheetName()).containsKey(strCellVal))
							{
								colNameIndexDict.get(strProduct).get(sheet.getSheetName()).put(strCellVal,String.valueOf(cell.getColumnIndex()));
							}
							
							
							if(!xpathDict.get(strProduct).get(sheet.getSheetName()).containsKey(strCellVal))
							{
								xpathDict.get(strProduct).get(sheet.getSheetName()).put(strCellVal,String.valueOf(cell.getColumnIndex()));
							}
							
							if(!objectTypeDict.get(strProduct).get(sheet.getSheetName()).containsKey(strCellVal))
							{
								objectTypeDict.get(strProduct).get(sheet.getSheetName()).put(strCellVal,String.valueOf(cell.getColumnIndex()));
							}
						}
					}
				}
			}
			
			
			
		}
	}
}





