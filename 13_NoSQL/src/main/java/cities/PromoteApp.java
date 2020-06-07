package cities;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;
import java.util.List;

public class PromoteApp {

    private static Jedis jedis;
    private static final String KEY = "users";

    public static void createListOfUsers() {
        jedis.del(KEY);
        List<User> userList = List.of(
                new User("1 Vika"),
                new User("2 Sonya"),
                new User("3 Katya"),
                new User("4 Olesya"),
                new User("5 ZafarJan"),
                new User("6 Tonya"),
                new User("7 Maga-G-est"),
                new User("8 Raffik"),
                new User("9 Ashot"),
                new User("10 Kishlak228")
        );
        for (User user : userList) {
            jedis.rpush(KEY, user.getName());
        }
    }

    public static void main(String[] args) {
        jedis = new Jedis("localhost");
        createListOfUsers();
        int i = 0;

        while (true) {
            String user = jedis.lpop(KEY);
            System.out.println(user);
            jedis.rpushx(KEY, user);

            if(++i % 10 == 0) {
                int randomUserNumber = (int) (Math.random() * jedis.llen(KEY));
                String randomUser = jedis.lindex(KEY, randomUserNumber);
                jedis.lrem(KEY, 1, randomUser);
                jedis.linsert(KEY, ListPosition.AFTER, user, randomUser);
                System.out.printf("User %s was promoted. \n", randomUser.toUpperCase());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }
}
