package java20170118;

import java.util.Date;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Demo1 {

	public static void main(String[] args) {
		test1(10023000);
		test2(10023000);
	}

	private static void test2(long n) {
		long t0 = new Date().getTime();
		LongStream.rangeClosed(0, n).reduce(0L, Long::sum);
		long t00 = new Date().getTime();
		System.err.println(t00 - t0);

		long t1 = new Date().getTime();
		LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
		long t2 = new Date().getTime();
		System.err.println(t2 - t1);

		long t3 = new Date().getTime();
		long t4 = new Date().getTime();
		System.err.println(t4 - t3);
	}
	
	private static void test1(long n) {
		long t0 = new Date().getTime();
		Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
		long t00 = new Date().getTime();
		System.err.println(t00 - t0);

		long t1 = new Date().getTime();
		Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
		long t2 = new Date().getTime();
		System.err.println(t2 - t1);

		long t3 = new Date().getTime();
		long t4 = new Date().getTime();
		System.err.println(t4 - t3);
	}
}
