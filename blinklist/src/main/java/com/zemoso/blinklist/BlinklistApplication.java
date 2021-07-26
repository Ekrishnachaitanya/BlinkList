package com.zemoso.blinklist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BlinklistApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlinklistApplication.class, args);
	}

}
