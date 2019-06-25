

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExceptionHandling {
	
	
	
	
	public void test()
	{
		//Read file abc 
		File file = new File("C:\\Users\\arjun\\scrachpad\\Scorpion\\datafiles\\Driver.xlsx");
			
		
		try {
			FileInputStream fis = new FileInputStream(file);
		} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println(" I m in catch");
			}
		finally{
			System.out.println(" I m in finally");
			
		}
			
		
		
				
		XSSFWorkbook wb = new XSSFWorkbook();
		
			try {
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	}
	
	public void error1() throws ArithmeticException,ArrayIndexOutOfBoundsException
	{
//		try 
//		{
			error();
//		}
//		catch(ArrayIndexOutOfBoundsException ae)
//		{
//			System.out.println("level 2 catch");
//		}
	}
	
	public void error() throws ArithmeticException,ArrayIndexOutOfBoundsException
	{
		
		int a[]=new int[5];
		a[6] = 30/1;
		
		
//		catch(ArithmeticException ae )
//		{
//			System.out.println("Not divisible by 0");
//			
//		}
//		catch(ArrayIndexOutOfBoundsException ae )
//		{
//			System.out.println("Array");
//		}
//		catch(Exception e){
//			System.out.println("General Exception");
//		}
		
		
		System.out.println("Test");
		
	}
	public static void main(String[] args) throws ArithmeticException{
		ExceptionHandling e = new ExceptionHandling();
		try{
		e.error1();}
		catch(ArrayIndexOutOfBoundsException ae)
		{
			System.out.println("main catch");
		}
		
	}

}
