package commonLibs.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileUtils {
	
	public static Properties configFileReader(String configFilename) throws Exception {
		
		configFilename = configFilename.trim();
				
		Properties configProperties = new Properties();
		
		InputStream inStream = new FileInputStream(configFilename);
		
		configProperties.load(inStream);
		
		return configProperties;
	}

}
