package lambdaStudy01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 
 * Stream流测试类
 * @author DZG
 * @since V1.0 2016年9月18日
 */
public class StreamOne {

	public static void main(String[] args) {
		List<Integer> menu =  Arrays.asList(1,2,3,4,4,5,6,4,7,8,9);
		List<Integer> menu2 = menu.stream().map(a -> a * 2).distinct().limit(100).collect(Collectors.toList());
		System.out.println(menu2);
		
		Iterator<Integer> menu3 = menu.iterator();
		List<Integer> menu3X =  new ArrayList<>();
		while(menu3.hasNext()){
			menu3X.add(menu3.next());		
		}
		System.out.println(menu3X);
		
		List<String> strs = Arrays.asList("Hello","World");
		System.out.println(strs.stream().map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList()));
		
		List<Integer> ins = Arrays.asList(1,2,3);
		List<Integer> ins2 = Arrays.asList(3,4);
		System.out.println(ins.stream().map(w -> w*w).collect(Collectors.toList()));
		
		List<int[]> paras =
				ins.stream()
					.flatMap(i -> 
					ins2.stream()
					.filter(j -> (i+j)%3 == 0)
					.map(j -> new int[]{i,j}))					
					.collect(Collectors.toList());
		System.out.println(paras);
		
		if(ins.stream().anyMatch(a -> a > 3)){
			System.err.println("呵呵");
		}
		
		System.out.println(ins2.stream().reduce(0,Integer::sum));
		System.out.println(ins2.stream().reduce(Integer::max));
		
		System.out.println(ins.parallelStream().map(d -> 1).reduce(0,Integer::sum));
		System.out.println(ins.parallelStream().count());
	}
}
