package streamOneTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FuckPrime implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

	@Override
	public Supplier<Map<Boolean, List<Integer>>> supplier() {
		return () -> new HashMap<Boolean, List<Integer>>() {

			private static final long serialVersionUID = -2542842140223455161L;

			{
				put(true, new ArrayList<Integer>());
				put(false, new ArrayList<Integer>());
			}
		};
	}

	@Override
	public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
		return (Map<Boolean, List<Integer>> map, Integer can) -> {
			map.get(isPrime(map.get(true), can)).add(can);
		};
	}

	@Override
	public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
		return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
			map1.get(true).addAll(map2.get(true));
			map1.get(false).addAll(map2.get(false));
			return map1;
		};
	}

	@Override
	public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
		return Function.identity();
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
	}

	private static boolean isPrime(List<Integer> list, int can) {
		int canRoot = (int) Math.sqrt((double) can);
		return takeWhile(list, i -> i <= canRoot).stream().noneMatch(p -> can % p == 0);
	}

	private static boolean isPrime2(int num) {
		int num2 = (int) Math.sqrt((double) num);
		return IntStream.rangeClosed(2, num2).noneMatch(t -> num % t == 0);
	}

	private static <T> List<T> takeWhile(List<T> list, Predicate<T> p) {
		int i = 0;
		for (T t : list) {
			if (!p.test(t)) {
				return list.subList(0, i);
			}
			i++;
		}
		return list;
	}

	public static Map<Boolean, List<Integer>> getPrime(int n) {
		return IntStream.rangeClosed(2, n).boxed().collect(new FuckPrime());
	}

	public static Map<Boolean, List<Integer>> getPrime2(int n) {
		return IntStream.rangeClosed(2, n).boxed().collect(Collectors.partitioningBy(i -> isPrime2(i)));
	}

	public static void main(String[] args) {
		Long s = System.nanoTime();
		for (int i = 0; i <= 10; i++) {
			getPrime(1_000_000);
		}
		Long e = System.nanoTime();
		System.out.println("自写方法耗时：" + (e - s) / 1_000_000 + "ms");

		Long s2 = System.nanoTime();
		for (int i = 0; i <= 10; i++) {
			getPrime2(1_000_000);
		}
		Long e2 = System.nanoTime();
		System.out.println("Stream分区方法耗时：" + (e2 - s2) / 1_000_000 + "ms");
	}
}
