package cities;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.List;

public class DisplayApp {
    private static final String KEY = "users";

    public static void getListOfUsers(Jedis jedis) {
        List<User> userList = List.of(
                new User("Vika"),
                new User("Sonya"),
                new User("Katya"),
                new User("Olesya"),
                new User("ZafarJan"),
                new User("Tonya"),
                new User("Maga-G-est"),
                new User("Raffik"),
                new User("Ashot"),
                new User("Kishlak228")
        );
        for (User user : userList) {
            jedis.rpush(KEY, user.getName());
        }
    }

    private static List<String> getUsers(Jedis jedis) {
        return jedis.lrange(KEY, 0, jedis.llen(KEY));
    }

    public static void main(String[] args) {

        try (JedisPool jedisPool = new JedisPool("localhost")) {

            Jedis jedis = jedisPool.getResource();
            getListOfUsers(jedis);

            while(true){
                List<String> users = getUsers(jedis);
                users.forEach(System.out::println);

                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }

        } catch (JedisConnectionException ex) {
            ex.printStackTrace();
        }

    }
}
