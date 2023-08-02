package com.newfeatures.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootLambdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootLambdaApplication.class, args);
		TheTwoStages tts = new TheTwoStages();

		String stra = "aabcccbbad";
		//input parameter 1 for stage#1
		System.out.println(tts.removeAAA(stra,1));
		
		String strb = "abcccbad";
		//input parameter 1 for stage#2
		System.out.println(tts.removeAAA(strb,2));
	}

}
