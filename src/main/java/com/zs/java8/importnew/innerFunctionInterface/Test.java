package com.zs.java8.importnew.innerFunctionInterface;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Test {

    public static void main(String[] args) {
        // Predicates
        // Predicate是一个布尔类型的函数，该函数只有一个输入参数。
        // Predicate接口包含了多种默认方法，用于处理复杂的逻辑动词（and, or，negate）
        Predicate<String> predicate = (s) -> s.length() > 0;

        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        // Functions
        // Function接口接收一个参数，并返回单一的结果。默认方法可以将多个函数串在一起（compse, andThen）
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        backToString.apply("123");     // "123"

        // Suppliers
        // Supplier接口产生一个给定类型的结果。与Function不同的是，Supplier没有输入参数。
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();   // new Person

        // Consumers
        // Consumer代表了在一个输入参数上需要进行的操作。
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));

        // Comparators
        // Comparator接口在早期的Java版本中非常著名。Java 8 为这个接口添加了不同的默认方法。
        Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");

        comparator.compare(p1, p2);             // > 0
        comparator.reversed().compare(p1, p2);  // < 0

        // Optionals
        // Optional不是一个函数式接口，而是一个精巧的工具接口，用来防止NullPointerEception产生。
        // 这个概念在下一节会显得很重要，所以我们在这里快速地浏览一下Optional的工作原理。
        // Optional是一个简单的值容器，这个值可以是null，也可以是non-null。
        // 考虑到一个方法可能会返回一个non-null的值，也可能返回一个空值。为了不直接返回null，我们在Java 8中就返回一个Optional.
        Optional<String> optional = Optional.of("bam");

        optional.isPresent();           // true
        optional.get();                 // "bam"
        optional.orElse("fallback");    // "bam"

        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"


        // Streams
        // java.util.Stream表示了某一种元素的序列，在这些元素上可以进行各种操作。
        // Stream操作可以是中间操作，也可以是完结操作。
        // 完结操作会返回一个某种类型的值，而中间操作会返回流对象本身，
        // 并且你可以通过多次调用同一个流操作方法来将操作结果串起来（就像StringBuffer的append方法一样————译者注）。
        // Stream是在一个源的基础上创建出来的，例如java.util.Collection中的list或者set（map不能作为Stream的源）。
        // Stream操作往往可以通过顺序或者并行两种方式来执行。

        // 我们先了解一下序列流。首先，我们通过string类型的list的形式创建示例数据：
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        // Filter
        // Filter接受一个predicate接口类型的变量，并将所有流对象中的元素进行过滤。
        // 该操作是一个中间操作，因此它允许我们在返回结果的基础上再进行其他的流操作（forEach）。
        // ForEach接受一个function接口类型的变量，用来执行对每一个元素的操作。
        // ForEach是一个中止操作。它不返回流，所以我们不能再调用其他的流操作。
        stringCollection
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);// "aaa2", "aaa1"

        // Sorted
        // Sorted是一个中间操作，能够返回一个排过序的流对象的视图。
        // 流对象中的元素会默认按照自然顺序进行排序，除非你自己指定一个Comparator接口来改变排序规则。
        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println); //"aaa1", "aaa2"
        // 一定要记住，sorted只是创建一个流对象排序的视图，而不会改变原来集合中元素的顺序。原来string集合中的元素顺序是没有改变的。
        System.out.println(stringCollection);

        // Map
        // map是一个对于流对象的中间操作，通过给定的方法，它能够把流对象中的每一个元素对应到另外一个对象上。
        // 下面的例子就演示了如何把每个string都转换成大写的string.
        // 不但如此，你还可以把每一种对象映射成为其他类型。对于带泛型结果的流对象，具体的类型还要由传递给map的泛型方法来决定。
        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println); // "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"

        // Match
        // 匹配操作有多种不同的类型，都是用来判断某一种规则是否与流对象相互吻合的。所有的匹配操作都是终结操作，只返回一个boolean类型的结果。
        boolean anyStartsWithA =
                stringCollection
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));

        System.out.println(anyStartsWithA);      // true

        boolean allStartsWithA =
                stringCollection
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA);      // false

        boolean noneStartsWithZ =
                stringCollection
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));

        System.out.println(noneStartsWithZ);      // true

        // Count
        // Count是一个终结操作，它的作用是返回一个数值，用来标识当前流对象中包含的元素数量。
        long startsWithB =
                stringCollection
                        .stream()
                        .filter((s) -> s.startsWith("b"))
                        .count();

        System.out.println(startsWithB);    // 3

        // Reduce
        // 该操作是一个终结操作，它能够通过某一个方法，对元素进行削减操作。该操作的结果会放在一个Optional变量里返回。
        Optional<String> reduced =
                stringCollection
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println); // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"

        // Parallel Streams
        // 像上面所说的，流操作可以是顺序的，也可以是并行的。顺序操作通过单线程执行，而并行操作则通过多线程执行。
        // 下面的例子就演示了如何使用并行流进行操作来提高运行效率，代码非常简单。
        // 首先我们创建一个大的list，里面的元素都是唯一的：
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        // 现在，我们测量一下对这个集合进行排序所使用的时间。
        // 顺序排序
        long t0 = System.nanoTime();
        long count = values.stream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));
        // sequential sort took: 899 ms

        // 并行排序
        long t2 = System.nanoTime();
        long count1 = values.parallelStream().sorted().count();
        System.out.println(count1);
        long t3 = System.nanoTime();
        long millis1 = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
        System.out.println(String.format("parallel sort took: %d ms", millis1));
        // parallel sort took: 472 ms

        // Map
        // 正如前面已经提到的那样，map是不支持流操作的。而更新后的map现在则支持多种实用的新方法，来完成常规的任务。
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }
        map.forEach((id, val) -> System.out.println(val));
        // 上面的代码风格是完全自解释的：putIfAbsent避免我们将null写入；forEach接受一个消费者对象，从而将操作实施到每一个map中的值上。
        // 下面的这个例子展示了如何使用函数来计算map的编码
        map.computeIfPresent(3, (num, val) -> val + num);
        map.get(3);             // val33

        map.computeIfPresent(9, (num, val) -> null);
        map.containsKey(9);     // false

        map.computeIfAbsent(23, num -> "val" + num);
        map.containsKey(23);    // true

        map.computeIfAbsent(3, num -> "bam");
        map.get(3);             // val33

        // 接下来，我们将学习，当给定一个key值时，如何把一个实例从对应的key中移除：
        map.remove(3, "val3");
        map.get(3);             // val33

        map.remove(3, "val33");
        map.get(3);             // null

        // 另一个有用的方法：
        map.getOrDefault(42, "not found");  // not found

        // 将map中的实例合并也是非常容易的：
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val9

        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val9concat

        // 合并操作先看map中是否没有特定的key/value存在，
        // 如果是，则把key/value存入map，否则merging函数就会被调用，对现有的数值进行修改。


    }
}


class Person {
    String firstName;
    String lastName;

    Person() {
    }

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}