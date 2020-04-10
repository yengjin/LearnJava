package redis;

import redis.clients.jedis.Jedis;

public class JedisTest {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("127.0.0.1", 6379);

        String result = jedis.ping();
        System.out.println(result);

        jedis.set("a", "a");

        jedis.close();
    }
}
