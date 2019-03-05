package com.sjw.redis.queue1;

public class RedisConsumer {  
  
    /**  
     * jedis操作List 
     * 先执行生产者，再执行本类，进行消费
     */    
    public static void main(String[] args){  
  
       ScheduleMQ mq = new ScheduleMQ();  
       mq.start();  
    }     
  
} 