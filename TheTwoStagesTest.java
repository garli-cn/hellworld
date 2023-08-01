package com.newfeatures.demo;


import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TheTwoStagesTest {

	@Autowired
	private TheTwoStages theService;

	String[] cases= {
			"aabcccbbad","d",
			"a","a",
			"aa","aa",
			"aab","aab",
			"aaab","b",
			"aaabb","bb",
			"aaabbb","",
			"aaabbbd","d",
			"aabbbad","d",
			"abcccb","abb",
			"abcccba","abba",
			"abcccbb","a",
			"abccccb","abb",
			"abcccccba","abba",
			"abcccbaa","abbaa",
			"abcccddda","aba",
			"abbbcddd","ac"
	};
	@Test
	public void removeAAA() {
		
		List<String> list = java.util.Arrays.asList(cases);

        Stream.iterate(0, i -> i + 2).limit(list.size()/2).forEach(i -> {
        	System.out.println("<"+i/2+">: "+String.format("%" + 20 + "s", list.get(i))+"-->"+list.get(i+1));
    		Assert.assertEquals( theService.removeAAA(list.get(i)), list.get(i+1) );

        });	
	}
	

	String[] cases2= {
			"abcccbad","d",
			"ccc","b",
			"aaa","",
			"bbb","a",
			"aaab","b",
			"a","a",
			"b","b",
			"aaabbbd","d",
			"aabbbad","d",
			"abcccb","aa",
			"ab","ab",
			"abcccbb","aa",
			"abccccb","aa",
			"abcccccba","",
			"abcccbaa","",
			"abcccddda","abbca",
			"abbbcddd","aacc"
	};
	
	@Test
	public void removeBBB() {
		
		List<String> list = java.util.Arrays.asList(cases2);

        Stream.iterate(0, i -> i + 2).limit(list.size()/2).forEach(i -> {
        	System.out.println("<"+i/2+">: "+String.format("%" + 20 + "s", list.get(i))+"-->"+list.get(i+1));
    		Assert.assertEquals( theService.removeBBB(list.get(i)), list.get(i+1) );

        });	
	}
}
