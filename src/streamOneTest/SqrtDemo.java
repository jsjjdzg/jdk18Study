package streamOneTest;

import java.util.Date;

/**
 * 实现一个函数, 完成 开根号 的操作, 方法签名如下.
 * 
 * double sqrt(int v, double t)
 * <p>
 * 要求:
 * 
 * 不能调用系统库函数, 诸如 Math.sqrt(v) 之类的; 假设计算出的结果为 r, 要求满足如下条件, , 其中 是真实的值, t 为给定的一个误差范围, 例如0.1等, 即你计算出的值要在给定的误差范围内. 实现语言不限,
 * 你条件可以比上述更加苛刻, 但不能宽松, 举例而言, 我调用你的接口 sqrt(9, 0.21) 返回值属于 [2.79, 3.21] 这个区间的任意一个都满足条件.
 * </p>
 * 
 * @author DZG
 * @since V1.0 2016年12月20日
 */
public class SqrtDemo {

	// 穷举法
	private static double sqrt1(int v, double t) {
		double result = 0;
		Long start = new Date().getTime();

		while (true) {
			if ((result + t) * (result + t) >= Math.abs(v - t)) {
				Long end = new Date().getTime();
				System.out.println("自写爆菊耗时：" + (end - start) + "ms");
				return result;
			}
			result += t;// 适当分段
		}
	}

	// 二分法
	private static double sqrt2(int v, double t) {
		Long start = new Date().getTime();
		double value = v / 2;
		double low = 0, up = v;

		while (Math.abs(up - value * value) > t) {
			if (value * value > v) {
				low = 0;
				up = value;
			} else if (value * value < v) {
				low = value;
			}
			if (value == (low + up) / 2)
				break;
			value = (low + up) / 2;
		}
		Long end = new Date().getTime();
		System.out.println("自写二分耗时：" + (end - start) + "ms");
		return value;
	}

	// !!牛顿快速迭代法
	// 求出根号a的近似值：首先随便猜一个近似值x，然后不断令x等于x和a/x的平均数，迭代个六七次后x的值就已经相当精确了。
	// 不断用(x,f(x))的切线来逼近方程x^2-a=0的根。根号a实际上就是x^2-a=0的一个正实根，这个函数的导数是2x。
	// 也就是说，函数上任一点(x,f(x))处的切线斜率是2x。那么，x-f(x)/(2x)就是一个比x更接近的近似值。
	// 代入f(x)=x^2-a得到x-(x^2-a)/(2x)，也就是(x+a/x)/2。
	private static double sqrt3(int v, double t) {
		double result = 0, value = v;
		Long start = new Date().getTime();
		while (true) {
			result = value;
			value = (value + v / value) / 2;
			if (Math.abs(result - value) <= t) {
				Long end = new Date().getTime();
				System.out.println("自写牛顿耗时：" + (end - start) + "ms");
				break;
			}
		}
		return result;
	}


	public static void main(String[] args) {
		System.out.println(sqrt1(10000000, 0.00001));
		System.out.println(sqrt2(1000000000, 0.00000001));
		System.out.println(sqrt3(1000000000, 0.00000001));

		Long start = new Date().getTime();
		Math.sqrt(10000000);
		Long end = new Date().getTime();
		System.out.println("系统sqrt耗时：" + (start - end) + "ms");
		System.out.println(Math.sqrt(1000000000));
	}
}
