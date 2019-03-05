package com.sjw.cache.services;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.UnsupportedEncodingException;

/**
 * Redis的发布订阅和MQ一样，消费者（订阅者）类需实现MessageListener接口
 * 为了简单，生产者直接和test合为一类
 */
public class RedisMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message, byte[] pattern) {
		try {
			System.out.println("====channel:====" + new String(message.getChannel()) + "\n====message:===="
					+ new String(message.getBody(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}