package streamOneTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapDemo {

	private static String helloworld = "Hello,World";
	private static Integer[] ints = { 1, 2, 3, 4, 5 };
	private static List<Integer> i1 = Arrays.asList(1, 2, 3);
	private static List<Integer> i2 = Arrays.asList(4, 5);

	public static void main(String[] args) {
		Stream<String> s = Arrays.stream(helloworld.split(","));
		List<Stream<String>> list = s.map(w -> w.split("")).map(Arrays::stream).distinct().collect(Collectors.toList());
		System.out.println(list);
		s = Arrays.stream(helloworld.split(","));
		List<String> list2 = s.map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
		System.out.println(list2);

		System.out.println(Arrays.asList(ints).stream().map(a -> a * a).collect(Collectors.toList()));

		List<int[]> intabs = i1.stream().flatMap(a -> i2.stream().map(b -> new int[] { a, b }))
				.collect(Collectors.toList());
		intabs.stream().forEach(a -> System.out.println("(" + a[0] + "," + a[1] + ")"));

		List<int[]> intabs2 = i1.stream()
				.flatMap(a -> i2.stream().filter(b -> (a + b) % 3 == 0).map(b -> new int[] { a, b }))
				.collect(Collectors.toList());
		intabs2.stream().forEach(a -> System.out.println("(" + a[0] + "," + a[1] + ")"));

		Optional<Integer> oi = i1.stream().filter(a -> a.intValue() > 0).findAny();
		System.out.println(oi.get());
		oi.ifPresent(a -> System.out.println(a));
	}
}
