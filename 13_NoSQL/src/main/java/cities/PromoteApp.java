package cities;

import redis.clients.jedis.Jedis;
import java.util.List;

public class PromoteApp {

    private static Jedis jedis;
    private static final String KEY = "users";

    public static void getListOfUsers(){
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
        for(User user : userList){
            jedis.rpush(KEY, user.getName());
        }
    }

    private static List<String> getUsers() {
        return jedis.lrange(KEY, 0, jedis.llen(KEY));
    }

    public static String getRandomUser(){
        int randomUserNumber = (int) (Math.random() * jedis.llen(KEY));
        return jedis.lindex(KEY, randomUserNumber);
    }

    public static void main(String[] args) {
        jedis = new Jedis("localhost");
        getListOfUsers();

        for(int i = 0; i > -1 ; ){
            List<String> users = getUsers();
            users.forEach(System.out::println);
            if(i % 10 == 0){
                String randomUser = getRandomUser();
                users.remove(randomUser);
                System.out.println("HEY LOOK AT ME MY, NAME IS: " +  randomUser.toUpperCase());
            }
            i++;
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
}
