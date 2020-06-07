package cities;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

import java.time.LocalDateTime;
import java.util.List;

public class PromoteApp {

    private static Jedis jedis;
    private static final String KEY = "users";

    public static void getListOfUsers() {
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

    private static List<String> getUsers() {
        return jedis.lrange(KEY, 0, jedis.llen(KEY));
    }

    public static void main(String[] args) {
        jedis = new Jedis("localhost");
        getListOfUsers();

        for (int i = 0; i > -1; ) {
            LocalDateTime start = LocalDateTime.now();
            List<String> users = getUsers();

            for (String user : users) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                if(LocalDateTime.now().minusSeconds(5L).isAfter(start)){ // ones at ten seconds promote user
                    int randomUserNumber = (int) (Math.random() * jedis.llen(KEY));
                    String randomUser = jedis.lindex(KEY, randomUserNumber);
                    jedis.lrem(KEY, 1, randomUser);
                    jedis.linsert(KEY, ListPosition.AFTER, user, randomUser);
                    System.out.println(randomUser.toUpperCase());
                }
                System.out.println(user);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
            i++;
        }
    }
}
