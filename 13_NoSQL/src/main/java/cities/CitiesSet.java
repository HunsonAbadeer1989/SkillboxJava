package cities;

import redis.clients.jedis.Jedis;

import java.util.Map;

public class CitiesSet {

    public static void main(String[] args) {

        final String KEY = "cities";
        Jedis jedis = new Jedis("localhost");

        Map<String, Double> cities = Map.of(
                "Tokyo", 10000.0,
                "London", 15000.0,
                "Odessa", 3000.0,
                "Ottawa", 20000.0,
                "Saint-Petersburg", 3000.0,
                "New Deli", 11000.0,
                "Barcelona", 24000.0,
                "Montenegro", 30000.0,
                "Sidney", 40000.0,
                "Taganrog", 1000.0
        );

        jedis.del(KEY);
        cities.entrySet().forEach(city -> {
            jedis.zadd(KEY, city.getValue(), city.getKey());
        });

        System.out.println("List of cheapest tickets to cities: ");
        jedis.zrangeWithScores(KEY, 0, 3).forEach(System.out::println);

        System.out.println("List of expensive tickets to cities: ");
        jedis.zrevrangeWithScores(KEY, 0, 3).forEach(System.out::println);

    }

}