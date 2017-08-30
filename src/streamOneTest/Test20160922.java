package streamOneTest;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test20160922 {
	
	public static void main(String[] args) {
		IntStream is = IntStream.range(1, 100).filter(a -> a % 2 == 0);
		IntStream is2 = IntStream.rangeClosed(1, 100).filter(a -> a % 2 == 0);
		System.out.println(is.count());
		System.out.println(is2.count());
		
		Stream<double[]> sqrt = IntStream.rangeClosed(1, 100).boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100)
						.mapToObj(b -> new double[]{a,b,Math.sqrt(a*a + b*b)})
						.filter(t -> t[2] % 1 == 0));
		sqrt.limit(10).forEach(t -> System.out.println((int)t[0] + "," + (int)t[1] + "," + (int)t[2]));
		
		//1.由值创建流
		Stream<String> s = Stream.of("1","2","3","6");
		Stream<String> s2 = Stream.empty(); //创建空流
		s.forEach(System.out::println);
		
		//2.由数组创建流
		int[] nums = { 11,60,61,12,16,20 };
		int sum = Arrays.stream(nums).sum();
		
		//3.由文件生成流
		long lines = 0;
		try (Stream<String> s3 = Files.lines(Paths.get("data.txt"),
				Charset.defaultCharset())) {
			lines = s3.flatMap(line -> Arrays.stream(line.split(""))).distinct().count();
		} catch (Exception e) {
		}
		System.out.println(sum + "::" + lines);
		
		//4.函数生成流
		//4.1 迭代 iterate(n,lambda) 方法接受初始值，并将lambda作用于每个值
		Stream.iterate(0, a -> a+2).limit(10).forEach(System.out::println);
		//4.1.1 测试 斐波那契数列
		Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0]+ t[1]})
				.limit(10).forEach(t -> System.out.print(t[0]+ " "));
		Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0]+ t[1]})
		.limit(10).map(t -> t[0] + " ").forEach(System.out::print);
		
		//4.2 生成 generate 接受一个 Supplier<T> 类型 Lambda值
		IntStream.generate(new IntSupplier() {
			public int getAsInt(){
				return 2;
			}
		});
		IntStream.generate(() -> 2).limit(10).forEach(System.out::print);;
		//4.2.2 测试 斐波那契数列
		IntStream.generate(new IntSupplier(){			
			private int a = 0;private int b = 1;
			public int getAsInt() {
				int oldPrevious = a;
				int nextValue = a + b;
				a = b;
				b = nextValue;
				return oldPrevious;}
			}).limit(10).forEach(System.out::println);
	}
	
	
}
