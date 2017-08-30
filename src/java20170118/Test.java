package java20170118;

public class Test {

	public static void main(String[] args) {
		Integer i1 = new Integer(127);
		Integer i2 = new Integer(127);
		
		Integer i1_1 = Integer.valueOf(127);
		Integer i2_2 = Integer.valueOf(127);
		
		System.out.println(i1 == i2);//false
		System.out.println(i1_1 == i2_2);//true
		
		Integer i3 = new Integer(128);
		Integer i4 = new Integer(128);
		Integer i3_1 = Integer.valueOf(129);
		Integer i4_2 = Integer.valueOf(129);
		
		System.out.println(i3 == i4);//false
		System.out.println(i3_1 == i4_2);//false
		
		int int1 = 127;
		int int2 = 127;
		
		System.out.println(int1 == int2);//true
		
		System.out.println(int1 == i1);//true
	}
}
