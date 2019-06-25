

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;

public class ReadConfig {
	
	
	public HashMap<String, String> readConfigFile() throws IOException
	{
		
		File file = new File("C:\\Users\\arjun\\scrachpad\\Scorpion\\datafiles\\Config.txt");
		BufferedReader br = null;
		String str;
		HashMap<String, String> configData = new HashMap<String, String>();
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] arr = null;
		
		while ((str = br.readLine()) != null)
		{
			arr = str.split("=");
			configData.put(arr[0], arr[1]);
		}
		
		return configData;
	}
	
	

}
