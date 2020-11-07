package commonLibs.utils;

import java.util.Date;

public class DateUtils {
	
	public static String getCurentTime() {
		Date date = new Date();
		
		return String.valueOf(date.getTime());
	}

}
