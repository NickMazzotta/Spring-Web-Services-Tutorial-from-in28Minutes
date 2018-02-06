package com.spring.basics.springin5steps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringIn5StepsApplication {

	public static void main(String[] args) {
		
		//  THIS LINE IS NO LONGER NECESSARY SINCE BinarySearchImpl IS A COMPONENT
		//BinarySearchImpl binarySearch = new BinarySearchImpl(new BubbleSortAlgorithm());
		
		// BinarySeachImpl CAN NOW BE FOUND IN THE APPLICATION CONTEXT 
		// (WHERE ALL THE BEANS ARE MAINTAINED)
		ConfigurableApplicationContext applicationContext = 
				SpringApplication.run(SpringIn5StepsApplication.class, args);
		BinarySearchImpl binarySearch = applicationContext.getBean(BinarySearchImpl.class);
		
		int result = binarySearch.binarySearch(new int[] { 12, 4, 6 }, 3);
		System.out.println(result);
	}
}
