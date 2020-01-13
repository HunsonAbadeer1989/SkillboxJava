import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;
import java.time.*;
import java.util.*;

public class Loader {
    public static void main(String[] args) {

        Airport airport = Airport.getInstance();
        List<Terminal> terminals = airport.getTerminals();
        List<Flight> flights = new ArrayList<>();

        terminals.forEach(t -> {
            t.getFlights();
            flights.addAll(t.getFlights());
        });

        LocalDateTime timePlus2hours = LocalDateTime.now().plusHours(2);
        flights.stream().
                filter(f -> f.getType() == Flight.Type.DEPARTURE).
                filter(f -> f.getDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime().isAfter(timePlus2hours)).
                forEach(f -> {
                             System.out.printf("Aircraft %s, will %s in %s \n", f.getAircraft(), f.getType(), f.getDate());
                });
    }
}
