package com.sjw.cache.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

public class JedisSentinelPoolUtils {

	private static JedisSentinelPool pool = null;

	public static Properties getJedisProperties() {

		Properties config = new Properties();

		InputStream is = null;

		try {

			is = JedisSentinelPoolUtils.class.getClassLoader().getResourceAsStream("redis.properties");

			config.load(is);

		} catch (IOException e) {
 

		} finally {

			if (is != null) {

				try {

					is.close();

				} catch (IOException e) {
 

				}

			}

		}

		return config;

	}

	/**
	 * 
	 * 创建连接池
	 * 
	 * 
	 * 
	 */

	private static void createJedisPool() {

		// 建立连接池配置参数

		JedisPoolConfig config = new JedisPoolConfig();

		Properties prop = getJedisProperties();

		// 设置最大连接数

		config.setMaxTotal(Integer.valueOf(prop.getProperty("im.hs.server.redis.maxTotal")));

		// 设置最大阻塞时间，记住是毫秒数milliseconds

		config.setMaxWaitMillis(Integer.valueOf(prop.getProperty("im.hs.server.redis.maxWaitTime")));

		// 设置空间连接

		config.setMaxIdle(Integer.valueOf(prop.getProperty("im.hs.server.redis.maxIdle")));

		// jedis实例是否可用

		boolean borrow = prop.getProperty("im.hs.server.redis.testOnBorrow") == "false" ? false : true;

		config.setTestOnBorrow(borrow);
       
		// 创建连接池

		// pool = new JedisPool(config, prop.getProperty("ADDR"),
		// StringUtil.nullToInteger(prop.getProperty("PORT")),
		// StringUtil.nullToInteger(prop.getProperty("TIMEOUT")));//
		// 线程数量限制，IP地址，端口，超时时间

		// 获取redis密码

		String password = prop.getProperty("im.hs.server.redis.sentinel.password").toString();

		String masterName = "mymaster";

		Set<String> sentinels = new HashSet<String>();

		sentinels.add("192.168.1.6:26379");

		sentinels.add("192.168.1.6:26380");

		sentinels.add("192.168.1.6:26381");

		pool = new JedisSentinelPool(masterName, sentinels, config,password);

	}

	/**
	 * 
	 * 在多线程环境同步初始化
	 * 
	 */

	private static synchronized void poolInit() {

		if (pool == null)

			createJedisPool();

	}

	/**
	 * 
	 * 获取一个jedis 对象
	 * 
	 * 
	 * 
	 * @return
	 * 
	 */

	public static Jedis getJedis() {

		if (pool == null)

			poolInit();

		return pool.getResource();

	}

	/**
	 * 
	 * 释放一个连接
	 * 
	 * 
	 * 
	 * @param jedis
	 * 
	 */

	public static void returnRes(Jedis jedis) {

		pool.returnResource(jedis);

	}

	/**
	 * 
	 * 销毁一个连接
	 * 
	 * 
	 * 
	 * @param jedis
	 * 
	 */

	public static void returnBrokenRes(Jedis jedis) {

		pool.returnBrokenResource(jedis);

	}

	

}
