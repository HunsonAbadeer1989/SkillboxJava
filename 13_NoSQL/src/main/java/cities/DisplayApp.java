package cities;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.List;

public class DisplayApp {

    private static final String KEY = "users";

    public static void main(String[] args) {

        while (true) {
            try (JedisPool jedisPool = new JedisPool("localhost")) {

                Jedis jedis = jedisPool.getResource();
                for (String user : jedis.lrange(KEY, 0, jedis.llen(KEY))) {
                    System.out.println(user);
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } catch (JedisConnectionException ex) {
                ex.printStackTrace();
            }
        }
    }
}
