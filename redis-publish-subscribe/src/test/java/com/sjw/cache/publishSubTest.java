package com.sjw.cache;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class publishSubTest {

	@Resource
	private RedisTemplate<String,String> redisTemplate;
	
	@Test
	public void AsendMessage() {
		
		String channel = "user:topic";   
        redisTemplate.convertAndSend(channel, "hello I am A student, the class time is sunday!!"); 
	}	
}
