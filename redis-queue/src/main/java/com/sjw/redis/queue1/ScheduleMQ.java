package com.sjw.redis.queue1;

import java.util.List;

import com.sjw.redis.utils.JedisUtils;

class ScheduleMQ extends Thread {  
    JedisUtils jedis = new JedisUtils("192.168.1.6", 6379,"123456");   
  
    @Override  
    public void run() {  
        while(true) {  
            //阻塞式brpop，List中无数据时阻塞  
            //参数0表示一直阻塞下去，直到List出现数据  
            List<String> list = jedis.brpop(0, "informList");  
            for(String s : list) {  
            	//省略处理业务逻辑
            	//如果失败的话，数据会丢失，数据不丢失可参见包queue2的写法
                System.out.println(s);  
            }  
  
        }  
    }  
}  