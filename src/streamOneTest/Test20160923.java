package streamOneTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * 收集器
 * 
 * @author DZG
 * @since V1.0 2016年9月23日
 */
public class Test20160923 {

	static Trader a = new Trader("BB", "Bc");
	static Trader b = new Trader("CC", "Ac");
	static Trader c = new Trader("AA", "Bc");
	static Trader d = new Trader("DD", "Bc");
	
	static List<Transaction> trans = Arrays.asList(new Transaction(a, 2011, 300, true),
			new Transaction(b, 2012, 1000, false), new Transaction(b, 2011, 1300, true),
			new Transaction(c, 2012, 500, false), new Transaction(c, 2012, 400, false),
			new Transaction(d, 2012, 700, true));
	
	public static void main(String[] args) {
		
		//分组
		System.out.println(trans.parallelStream().collect(Collectors.groupingBy(Transaction::getYear)));
	}
}
