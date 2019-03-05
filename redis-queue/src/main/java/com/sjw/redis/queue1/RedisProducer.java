package com.sjw.redis.queue1;

import com.sjw.redis.utils.JedisUtils;

public class RedisProducer {  
  
    /**  
     * jedis操作List 
     * 先操作这个类
     */    
    public static void main(String[] args){  
  
        JedisUtils jedis = new JedisUtils("192.168.1.6", 6379,"123456");   
        for(int i = 0;i<10;i++) {  
            jedis.lpush("informList","orderIdadb_" + i);    
        }  
    }  
  
} 