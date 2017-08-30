package java20170118;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 
 * 求Long[] l的和的 demo
 * 
 * @author DZG
 * @since V1.0 2017年1月20日
 */
public class ForkJoinSum extends RecursiveTask<Long> {

	private static final long serialVersionUID = 1L;
	private long[] numbers;
	private int start;
	private int end;

	private static long THRESHOLD = 10_000;// 最低任务量

	public ForkJoinSum(long[] numbers) {
		this(numbers, 0, numbers.length);
	}

	public ForkJoinSum(long[] numbers, int start, int end) {
		super();
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		int length = end - start;
		if (length < THRESHOLD) {
			return computeValue();
		}
		ForkJoinSum leftTask = new ForkJoinSum(numbers, start, start + length / 2);
		leftTask.fork();
		ForkJoinSum rightTask = new ForkJoinSum(numbers, start + length / 2, end);
		Long rightResult = rightTask.compute();
		Long leftResult = leftTask.join();
		return rightResult + leftResult;
	}

	private long computeValue() {
		long sum = 0;
		for (int i = start; i < end; i++) {
			sum += numbers[i];
		}
		return sum;
	}

	public static Long forkJoinSum(long n) {
		long[] numbers = LongStream.rangeClosed(1, n).toArray();
		ForkJoinTask<Long> task = new ForkJoinSum(numbers);
		return new ForkJoinPool().invoke(task);
	}
}
