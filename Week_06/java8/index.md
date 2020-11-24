# Java 8 新特性

## Introduction

按照秦老师课上讲解的内容，同时参考如下资料，学习Lambda、Method Reference和Stream的使用方法：

- 《Java in Action》



## Parameterization

本章节是对《Java in Action》第二节的总结。

在Java中，参数化有两类办法：

- 值参数化 Value Parameterization

- 行为参数化 Behavior Parameterization

    - 类 Class

    - 匿名类 Anonymous Class

    - **Lambda （结合泛型generic、stream代码将会更加灵活、简洁）**



从编程和简洁角度的小结如下：

![Summary](assets/BehaviorParameterization/summary.png)

**下面通过一个筛选苹果的例子，简单介绍下这几种办法。**

### 值参数化 Value Parameterization

初步解决方案，如果下图所示，函数 filterGreenApples 内部只选出绿苹果。

![Value Parameterization 1](assets/BehaviorParameterization/value-parameterization-1.png)

改进后的方案如下，将颜色作为参数：

![Value Parameterization 2](assets/BehaviorParameterization/value-parameterization-2.png)

然后使用下面的方法调用函数：

```java
List<Apple> greenApples = filterApplesByColor(inventory, "green");
List<Apple> redApples = filterApplesByColor(inventory, "red");
```

如果有更多的筛选标准，则需要写为每一个筛选标准添加新的函数。下图是按颜色或重量选择苹果的另一个函数：

![Value Parameterization 3](assets/BehaviorParameterization/value-parameterization-3.png)



### 类 Class

下图是改善后的filterApples，它的第二个参数是Interface ApplePredicate，

![Class 1](assets/BehaviorParameterization/class1.png)

下图是两个实现Interface ApplePredicate的子类：

![Class 2](assets/BehaviorParameterization/class2.png)

可以把这些标准看作filter方法的不同行为，这些和**策略设计模式**相关，定义一族算法，把它们封装起来（称为**策略**），然后在运行时选择一个算法。在这里，算法族就是ApplePredicate，不同的策略就是AppleHeavyWeightPredicate和AppleGreenColorPredicate。 

![Class 3](assets/BehaviorParameterization/class3.png)



![Class 4](assets/BehaviorParameterization/class4.png)



### 匿名类 Anonymous Class

匿名类和Java局部部类（块中定义的类）差不多，但匿名类没有名字。它允许你同时声明并实例化一个类。换句话说，它允许你随用随建。 

下面的代码展示了如何通过创建一个用匿名类实现ApplePredicate的对象，重写筛选的例子： 

![Anonymous Class](assets/BehaviorParameterization/anonymous-class.png)

### Lambda

上面的代码在Java 8里可以用Lambda表达式重写为下面的样子： 

![Lambda](assets/BehaviorParameterization/lambda.png)

### 泛型

在通往抽象的路上，我们还可以更进一步，将List类型抽象化：

![Generic](assets/BehaviorParameterization/generic.png)

现在filter方法就可以用在香蕉、桔子、Integer或者String的列表上了：

```java
List<Apple> redApples = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));
List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
```



## Lambda

可以把Lambda表᣹式理解为简洁地表示可传递的匿名函数的一种方式：它没有名称，但它有参数列表、函数主体、返回类型，可能还有一个可以抛出的异常列表：

- **匿名**——我们说ӟՏ，是因为它不像普通的方法那样有一个明确的名称：写得少而想得多！ 

- **函数**——我们说它是函数，是因为Lambda函数不像方法那样属于某个特定的类。但和方法一样，Lambda有参数列表、函数主体、返回类型，还可能有可以ઊ出的异常列表。 

- **传递**——Lambda表达式可以作为参数传递给方法或存储在变量中。 

- **简洁**——无需像匿名类那样写很多模板代码。 



Lambda 表达式基于数学中的λ演算得名，直接对应于其中的 lambda 抽象（lambda abstraction）。



### Java Lambda 表达式示例

比如，利用Lambda表达式，你可以更为简洁地自定义一个Comparator对象：

![Example](assets/Lambda/example.png)

如上图所示，Lambda表达式有三个部分： 

- **参数列表**——这里它采用了Comparator中compare方法的参数，两个Apple。 

- **箭头**——箭头->把参数列表与Lambda主体分隔开。 

- **Lambda主体**——比较两个Apple的重量。表达式就是Lambda的返回值了。 



Lambda的基本语法（只有一行时Lambda 表达式可以省略大括号）：

- (parameters) -> expression
- (parameters) -> { statements; }

#### expression

下表是更多的有效的表达式：

| Lambda                                                       | Purpose                                                      |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| (String s) -> s.length()                                     | 具有一个String类型的参数并返回一个int。Lambda⋗有return语句，因为已隐含了return。 |
| (Apple a) -> a.getWeight() > 150                             | 有一个Apple类型的参数并返回一个boolean（苹果的重量是否超过 150克） |
| (int x, int y) -> {<br/>    System.out.println("Result:");<br/>    System.out.println(x+y);<br/>} | 具有两个int类型的参数而没有返回值(void返回）。注意Lambda表达式可以包含多行语句，这里是两行。 |
| () -> 42                                                     | 没有参数，返回一个int。                                      |
| (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()) | 具有两个Apple类型的参数，返回一个int：比较两个Apple的重量。  |
| () -> 5                                                      | 不需要参数、返回值为 5                                       |
| x -> 2 * x                                                   | 接收一个参数(数字类型)、返回其2倍的值                        |
| (x, y) -> x – y                                              | 接受2个参数(数字)、并返回他们的差值                          |
| (int x, int y) -> x + y                                      | 接收2个int型整数、返回他们的和                               |
| (String s) -> System.out.print(s)                            | 接受一个 string 对象、并在控制台打印,不返回任何值(看起来像是返回 void) |
| (List<String> list) -> list.isEmpty()                        | 布尔表达式                                                   |
| () -> new Apple(10)                                          | 创建对象                                                     |
| (Apple a) -> {<br/>    System.out.println(a.getWeight());<br/>} | 消费一个对象                                                 |
| (String s) -> s.length()                                     | 从一个对象中选择/抽取                                        |
| (int a, int b) -> a * b                                      | 组合两个值                                                   |
| (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()) | 比较两个对象                                                 |



下面这个测验，用于加深对Lambda Expression的理解：

![Test](assets/Lambda/expression.png)



### 在哪里以及如何使用Lambda

Lambda仅可用于上下文是函数式接口的情况，只有在需要函数式接口的时候才可以传递Lambda。

Lambda表达式可以：

- 被赋给一个变量
- 传递给一个接受函数式接口作为参数的方法，当然这个Lambda表达式的签名要和函数式接口的抽象方法一样。



#### 函数式接口 Functional interface

**函数式接口就是只定义一个抽象方法的接口。**

下图是Java API中一些有效的函数式接口：

![Functional Interface 1](assets/Lambda/functional-interface-1.png)

下图是个测验，以加深对函数式接口的理解：

![Functional Interface 2](assets/Lambda/functional-interface-2.png)



用函数式接口可以干什么呢？Lambda表达式允许你直接以内联的形式为函数式接口的抽象方法提供实现，**并把整个表达式作为函数式接口的实例**（具体说来，是函数式接口一个具体实现的实例）。
用匿名内部类也可以完成同样的事情，只不过比较笨拙：需要提供一个实现，然后再直接内联将它实例化。

下面的代码是有效的，因为Runnable是一个只定义了一个抽象方法run的函数式接口：

![Functional Interface 3](assets/Lambda/functional-interface-3.png)



#### 函数式描述符 Function descriptor

函数式接口的抽象方法的签名基本上就是Lambda表达式的签名。我们将这种抽象方法叫作函数描述符。

![Function Descriptor](assets/Lambda/function-descriptor.png)



![Functional Internface API](assets/Lambda/functional-interface-api.png)



### 使用函数式接口实战

Putting lambdas into practice: the execute-around pattern

- 第 1步：记得行为参数化 
- 第2步：使用函数式接口来传递行为
- 第3步：执行一个行为
- 第4步：传递Lambda

![Practice](assets/FunctionalInterace/practice.png)



### 深入 Java8 函数式接口 @FunctionalInterface

函数式接口定义且只定义了一个抽象方法。函数式接口很有用，因为抽象方法的签名可以描述Lambda表达式的签名。函数式接口的抽象方法的签名称为函数描述符。所以为了应用不同的Lambda表达式，你需要一套能够描述常见函数描述符的函数式接口。

- Predicate<T> 有参数、条件判断
- Function<T, R> 有参数、有返回值
- Consumer<T> 无返回值
- Supplier<T>    无参数、有返回值



**Common functional interfaces added in Java 8 java.util.function package:**

| Functional interface | Predicate<T>      | Consumer<T>                                                  |
| -------------------- | ----------------- | ------------------------------------------------------------ |
| Predicate<T>         | T -> boolean      | IntPredicate,<br/>LongPredicate,<br/>DoublePredicate         |
| Consumer<T>          | T -> void         | IntConsumer,<br/>LongConsumer,<br/>DoubleConsumer            |
| Function<T, R>       | T -> R            | IntFunction<R>,<br/>IntToDoubleFunction,<br/>IntToLongFunction,<br/>LongFunction<R>,<br/>LongToDoubleFunction,<br/>LongToIntFunction,<br/>DoubleFunction<R>,<br/>DoubleToIntFunction,<br/>DoubleToLongFunction,<br/>ToIntFunction<T>,<br/>ToDoubleFunction<T>,<br/>ToLongFunction<T> |
| Supplier<T>          | () -> T           | BooleanSupplier,<br/>IntSupplier,<br/>LongSupplier,<br/>DoubleSupplier |
| UnaryOperator<T>     | T -> T            | IntUnaryOperator,<br/>LongUnaryOperator,<br/>DoubleUnaryOperator |
| BinaryOperator<T>    | (T, T) -> T       | IntBinaryOperator,<br/>LongBinaryOperator,<br/>DoubleBinaryOperator |
| BiPredicate<T, U>    | (T, U) -> boolean |                                                              |
| BiConsumer<T, U>     | (T, U) -> void    | ObjIntConsumer<T>,<br/>ObjLongConsumer<T>,<br/>ObjDoubleConsumer<T> |
| BiFunction<T, U, R>  | (T, U) -> R       | ToIntBiFunction<T, U>,<br/>ToLongBiFunction<T, U>,<br/>ToDoubleBiFunction<T, U> |



下面是一些示例：

| Use case                      | Example of lambda                                            | Matching functional interface                                |
| ----------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| A boolean expression          | (List<String> list) -> list.isEmpty()                        | Predicate<List<String>>                                      |
| Creating objects              | () -> new Apple(10)                                          | Supplier<Apple>                                              |
| Consuming from an object      | (Apple a) -> System.out.println(a.getWeight())               | Consumer<Apple>                                              |
| Select/extract from an object | (String s) -> s.length()                                     | Function<String, Integer>,<br/>ToIntFunction<String>         |
| Combine two values            | (int a, int b) -> a * b                                      | IntBinaryOperator                                            |
| Compare two objects           | (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()) | Comparator<Apple>,<br/>BiFunction<Apple, Apple, Integer>,<br/>ToIntBiFunction<Apple, Apple> |



## Method Reference

方法引用让你可以重复使用现有的方法定义，并像Lambda一样传递它们。在一些情况下，比起使用Lambda表达式，它们似乎更易读，感觉也更自然。方法引用可以被看作仅仅调用特定方法的Lambda的一种快捷写法。



它的基本思想是，如果一个Lambda代表的只是“直接调用这个方法”，那最好还是用名称来调用它，而不是去描述如何调用它。事实上，方法引用就是让你根据已有的方法实现来创建Lambda表达式。但是，显式地指明方法的名称，你的代码的可读性会更好。



它是如何工作的呢？当你需要使用方法引用时，目标引用放在分隔符::前，方法的名称放在后面。

例如，Apple::getWeight就是引用了Apple类中定义的方法getWeight。

请记住，不需要括号，因为你没有实际调用这个方法。

方法引用就是Lambda表达式(Apple a) -> a.getWeight()的快捷写法。



下表给出了Java 8中方法引用的其他一些例子。 

| Lambda                                   | 等效的方法引用                    |
| ---------------------------------------- | --------------------------------- |
| (Apple a) -> a.getWeight()               | Apple::getWeight                  |
| () -> Thread.currentThread().dumpStack() | Thread.currentThread()::dumpStack |
| (str, i) -> str.substring(i)             | String::substring                 |
| (String s) -> System.out.println(s)      | System.out::println               |

### 如何构建方法引用

方法引用主要有三类：

1. 指向**静态方法**的方法引用（例如Integer的parseInt方法，写作Integer::parseInt）。
2. 指向**任意类型实例方法**的方法引用（ 例 如 String 的 length 方 法 ，写作String::length）。
3. 指向**现有对象的实例方法**的方法引用（假设你有一个ࡌ部变量expensiveTransaction用于存放Transaction类型的对象，它支持实例方法getValue，那么你就可以写expensiveTransaction::getValue）。 
4. 构造函数
5. 数组构造函数
6. 父类调用（super-call）

![Methods](assets/MethodReference/methods.png)

比如，下面的Lambda：

```java
List<String> str = Arrays.asList("a", "b", "A", "B");
str.sort((s1,s2)->s1.compareToIgnoreCase(s2));
```

可以更改为如下Method Reference:

```java
List<String> str = Arrays.asList("a", "b", "A", "B");
str.sort(String::compareToIgnoreCase);
```

再通过下面的测验加深理解：

![Quiz](assets/MethodReference/quiz.png)



#### 构造函数引用

没有参数的构造函数：

例如，假设有一个构造函数没有参数。它适合Supplier的签名() -> Apple。你可以这样做：

![No Parameter Constructore Method](assets/MethodReference/constructor-reference-1.png)



有参数的构造函数：

如果构造函数的签名是Apple(Integer weight)，那么它就适合Function接口的签名，于是可以这样写： 

![Paramter Constructor Method](assets/MethodReference/constructor-reference-2.png)



在下面的代码中，一个由Integer构成的List中的每个元素都通过我们前面定义的类似的map方法传递给了Apple的构造函数，得到了一个具有不同重量苹果的List：

![Pass Constructor to Map](assets/MethodReference/constructor-reference-3.png)



如果你有一个具有两个参数的构造函数Apple(String color, Integer weight)，那么它就适合BiFunction接口的签名，于是你可以这样写： 

![Pass Constructor to Map](assets/MethodReference/constructor-reference-4.png)



不将构造函数实例化却能够引用它，这个功能有一些有趣的应用。例如，可以使用Map来将构造函数映射到字符串值。可以创建一个giveMeFruit方法，给它一个String和一个Integer，它就可以创建出不同重量的各种水果：

![Pass Constructor to Map](assets/MethodReference/constructor-reference-5.png)



### Lambda和方法引用实战

- 第1步：传递代码

    ```java
    public class AppleComparator implements Comparator<Apple> {
        public int compare(Apple a1, Apple a2) {
            return a1.getWeight().compareTo(a2.getWeight());
        }
    } 
     
    inventory.sort(new AppleComparator());
    ```



- 第2步：使用匿名类

    ```java
    inventory.sort(new Comparator<Apple>() {
        public int compare(Apple a1, Apple a2) {
            return a1.getWeight().compareTo(a2.getWeight());
        }
    });
    ```

    

- 第3步：使用Lambda表达式

    下面三种方法都正确，每一种都比前一种更为紧凑、简洁：

    ```java
    inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
    ```

    ```java
    inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
    ```

    ```java
    import static java.util.Comparator.comparing;
    inventory.sort(comparing((a) -> a.getWeight()));
    ```

    

- 第4步：使用方法引用

    ```java
    import static java.util.Comparator.comparing;
    inventory.sort(comparing(Apple::getWeight));
    ```



## Stream

### 什么是流

Stream（流）是一个来自数据源的元素队列，并支持聚合操作

- 元素：特定类型的对象，形成一个队列。 Java 中的 Stream 并不会存储元素，而是按需计算。

- 数据源：流的来源。，可以是集合、数组、I/O channel、 产生器 generator 等。

- 聚合操作：类似 SQL 语句一样的操作， 比如 filter、map、reduce、find、match、sorted 等。



和以前的 Collection 操作不同， Stream 操作还有两个基础的特征：

- Pipelining：中间操作都会返回流对象本身， 这样多个操作可以串联成一个管道， 如同流式风
    格(fluent style)。 这样做可以对操作进行优化， 比如延迟执行(laziness)和短路((short-circuiting)。

- 内部迭代：以前对集合遍历都是通过 Iterator 或者 For-Each 的方式, 显式的在集合外部进行迭代， 这叫做外部迭代。 Stream 提供了内部迭代的方式， 通过访问者模式(Visitor)实现。



### Stream 操作

#### 中间操作 Intermediate Operations

诸如filter或sorted等中间操作会返回另一个流。这让多个操作可以连接起来形成一个查询。重要的是，除非流水线上触发一个终端操作，否则中间操作不会执行任何处理——它们很懒。这是因为中间操作一般都可以合并起来，在终端操作时一次性全部处理。

1. 选择与过滤

    - filter(Predicate p) 接收 Lambda ， 从流中排除某些元素。
    - distinct() 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素。
    - limit(long maxSize) 截断流，使其元素不超过给定数量。
    - skip(long n) 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。

2. 映射

    - map(Function f) 接收 Lambda ，将元素转换成其他形式或提取信息；接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。

    - mapToDouble(ToDoubleFunction f) 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 DoubleStream。

    - mapToInt(ToIntFunction f) 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 IntStream。

    - mapToLong(ToLongFunction f) 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 LongStream。

    - flatMap(Function f) 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。

3. 排序

    - sorted() 产生一个新流，其中按自然顺序排序。

    - sorted(Comparator comp) 产生一个新流，其中按比较器顺序排序。



**Intermediate Operations**

| Operation | Type         | Return type | Argument of the operation | Function descriptor |
| --------- | ------------ | ----------- | ------------------------- | ------------------- |
| filter    | Intermediate | Stream<T>   | Predicate<T>              | T -> boolean        |
| map       | Intermediate | Stream<R>   | Function<T, R>            | T -> R              |
| limit     | Intermediate | Stream<T>   |                           |                     |
| sorted    | Intermediate | Stream<T>   | Comparator<T>             | (T, T) -> int       |
| distinct  | Intermediate | Stream<T>   |                           |                     |



#### 终止操作 Terminal Operations

终端操作会从流的流水线生成结果。其结果是任何不是流的值，比如List、Integer，甚至void。
例如，在下面的流水线中，forEach是一个返回void的终端操作，它会对源中的每道菜应用一个Lambda。把System.out.println传递给forEach，并要求它打印出由menu生成的流中的每一个Dish： 

```java
menu.stream().forEach(System.out::println);
```

1. 查找与匹配

    - allMatch 检查是否匹配所有元素。

    - anyMatch 检查是否至少匹配一个元素。

    - noneMatch 检查是否没有匹配的元素。

    - findFirst 返回第一个元素。

    - findAny 返回当前流中的任意元素。

    - count 返回流中元素的总个数。

    - max 返回流中最大值。

    - min 返回流中最小值。

2. 归约 reduce， 需要初始值（类比Map-Reduce）。

3. 收集 collect

    - toList List<T> 把流中元素收集到 List。

    - toSet Set<T> 把流中元素收集到 Set。

    - toCollection Collection<T> 把流中元素收集到创建的集合。

    - count 计算流中元素的个数。

    - summaryStatistics 统计最大最小平均值。

4. 迭代 forEach。



**Terminal operations**

| Operation | Type     | Return type | Purpose                                                      |
| --------- | -------- | ----------- | ------------------------------------------------------------ |
| forEach   | Terminal | void        | Consumes each element from a stream and applies a lambda to each of them. |
| count     | Terminal | long        | Returns the number of elements in a stream.                  |
| collect   | Terminal | (generic)   | Reduces the stream to create a collection such as a List, a Map, or even an Integer. See chapter 6 for more detail. |



**The main static factory methods of the Collectors class**

| Factory method    | Returned type                                  | Used to                                                      | Example use                                                  |
| ----------------- | ---------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| toList            | List<T>                                        | Gather all the stream’s items in a List.                     | List<Dish> dishes = menuStream.collect(toList());            |
| toSet             | Set<T>                                         | Gather all the stream’s items in a Set, eliminating duplicates. | Set<Dish> dishes = menuStream.collect(toSet());              |
| toCollection      | Collection<T>                                  | Gather all the stream’s items in the collection created by the provided supplier. | Collection<Dish> dishes = menuStream.collect(toCollection(), ArrayList::new); |
| counting          | Long                                           | Count the number of items in the stream.                     | longhowManyDishes = menuStream.collect(counting());          |
| summingInt        | Integer                                        | Sum the values of an Integer property of the items in the stream. | int totalCalories = menuStream.collect(summingInt(Dish::getCalories)); |
| averagingInt      | Double                                         | Calculate the average value of an Integer property of the items in the stream. | double avgCalories = menuStream.collect(averagingInt(Dish::getCalories)); |
| summarizingInt    | IntSummaryStatistics                           | Collect statistics regarding an Integer property of the items in the stream, such as the maximum, minimum, total, and average. | IntSummaryStatistics menuStatistics = menuStream.collect(summarizingInt(Dish::getCalories)); |
| joining           | String                                         | Concatenate the strings resulting from the invocation of the toString method on each item of the stream. | String shortMenu = menuStream.map(Dish::getName).collect(joining(", ")); |
| maxBy             | Optional<T>                                    | An Optional wrapping the maximal element in this stream according to the given comparator or Optional.empty() if the stream is empty. | Optional<Dish> fattest = menuStream.collect(maxBy(comparingInt(Dish::getCalories))); |
| minBy             | Optional<T>                                    | An Optional wrapping the minimal element in this stream according to the given comparator or Optional.empty() if the stream is empty. | Optional<Dish> lightest = menuStream.collect(minBy(comparingInt(Dish::getCalories))); |
| reducing          | The type produced by the reduction operation   | Reduce the stream to a single value starting from an initial value used as accumulator and iteratively combining it with each item of the stream using a BinaryOperator. | int totalCalories = menuStream.collect(reducing(0, Dish::getCalories, Integer::sum)); |
| collectingAndThen | The type returned by the transforming function | Wrap another collector and apply a transformation function to its result. | int howManyDishes = menuStream.collect(collectingAndThen(toList(), List::size)); |
| groupingBy        | Map<K, List<T>>                                | Group the items in the stream based on the value of one of their properties and use those values as keys in the resulting Map. | Map<Dish.Type,List<Dish>> dishesByType = menuStream.collect(groupingBy(Dish::getType)); |
| partitioningBy    | Map<Boolean, List<T>>                          | Partition the items in the stream based on the result of the application of a predicate to each of them. | Map<Boolean,List<Dish>> vegetarianDishes = menuStream.collect(partitioningBy(Dish::isVegetarian)); |



#### **Intermediate and Terminal Operations Summary**

| Operation | Type                                  | Return type | Type/functional interface used | Function descriptor |
| --------- | ------------------------------------- | ----------- | ------------------------------ | ------------------- |
| filter    | Intermediate                          | Stream<T>   | Predicate<T>                   | T -> boolean        |
| distinct  | Intermediate<br/>(stateful-unbounded) | Stream<T>   |                                |                     |
| takeWhile | Intermediate                          | Stream<T>   | Predicate<T>                   | T -> boolean        |
| dropWhile | Intermediate                          | Stream<T>   | Predicate<T>                   | T -> boolean        |
| skip      | Intermediate<br/>(stateful-bounded)   | Stream<T>   | long                           |                     |
| limit     | Intermediate<br/>(stateful-bounded)   | Stream<T>   | long                           |                     |
| map       | Intermediate                          | Stream<R>   | Function<T, R>                 | T -> R              |
| flatMap   | Intermediate                          | Stream<R>   | Function<T, Stream<R>>         | T -> Stream<R>      |
| sorted    | Intermediate<br/>(stateful-unbounded) | Stream<T>   | Comparator<T>                  | (T, T) -> int       |
| anyMatch  | Terminal                              | boolean     | Predicate<T>                   | T -> boolean        |
| noneMatch | Terminal                              | boolean     | Predicate<T>                   | T -> boolean        |
| allMatch  | Terminal                              | boolean     | Predicate<T>                   | T -> boolean        |
| findAny   | Terminal                              | Optional<T> |                                |                     |
| findFirst | Terminal                              | Optional<T> |                                |                     |
| forEach   | Terminal                              | void        | Consumer<T>                    | T -> void           |
| collect   | Terminal                              | R           | Collector<T, A, R>             |                     |
| reduce    | Terminal<br/>(stateful-bounded)       | Optional<T> | BinaryOperator<T>              | (T, T) -> T         |
| count     | Terminal                              | long        |                                |                     |

