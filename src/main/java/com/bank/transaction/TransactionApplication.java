package com.bank.transaction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TransactionApplication extends SpringBootServletInitializer implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(TransactionApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TransactionApplication.class);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("hi from application runner");

		List<Integer> list = Arrays.asList(4, 6, 7, 8, 90, 48);
		System.out.println(list);
		List<Integer> square = list.stream().map(x -> x * x).collect(Collectors.toList());
		System.out.println(square);

		list.stream().sorted().forEach(y -> {
			System.out.println(y);
		});

		List<String> names = Arrays.asList("john", "britto", "mjb");
		System.out.println(names);
		List<String> names1 = names.stream().filter(s -> s.startsWith("b")).collect(Collectors.toList());
		System.out.println(names1);

		list.stream().distinct().forEach(System.out::println);

		list.forEach(a -> {
			System.out.println(a);
		});

		MyInterface.method1();
		MyInterface.method2();
		MyInterface.method3();

		MyClass myClass = new MyClass();
		myClass.default1();
		myClass.default2();

		myClass.method1();

		myClass.lamdaMethod(10);
		myClass.lamdaMethod2(x -> {
			System.out.println(x*x);
		}, 20);
		
		String s1 = "hi";
		String s2 = new String("hello");
		s1.concat(" John");
		System.out.println(s1);
		System.out.println(s1=s2);
		System.out.println(s1);
		
		System.out.println( 'c'=='c' );
		System.out.println( 'c'=='C' );
		System.out.println( 'c'=='a' );

	}

	class MyClass implements MyInterface {

		private void method1() {
			System.out.println("method1 overridden");
		}

		@Override
		public void default1() {
			System.out.println("default1 overridden");
		}

		void lamdaMethod(int abc) {
			MyInterface2 myIn = x -> System.out.println(x);
			myIn.abstractFuntion(abc);
			
			myIn = x -> {
				System.out.println(x*x);
				
			};
			myIn.abstractFuntion(abc);
		}
		
		void lamdaMethod2(MyInterface2 myIn, int abc) {
			myIn.abstractFuntion(abc);
		}

	}

}

interface MyInterface {
	static void method1() {
		System.out.println("method1");
	}

	static void method2() {
		System.out.println("method2");
	}

	static void method3() {
		System.out.println("method3");
	}

	default void default1() {
		System.out.println("default1");
	}

	default void default2() {
		System.out.println("default2");
	}

}

@FunctionalInterface
interface MyInterface2 {
	abstract void abstractFuntion(int x);
}
