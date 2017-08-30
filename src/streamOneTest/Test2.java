package streamOneTest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Test2 {

	static Trader a = new Trader("BB", "Bc");
	static Trader b = new Trader("CC", "Ac");
	static Trader c = new Trader("AA", "Bc");
	static Trader d = new Trader("DD", "Bc");
	
	static List<Transaction> trans = Arrays.asList(new Transaction(a, 2011, 300, true),
			new Transaction(b, 2012, 1000, false), new Transaction(b, 2011, 1300, true),
			new Transaction(c, 2012, 500, false), new Transaction(c, 2012, 400, false),
			new Transaction(d, 2012, 700, true));
	
	public static void main(String[] args) {
		//1
		System.out.println(
				trans.parallelStream()
				.filter(Transaction -> Transaction.getYear() == 2011)
				.sorted(Comparator.comparing(Transaction::getValue))
				.collect(Collectors.toSet())
				);
		//2
		System.out.println(
				trans.parallelStream()
				.map(Transaction -> Transaction.getTrader().getCity())
				.collect(Collectors.toSet())
				);
		//3
		System.out.println(
				trans.parallelStream()
				.map(Transaction::getTrader)
				.distinct()
				.filter(Trader -> "Bc".equals(Trader.getCity()))
				.sorted(Comparator.comparing(Trader::getName))
				.collect(Collectors.toList())
				);
		//4
		System.out.println(
				trans.parallelStream()
				.map(Transaction -> Transaction.getTrader().getName())
				.distinct()
				.sorted()
				.collect(Collectors.joining())
				);
		//5
		System.out.println(
				trans.parallelStream()
				.anyMatch(Transaction -> "Ac".equals(Transaction.getTrader().getCity()))
				);
		//6
		trans.parallelStream().filter(Transaction -> "Bc".equals(Transaction.getTrader().getCity()))
							.map(Transaction::getValue)
							.forEach(System.out::println);
		//7
		System.out.println(
				trans.parallelStream()
				.map(Transaction::getValue)
				.reduce(0,Integer::max)
				);
		System.out.println(
				trans.parallelStream()
				.mapToInt(Transaction::getValue)
				.max().getAsInt()
				);
		//8
		System.out.println(
				trans.parallelStream()
				.min(Comparator.comparing(Transaction::getValue))
				);
	}
}
