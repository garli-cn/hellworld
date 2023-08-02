package com.newfeatures.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootLambdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootLambdaApplication.class, args);
		TheTwoStages tts = new TheTwoStages();

		String stra = "aabcccbbad";
		System.out.println(tts.removeAAA(stra,1));
		String strb = "abcccbad";
		System.out.println(tts.removeAAA(strb,2));
	}

}
