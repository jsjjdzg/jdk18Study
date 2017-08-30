package streamOneTest;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * 
 * 类描述
 * @author DZG
 * @since V1.0 2016年11月16日
 */
public class Test2333 {

	public static Map<Boolean,List<Integer>> getPrime(int n){
		return IntStream.rangeClosed(0, n).boxed().collect(new FuckPrime());
	}
	
	public static void main(String[] args) {
		getPrime(100);
	}
}
