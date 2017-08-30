package streamOneTest;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test {

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

	public static void main(String[] args) {
		// 1
		// System.out.println(trans.parallelStream().filter(Transaction -> Transaction.getYear() == 2011)
		// .sorted(Comparator.comparing(Transaction::getValue)).collect(toSet()));
		// // 2
		// System.out.println(trans.parallelStream().map(Transaction -> Transaction.getTrader().getCity()).distinct()
		// .collect(toSet()));
		// // 3
		// System.out.println(
		// trans.parallelStream().map(Transaction::getTrader).distinct().filter(Trader -> Trader.getCity() == "Bc")
		// .sorted(Comparator.comparing(Trader::getName)).collect(toList()));
		// // 4
		// System.out.println(trans.parallelStream().map(Transaction -> Transaction.getTrader().getName()).distinct()
		// .sorted().collect(joining()));
		// // 5
		// System.out.println(trans.parallelStream().map(Transaction::getTrader).distinct()
		// .filter(Trader -> Trader.getCity() == "Ac").collect(toList()));
		// System.out.println(
		// trans.parallelStream().anyMatch(Transaction -> "Ac".equals(Transaction.getTrader().getCity())));
		// // 6
		// System.out.println(trans.parallelStream().filter(Transaction -> Transaction.getTrader().getCity() == "Bc")
		// .map(Transaction::getValue).reduce(0, Integer::sum));
		// trans.parallelStream().filter(Transaction -> Transaction.getTrader().getCity() == "Bc")
		// .map(Transaction::getValue).forEach(System.err::println);
		// // 7
		// System.out.println(trans.parallelStream().map(Transaction::getValue).reduce(0, Integer::max));
		// // 8
		// System.out.println(trans.parallelStream().sorted(Comparator.comparing(Transaction::getValue)).findFirst());
		// System.out.println(trans.parallelStream().min(Comparator.comparing(Transaction::getValue)));

		// 分组

		Map<Integer, List<Transaction>> maps = trans.parallelStream().collect(groupingBy(Transaction::getValue));
		Map<Level, List<Transaction>> maps2 = trans.parallelStream().collect(groupingBy(t -> {
			if (t.getValue() <= 500)
				return Level.LOW;
			else if (t.getValue() <= 1000)
				return Level.NORMAL;
			else
				return Level.HIGH;
		}));
		Map<Integer, Map<Level, List<Transaction>>> maps3 = trans.parallelStream()
				.collect(groupingBy(Transaction::getYear, groupingBy(t -> {
					if (t.getValue() <= 500)
						return Level.LOW;
					else if (t.getValue() <= 1000)
						return Level.NORMAL;
					else
						return Level.HIGH;
				})));
		Map<Integer, Optional<Transaction>> maps4 = trans.stream()
				.collect(groupingBy(Transaction::getYear, maxBy(comparingInt(Transaction::getValue))));
		// 返回每个子组里最大的值
		Map<Integer, Transaction> maps5 = trans.stream().collect(groupingBy(Transaction::getYear,
				collectingAndThen(maxBy(comparing(Transaction::getValue)), Optional::get)));
		Map<Integer, Integer> maps6 = trans.stream()
				.collect(groupingBy(Transaction::getYear, summingInt(Transaction::getValue)));
		Map<Integer, Set<Level>> maps7 = trans.stream()
				.collect(groupingBy(Transaction::getYear, Collectors.mapping(t -> {
					if (t.getValue() <= 500)
						return Level.LOW;
					else if (t.getValue() <= 1000)
						return Level.NORMAL;
					else
						return Level.HIGH;
				}, toSet())));
		Map<Integer, Set<Level>> maps8 = trans.stream()
				.collect(groupingBy(Transaction::getYear, Collectors.mapping(t -> {
					if (t.getValue() <= 500)
						return Level.LOW;
					else if (t.getValue() <= 1000)
						return Level.NORMAL;
					else
						return Level.HIGH;
				}, Collectors.toCollection(HashSet::new))));
		// System.out.println(maps8);

		// 分区
		Map<Boolean, List<Transaction>> map1 = trans.stream().collect(Collectors.partitioningBy(Transaction::getIsOk));
		Map<Boolean, Map<Integer, List<Transaction>>> map2 = trans.stream()
				.collect(Collectors.partitioningBy(Transaction::getIsOk, groupingBy(Transaction::getYear)));
		Map<Boolean, Transaction> map3 = trans.stream().collect(Collectors.partitioningBy(Transaction::getIsOk,
				collectingAndThen(maxBy(Comparator.comparing(Transaction::getValue)), Optional::get)));

		Map<Boolean, List<Integer>> map4 = getNum(30);

		System.out.println(map4);
	}

	private static boolean isPrime(int num) {
		int num2 = (int) Math.sqrt((double) num);
		return IntStream.rangeClosed(2, num2).noneMatch(t -> num % t == 0);
	}

	private static Map<Boolean, List<Integer>> getNum(int n) {
		return IntStream.rangeClosed(2, n).boxed().collect(Collectors.partitioningBy(t -> isPrime(t)));
	}
}
