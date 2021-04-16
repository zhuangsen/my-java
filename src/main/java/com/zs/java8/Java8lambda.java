package com.zs.java8;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Java8lambda {

    public static void main(String[] args) {
//		Arrays.asList( "a", "b", "d" ).forEach( e -> System.out.println( e ) );
//		Arrays.asList( "a", "b", "d" ).forEach( ( String e ) -> System.out.println( e ) );

//		Arrays.asList( "a", "b", "d" ).forEach( e -> {
//		    System.out.print( e );
//		    System.out.print( e );
//		} );

//		Lambda表达式可以引用类成员和局部变量（会将这些变量隐式得转换成final的），例如下列两个代码块的效果完全相同：
        String separator = ",";
        Arrays.asList("a", "b", "d").forEach(
                (String e) -> System.out.print(e + separator));


//		Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );

        Arrays.asList("a", "b", "d").sort((e1, e2) -> {
            int result = e1.compareTo(e2);
            return result;
        });


        Random random = new Random();
        // 随机产生数据
        Stream<Integer> stream = Stream.generate(() -> random.nextInt())
                // 产生500个 ( 无限流需要短路操作. )
                .limit(500)
                // 第1个无状态操作
                .peek(s -> print("peek: " + s))
                // 第2个无状态操作
                .filter(s -> {
                    print("filter: " + s);
                    return s > 1000000;
                })
                // 有状态操作
                .sorted((i1, i2) -> {
                    print("排序: " + i1 + ", " + i2);
                    return i1.compareTo(i2);
                })
                // 又一个无状态操作
                .peek(s -> {
                    print("peek2: " + s);
                }).parallel();

        // 终止操作
        System.out.println(stream.count());;
    }

    /**
     * 打印日志并sleep 5 毫秒
     *
     * @param s
     */
    public static void print(String s) {
        // System.out.println(s);
        // 带线程名(测试并行情况)
        System.out.println(Thread.currentThread().getName() + " > " + s);
        try {
            TimeUnit.MILLISECONDS.sleep(5);
        } catch (InterruptedException e) {
        }
    }
}
