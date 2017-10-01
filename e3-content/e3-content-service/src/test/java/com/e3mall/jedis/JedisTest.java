package com.e3mall.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.sound.midi.Soundbank;

public class JedisTest {
    @Test
    public void testJedis() {
        //创建一个jedis对象，参数:host、port
        Jedis jedis = new Jedis("47.95.118.3",6379);
        //所有redis的命令都对应一个jedis方法
        jedis.set("test123","myfirst jedis test");
        System.out.println(jedis.get("test123"));
        //关闭连接
        jedis.close();
    }

    /**
     * 使用连接池提高性能
     */
    @Test
    public void testJedisPool() throws Exception {
        //创建连接池对象
        JedisPool jedisPool = new JedisPool("47.95.118.3",6379);
        //从连接池获得一个连接，就是一个jedis对象
        Jedis jedis = jedisPool.getResource();
        //使用jedis操作redis
        System.out.println(jedis.get("test123"));
        //关闭连接，每次使用完后关闭连接。
        jedis.close();
        //关闭连接池
        jedisPool.close();
    }
}
