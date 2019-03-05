package com.sjw.cache.service;

import redis.clients.jedis.JedisPubSub;

public class JedisPubSubListener extends JedisPubSub {
    // ȡ�ö��ĵ���Ϣ��Ĵ���  
    public void onMessage(String channel, String message) {  
        System.out.println(channel + "=" + message);  
    }  

    // ��ʼ������ʱ��Ĵ���  
    public void onSubscribe(String channel, int subscribedChannels) {  
        System.out.println(channel + "=" + subscribedChannels);  
    }  

    // ȡ������ʱ��Ĵ���  
    public void onUnsubscribe(String channel, int subscribedChannels) {  
        System.out.println(channel + "=" + subscribedChannels);  
    }  

    // ��ʼ�������ʽ�ķ�ʽ����ʱ��Ĵ���  
    public void onPSubscribe(String pattern, int subscribedChannels) {  
        System.out.println(pattern + "=" + subscribedChannels);  
    }  

    // ȡ�������ʽ�ķ�ʽ����ʱ��Ĵ���  
    public void onPUnsubscribe(String pattern, int subscribedChannels) {  
        System.out.println(pattern + "=" + subscribedChannels);  
    }  

    // ȡ�ð����ʽ�ķ�ʽ���ĵ���Ϣ��Ĵ���  
    public void onPMessage(String pattern, String channel, String message) {  
        System.out.println(pattern + "=" + channel + "=" + message);  
    }  
}  