package streamOneTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToCollectionList<T> implements Collector<T, List<T>, List<T>>{

	@Override
	//供应源--建立新的结果容器
	public Supplier<List<T>> supplier() {
		return ArrayList::new;
	}

	@Override
	//累加器--将元素添加到结果容器中
	public BiConsumer<List<T>, T> accumulator() {
		return List::add;
	}

	@Override
	//组合器--合并两个结果容器
	public BinaryOperator<List<T>> combiner() {
		return (listA, listB) -> {
			listA.addAll(listB);
			return listA;
		};
	}

	@Override
	//恒等函数--对结果容器应用转换
	public Function<List<T>, List<T>> finisher() {
		return Function.identity();
	}

	@Override
	//返回不可变Characteristics,定义收集器的行为，尤其是是否进行并行归约
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
	}

}
