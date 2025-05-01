package com.substirng.coreboot;

import com.substirng.coreboot.beans.Samosa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//extra
@SpringBootApplication
/*
	@Configuration-
		- it is used to mark a class as a source of bean definitions for the application context.
		- it is used to define beans in the application context.
	@EnableAutoConfiguration-
		- it is used to enable auto-configuration in the application.
		- it is used to automatically configure the application based on the classpath.
	@ComponentScan-
		- it is used to scan the packages for components.
		- it is used to automatically register beans in the application context.

 */
public class CoreBootApplication
{

	public static void main(String[] args)
	{

		System.out.println("starting core boot application");
		//	it will boot ups your spring boot application
		ConfigurableApplicationContext context = SpringApplication.run(CoreBootApplication.class, args);


		/// we are getting the bean from the application context

		Samosa bean1 = context.getBean("mySamosa", Samosa.class);

		System.out.println(bean1);
		bean1.eat();

		Samosa bean2=context.getBean("mySamosa", Samosa.class);

		System.out.println(bean2);
		bean2.eat();
		/*

			it will boot ups your spring boot application

			In-short, it will do the following:
			1. scans the classpath for components
			2. auto-configurations the application based on the classpath
			3. start your application


		 */

		/*

		in detail:
		run method:
		1. create a new SpringApplication instance
		2. boostrap the application context
		3. start the embedded server
		4. perform classpath scanning
		5. intializes all beans and dependencies injection
		6. autoconfigurations
		7. start the application


		 */

	}

}
