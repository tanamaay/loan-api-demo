package com.example.loan_api_demo;

import com.example.loan_api_demo.util.SSLUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoanApiDemoApplication {

	public static void main(String[] args) {
		try {
			SSLUtil.disableSSLVerification();
		} catch (Exception e) {
			System.err.println("Warning: Could not disable SSL verification!");
			e.printStackTrace();
		}

		SpringApplication.run(LoanApiDemoApplication.class, args);
	}
}
