package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author 597059
 */
public class StreamDemo {

    public static void main(String[] args) {
        demoOne();
    }


    /**
     * 通过 java.util.Collection.stream() 方法用集合创建流
     */
    private static void demoOne() {
        List<String> list = Arrays.asList("a", "b", "c");
        // 创建一个顺序流
        Stream<String> stream = list.stream();
        // 创建一个并行流
        Stream<String> parallelStream = list.parallelStream();
        System.out.println(stream);
        System.out.println(parallelStream);
    }

    /**
     * 使用java.util.Arrays.stream(T[] array)方法用数组创建流
     */
    private static void demoTwo() {

        int[] array = {1, 3, 5, 6, 8};
        IntStream stream = Arrays.stream(array);
        System.out.println(stream);

    }

    /**
     * 使用Stream的静态方法：of()、iterate()、generate()
     */
    private static void demoThree() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 3).limit(4);
        stream2.forEach(System.out::println);
        Stream<Double> stream3 = Stream.generate(Math::random).limit(3);
        stream3.forEach(System.out::println);


    }


}
