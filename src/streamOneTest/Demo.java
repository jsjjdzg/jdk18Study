package streamOneTest;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo {

	public static void main(String[] args) {
		Stream<double[]> s = IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100)
				.mapToObj(b -> new double[] { a, b, Math.sqrt(a * a + b * b) }).filter(t -> t[2] % 1 == 0));
		s.limit(5).forEach(a -> System.out.println(a[0] + " " + a[1] + " " + a[2]));
	}
}
