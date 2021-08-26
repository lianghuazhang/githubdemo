package com.example.springbootlogliz01.lambda;

import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class LambdaTest {


    public void test1(){
        Consumer<String> con1 = str -> System.out.println(str);
        System.out.println("******* test1 ********");
        PrintStream ps = System.out;
        Consumer<String> con2 = ps::println;//接口参数与lambda实现的参数要一致。
        con2.accept("ceshi");
    }

    public void test2(){
        EmplyeeTest emp = new EmplyeeTest("",18,"男");
        Supplier<String> supplier1 = ()-> emp.getName();
        System.out.println(supplier1.get());
        System.out.println("***************** test2 ******************");
        Supplier<String> supplier2 = emp::getName;
        System.out.println(supplier2.get());
    }

    public void test3(){
        Function<EmplyeeTest,String> fun1 = e -> e.getName();
        System.out.println(fun1.apply(new EmplyeeTest("zhangsan",18,"男")));
        System.out.println("************* test3 **************");
        Function<EmplyeeTest,String> fun2 = EmplyeeTest::getName;
        System.out.println(fun2.apply(new EmplyeeTest("zhangsan",18,"男")));
    }

    /**
     * 把数组看做一个特殊的类来操作
     *
     */
    public void test4(){
        //1
        Function<Integer,String[]> func0 = new Function<Integer, String[]>() {
            @Override
            public String[] apply(Integer integer) {
                return new String[integer];
            }
        };
        System.out.println(Arrays.toString(func0.apply(4)));
        System.out.println("********** test4 ***********");
        //2
        Function<Integer,String[]> func1 = length -> new String[length];
        System.out.println(Arrays.toString(func1.apply(5)));
        System.out.println("********** test4 ***********");
        Function<Integer,String[]> func2 = String[]::new;
        System.out.println(Arrays.toString(func2.apply(6)));

    }


    class EmplyeeTest{

        public EmplyeeTest(String name,Integer age,String sex){
            this.age=age;
            this.name = name;
            this.sex = sex;
        }

        private String name;

        private Integer age;

        private String sex;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
