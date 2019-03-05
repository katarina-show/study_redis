package com.sjw.cache;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sjw.cache.entity.TCountDetail;
import com.sjw.cache.services.VisitService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SiteVisitNumTest {
	
	@Resource(name="visitServiceImpl")
	private VisitService vs;
	
	private String visitId = "123";
	
	//记录访问量
	private String key = "redis:count:visit"; 
	//@Test
	public void testAdd(){
		TCountDetail tcd = new TCountDetail();
		tcd.setId(visitId);
		tcd.setIp("127.0.0.1");
		tcd.setOptime(new Date());
		tcd.setUsername("test");
		vs.addTCountDetail(tcd,key);		
	}
	
	@Test
	public void testVisitCount(){
		int count = vs.getVisitNum(key);
		System.out.println("====visit num ========="+count);
	}
}
