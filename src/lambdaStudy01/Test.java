package lambdaStudy01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Test {
	
	private static String add1(String txt){
		return txt + " 添加头部 ";
	}
	private static String add2(String txt){
		return txt + "添加中部 ";
	}
	private static String add3(String txt){
		return txt + "添加尾部 ";
	}

	static int number = 123;
	
	static Function<Integer, Integer> f = x -> x+1;
	static Function<Integer, Integer> h = x -> x*2;
	static Function<Integer, Integer> m = f.andThen(h);
	static Function<Integer, Integer> n = f.compose(h);
	
	static Function<String, String> t = Test :: add1;
	static Function<String, String> txt = t.andThen(Test :: add3);
	static Function<String, String> txt2 = t.andThen(Test :: add2).andThen(Test :: add3);
	
	public static String execute(BufferReaderProcessor p) throws IOException{
		try(BufferedReader br = new BufferedReader(new FileReader("C:/Users/DZG/Desktop/SVN.txt"))){
			return p.process(br);
		}
	}
	
	/**
	 * Predicate接口定义了一个test方法，接受泛型T对象，返回的是一个boolean。
	 */
	public static <T> List<T> filter(List<T> list,Predicate<T> p){
		List<T> result = new ArrayList<T>();
		for(T t:list){
			if(p.test(t)){
				result.add(t);
			}
		}
		return result;
	}
	
	/**
	 * Consumer接口定义了一个accept的方法，接受泛型T对象，无返回void
	 */
	public static <T> void forEach(List<T> list,Consumer<T> c){
		for(T i:list){
			c.accept(i);
		}
	}
	
	/**
	 * Function接口定义了一个apply的方法，接受泛型为T的对象，返回泛型为R的对象
	 */
	public static <T,R> List<R> map(List<T> list,Function<T,R> f){
		List<R> listR = new ArrayList<>();
		for(T t:list){
			listR.add(f.apply(t));
		}
		return listR;
	}
	
	static Random r = new Random();
	
	//一个参数 Function
	private static List<Fan> map(List<String> citys){
		Function<String,Fan> fu = Fan :: new;
		List<Fan> fans = new ArrayList<>();
		
		for(String s : citys){
			fans.add(fu.apply(s));
		}
		
		return fans;
	}
	
	//两个参数 BiFunction
	private static List<Fan> map2(List<String> citys){
		BiFunction<String,String,Fan> fu = Fan :: new;
		List<Fan> fans = new ArrayList<>();
		
		for(String s : citys){
			fans.add(fu.apply(s,String.valueOf(r.nextInt(10))));
		}
		
		return fans;
	}
	
	//三个参数及以上  需要自定义 BiN..NFunction
	private static List<Fan> map3(List<String> citys){
		BiThreeFunction<String,String,String,Fan> fu = Fan :: new;
		List<Fan> fans = new ArrayList<>();
		
		for(String s : citys){
			fans.add(fu.apply(String.valueOf(r.nextInt(10)),s,String.valueOf(r.nextInt(10))));
		}
		
		return fans;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		List<String> citys = Arrays.asList("苏州23232","上海222","北京111","靖江343434343");
		
		List<Fan> fans = map(citys);
		for(Fan fan : fans){
			System.err.println(fan.toString());
		}
		
		System.err.println("--------------------");
		List<Fan> fans2 = map2(citys);
		for(Fan fan : fans2){
			System.err.println(fan.toString());
		}
		
		System.err.println("========================");
		List<Fan> fans3 = map3(citys);
		for(Fan fan : fans3){
			System.err.println(fan.toString());
		}
		
		//fans2.sort(Comparator.comparing((f) -> f.getLength()));
		fans3.sort(Comparator.comparing(Fan :: getLength).reversed().thenComparing(Fan :: getIdlen));
		
		System.err.println("+++++++++++++++++++++++++++++");
//		for(Fan fan : fans2){
//			System.err.println(fan.toString());
//		}
//		System.err.println("_____________________________");
		for(Fan fan : fans3){
			System.err.println(fan.toString());
		}
		
		System.out.println(m.apply(2));
		System.out.println(n.apply(2));
		System.out.println(txt2.apply("丁正刚"));
		System.out.println(txt.apply("郑总"));
		
//		String oneLine = execute((BufferedReader br) -> br.readLine());//该lambda返回的是String
//		String TwoLine = execute((BufferedReader br) -> br.readLine() + br.readLine());
//		System.out.println(oneLine + "- -" + TwoLine);
//		
//		List<String> Strings = filter(Arrays.asList("1","2","3"),(String s) -> !s.isEmpty());//Predicate示例
//		forEach(Strings,(String i) -> System.out.println(i));//Consumer示例
//		List<Integer> ints = map(Arrays.asList("Lambda","in","action"),(String s) -> s.length());//Function示例 从String类型变为Integer类型
//		forEach(ints,(Integer i) -> System.out.println("长度:"+i));//Consumer示例
//		
//		Runnable r = () -> System.out.println(number);
//		r.run();
//		number = 234;
//		r.run();
		
//		Supplier<Fan> su = Fan :: new;
//		Fan fan = su.get();
//		
//		Function<String, Fan> fu = Fan :: new;
//		fan = fu.apply("苏州");
//		System.err.println(fan.toString());
		test();
		test2();
		test3();
		test4();
	}
	
	
	public static void test(){
		if(((BooleanSupplier)(() -> { System.out.print("a");return false;})).getAsBoolean()){
			System.out.print("a");
		}else{
			System.out.print("b");
		}
	}
	
	public static void test2(){
		if(System.out.printf("a") == null){
			System.out.print("a");
		}else{
			System.out.print("b");
		}
	}
	
	public static void test3(){
		if(new Object(){
			public boolean foo(){
				System.out.print("a");
				return false;
			}
		}.foo()){
			System.out.print("a");
		}else{
			System.out.print("b");
		}
	}
	
	public static void test4(){
		if("1".equals("1")){
			System.out.print("a");
		}else{}
		System.out.print("b");
	}
}
