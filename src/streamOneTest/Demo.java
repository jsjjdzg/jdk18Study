package streamOneTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Demo {

	
	public static void main(String[] args) {
		String str = "2017-01-05 15:00";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			System.err.println(format.parse(str));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
