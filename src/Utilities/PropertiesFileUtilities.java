package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesFileUtilities {

	public static String getValueForKey(String Key) throws Exception{
		Properties p=new Properties();
		FileInputStream fis=new FileInputStream("D:\\Selenium_Prathyusha\\SA_Hybrid\\Properties\\Environment.properties");
		p.load(fis);
		return p.getProperty(Key);
	}
}
