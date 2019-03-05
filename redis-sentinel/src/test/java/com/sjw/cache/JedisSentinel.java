package com.sjw.cache;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
/**
 * 哨兵原生实现
 */
public class JedisSentinel {

	/**
	 * 只关注哨兵的IP地址和端口，而不是redis的
	 */
	@Test
	public void testJedis() throws InterruptedException {
		Set<String> sentinels = new HashSet<String>();
		String hostAndPort1 = "192.168.1.6:26379";
		String hostAndPort2 = "192.168.1.6:26380";
		String hostAndPort3 = "192.168.1.6:26381";
		sentinels.add(hostAndPort1);
		sentinels.add(hostAndPort2);
		sentinels.add(hostAndPort3);

		String clusterName = "mymaster";
		String password = "123456";

		//JedisSentinelPool构建JedosPool
		JedisSentinelPool redisSentinelJedisPool = new JedisSentinelPool(clusterName, sentinels, password);

		Jedis jedis = null;
		try {
			jedis = redisSentinelJedisPool.getResource();

			jedis.set("name", "sjw");
			System.out.println(jedis.get("name"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			redisSentinelJedisPool.returnBrokenResource(jedis);
		}

		redisSentinelJedisPool.close();
	}

}
