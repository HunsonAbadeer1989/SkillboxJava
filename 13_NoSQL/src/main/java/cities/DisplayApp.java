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
                List<String> users = jedis.lrange(KEY, 0, jedis.llen(KEY));

                for (String user : users) {
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
