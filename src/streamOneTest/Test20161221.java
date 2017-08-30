package streamOneTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test20161221 {

	static Trader a = new Trader("BB", "Bc");
	static Trader b = new Trader("CC", "Ac");
	static Trader c = new Trader("AA", "Bc");
	static Trader d = new Trader("DD", "Bc");

	static List<Transaction> trans = Arrays.asList(new Transaction(a, 2011, 300, true),
			new Transaction(b, 2012, 1000, false), new Transaction(b, 2011, 1300, true),
			new Transaction(c, 2012, 500, false), new Transaction(c, 2012, 400, false),
			new Transaction(d, 2012, 700, true));

	public enum Level {
		LOW, NORMAL, HIGH
	};
	
	static List<Transaction> l = trans.stream().collect(Collectors.toList());
	static List<Transaction> l2 = trans.stream().collect(ArrayList::new, List::add, List::addAll);
	
	public static void main(String[] args) {
		System.out.println(l);
		System.out.println(l2);
	}
	
}
