package com.sjw.cache;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sjw.cache.service.JedisSentinelPoolUtils;
import com.sjw.cache.service.SentinelTemplateUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 * 哨兵spring实现
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SentinelTest {
	
	@Resource
	private SentinelTemplateUtil service;
	
	@Test
	public void test() throws InterruptedException{
		 service.set("name12", "sjwssss");
	     String str = service.get("name12");   
		 
    	 System.out.println("====="	+str);
	}	
	
	@Test
	public void testJedis() throws InterruptedException{
		Jedis jedis = JedisSentinelPoolUtils.getJedis();
		jedis.set("age1", "5122!!");
		System.out.println("============="+jedis.get("age1"));
	}
}
