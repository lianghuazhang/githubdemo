package com.example.springbootlogliz01.steam;


import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * 1、Stream 关注的是对数据的运算，与cpu打交道
 *      集合关注的是数据的存储，与内存打交道
 *
 * 2、
 *  Stream 自己不会存储元素
 *  Stream 不会改变源对象，相反，他们会返回一个持有结果的新的Stream.
 *  Stream 操作是延迟操作的，意味着他们会等到需要结果的时候才执行
 *
 *  3、Stream 执行流程
 *    1）、Stream 的实例化
 *    2）、一系列的中间操作（过滤、映射、。。。）
 *    3）、终止操作
 *
 *  4、说明：
 *      1）、一个中间操作链，对数据源的数据进行处理
 *      2）、一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用。
 */
@Component
public class SteamTest {


    public void test1(){
        //获取一个顺序流---通过集合获取stream
        Stream<Employee> stream = this.getEmployeeDate().stream();


        //通过一个数组获取一个流
        int[] arr = new int[]{1,2,3,4,5,6};
        IntStream streamArr = Arrays.stream(arr);

        Employee e1 = new Employee("马化腾",1l,111.11,33);
        Employee e2 = new Employee("马云",2l,12312.11,44);
        Employee[] arrEmployee = new Employee[]{e1,e2};

        Stream<Employee> employeeStream = Arrays.stream(arrEmployee);

        //通过stream.of来获取
        Stream<Employee> employeeStream2 = Stream.of(e1,e2);
        Stream<Integer> streamInt = Stream.of(1,2,3,4,5,6);


    }

    public void test2(){
        //创建Stream方式四，创建无线流
        Stream.iterate(0,t -> t+2).limit(10).forEach(System.out::println);

        Stream.generate(Math::random).limit(10).forEach(System.out::println);

    }

    public void test3(){
        List<Employee> list = this.getEmployeeDate();

        Stream<Employee> stream = list.stream();
        System.out.println("************** filter ***************");
        //filter ,点进去看，过滤来着
        stream.filter(e -> e.salary>5000).forEach(System.out::println);
        System.out.println("************** limit ***************");
        //limit(n)--截断流，使其元素不超过给定数量
        list.stream().limit(10).forEach(System.out::println);
        System.out.println("************** skip **************");
        //skip(n)--跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空集合。
        list.stream().skip(3).forEach(System.out::println);
        System.out.println("************** distinct **************");

        list.add(new Employee("马化腾",1l,111.11,33));
        list.add(new Employee("马化腾",1l,111.11,33));
        list.add(new Employee("马化腾",1l,111.11,33));
        list.add(new Employee("马化腾",1l,111.11,33));
        list.add(new Employee("马化腾",1l,111.11,33));

        list.stream().limit(30).forEach(System.out::println);
        System.out.println("distinct");
        list.stream().distinct().forEach(System.out::println);
        System.out.println("************** max **************");
        Employee e = list.stream().max((e1,e2) -> e1.getAge()-e2.getAge()).get();
        System.out.println(e.toString());
    }

    /**
     * 映射测试
     */
    public void test4(){

        //map--接收一个函数作为参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，
        //并将其映射成一个新的元素
        List<String> list = Arrays.asList("aa","bb","cc","dd");
        list.stream().map(String::toUpperCase).forEach(System.out::println);
        List<Employee> employeeList = this.getEmployeeDate();
        System.out.println("********************************");
        employeeList.stream().map(Employee::getName).filter(e->e.length()>2).forEach(System.out::println);
        System.out.println("********************************");

        //旧方法，对比flatMap,flatMap防止嵌套stream
        Stream<Stream<Character>> stream1 = list.stream().map(SteamTest::fromStringToStream);
        stream1.forEach(a ->{
            a.forEach(System.out::println);
        });
        System.out.println("********************************");
        //flatMap，没有嵌套stream.直接拆解
        Stream<Character> stream2 = list.stream().flatMap(SteamTest::fromStringToStream);
        stream2.forEach(System.out::println);

        //sorted排序
        List<Integer> sortTest = Arrays.asList(11,55,22,33,66,44);
        System.out.println("********************************");
        sortTest.stream().sorted().forEach(System.out::println);

        //定制排序
        List<Employee> empList = this.getEmployeeDate();
        System.out.println("********************************");
        List<Employee> empList3 = empList.stream().sorted((e1,e2) ->Integer.compare(e1.getAge(),e2.getAge())).collect(Collectors.toList());
        empList3.stream().forEach(System.out::println);


    }

    /**
     * 终止操作
     */
    public void test5(){
        //allMatch(Predicate p )--检查是否匹配所有元素
        //练习：是否所有的员工的年龄都大于18
        boolean bo = this.getEmployeeDate().stream().allMatch(e -> e.getAge()>18);
        System.out.println(bo);
        System.out.println("***********************************");
        boolean bo2 = this.getEmployeeDate().stream().anyMatch(e -> e.getAge()>18);

        //noneMatch(Predicate p ) --检查是否没有匹配的元素，练习：是否存在员工姓马。
        boolean bo3 = this.getEmployeeDate().stream().noneMatch(e -> e.getName().indexOf("翁")==1);
        System.out.println(bo3);
        System.out.println("***********************************");
        // findFirst -- 返回第一个元素
        Employee e = this.getEmployeeDate().stream().findFirst().get();
        System.out.println(e.toString());
        System.out.println("***********************************");
        //findAny -- 返回流中的任意元素
        Employee returne = this.getEmployeeDate().parallelStream().findAny().get();
        System.out.println(returne.toString());
        System.out.println("***********************************");
        //count -- 返回流中元素的总个数
        Long lo = this.getEmployeeDate().stream().filter(ee-> ee.getAge()>5).count();
        System.out.println(lo);
        System.out.println("***********************************");
        //max(Comparator c) --返回流中的最大值
        Employee maxe = this.getEmployeeDate().stream().max((e1,e2) -> e1.getAge()-e2.getAge()).get();
        System.out.println(maxe.toString());
        System.out.println("***********************************");
        //min(Comparator c) --返回六中的最小值
        Employee mine = this.getEmployeeDate().stream().min((e1,e2) -> e1.getAge()-e2.getAge()).get();
        System.out.println(mine.toString());
        System.out.println("***********************************");
        //forEach(Consumer c) -- 内部迭代
        this.getEmployeeDate().stream().forEach(System.out::println);
        System.out.println("***********************************");

        //外部迭代
        this.getEmployeeDate().forEach(System.out::println);

    }

    public void test6(){
        //reduce(T identity,BinaryOperator) --可以将流中的元素反复结合其阿里，得到一个值，返回
        List<Integer> list = Arrays.asList(1,2,4,5,6,77,8);
        Integer sum = list.stream().reduce(0,Integer::sum);
        System.out.println(sum);

        System.out.println("****************************************************");
        //reduce(BinaryOperator) -- 可以将流中元素反复结合起来，得到一个值。返回类型
        Double allSalary = this.getEmployeeDate().stream().map(Employee::getSalary).reduce(Double::sum).get();
        System.out.println(allSalary);
        System.out.println("****************************************************");
        this.getEmployeeDate().stream().map(Employee::getAge).reduce((a1,a2)->a1+a2).get();

    }

    /**
     * 收集
     * collect
     */
    public void test7(){
        //collect(Collector c) --将流转换为其他形式。接收一个Collector 接口的实现，用于给stream返回
        List<Employee> emp = this.getEmployeeDate().stream().filter(e -> e.getSalary()>10000).collect(Collectors.toList());
        emp.stream().forEach(System.out::println);
        System.out.println("**********************************");
        Set<Employee> emp2 = this.getEmployeeDate().stream().filter(e -> e.getSalary()>10000).collect(Collectors.toSet());
        emp2.stream().forEach(System.out::println);
        System.out.println("**********************************");
        List<Employee> list = new ArrayList<>();
        List<Employee> emp3 = this.getEmployeeDate().stream().filter(e -> e.getSalary()>10000).collect(Collectors.toCollection(ArrayList::new));
        emp3.stream().forEach(System.out::println);
        System.out.println("**********************************");

        //计算条件流的元素个数
        Long emp4 = this.getEmployeeDate().stream().filter(e -> e.getSalary()>10000).collect(Collectors.counting());
        //计算条件流的平均值
        Double dod = this.getEmployeeDate().stream().filter(e -> e.getSalary()>1000).collect(Collectors.averagingDouble(Employee::getSalary));


    }

    /**
     * optional
     */
    public void test8(){
//        Optional.of()

    }



    public static Stream<Character> fromStringToStream(String str){
        ArrayList<Character> list = new ArrayList<Character>();
        for(Character c : str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }


    public List<Employee> getEmployeeDate(){
        List<Employee> list = new ArrayList<Employee>();
        list.add(new Employee("马化腾",1l,111.11,33));
        list.add(new Employee("马云",2l,12312.11,44));
        list.add(new Employee("刘强东",3l,11435.11,66));
        list.add(new Employee("赵凯",4l,34656.11,33));
        list.add(new Employee("张勇",5l,657567.11,22));
        list.add(new Employee("刘建明",6l,1567.11,66));
        list.add(new Employee("康熙",7l,5678.11,68));
        list.add(new Employee("翁征",8l,88654.11,99));
        list.add(new Employee("煞笔",9l,23423656.11,88));
        return list;
    }


    class Employee{

        private String name;
        private Long id;
        private Double salary;
        private Integer age;

        public Employee(String name,Long id ,Double salary,Integer age){
            this.name = name;
            this.id=id ;
            this.salary = salary;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    ", salary=" + salary +
                    ", age=" + age +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Double getSalary() {
            return salary;
        }

        public void setSalary(Double salary) {
            this.salary = salary;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
