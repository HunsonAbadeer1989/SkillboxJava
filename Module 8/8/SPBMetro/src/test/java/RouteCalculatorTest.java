import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RouteCalculatorTest extends TestCase {
    StationIndex stationIndex;
    RouteCalculator routeCalc;

    @Override
    protected void setUp() throws Exception {
        stationIndex = new StationIndex();
        routeCalc = new RouteCalculator(stationIndex);

        List<Line> lines = new ArrayList<Line>() {{
            add(new Line(1, "Десертная"));
            add(new Line(2, "Сладкая"));
            add(new Line(3, "Напитковая"));
        }};

        for (Line line : lines) {
            stationIndex.addLine(line);
        }

        List<Station> stations = new ArrayList<Station>() {{
            add(new Station("Мятная", stationIndex.getLine(1)));
            add(new Station("Чайная", stationIndex.getLine(1)));
            add(new Station("Смузная", stationIndex.getLine(1)));
            add(new Station("Кофейная", stationIndex.getLine(1)));
            add(new Station("Сахарная", stationIndex.getLine(1)));
            add(new Station("Конфетная", stationIndex.getLine(2)));
            add(new Station("Сиропная", stationIndex.getLine(2)));
            add(new Station("Помадная", stationIndex.getLine(2)));
            add(new Station("Карамельная", stationIndex.getLine(2)));
            add(new Station("Халвичная", stationIndex.getLine(2)));
            add(new Station("Козинаковая", stationIndex.getLine(2)));
            add(new Station("Сочная", stationIndex.getLine(3)));
            add(new Station("Шипучая", stationIndex.getLine(3)));
            add(new Station("Лимонадная", stationIndex.getLine(3)));
            add(new Station("Мутная", stationIndex.getLine(3)));
            add(new Station("Водная", stationIndex.getLine(3)));
            add(new Station("Пивная", stationIndex.getLine(3)));
        }};

        for (Station station : stations) {
            if (station.getLine().equals(stationIndex.getLine(1))) {
                stationIndex.getLine(1).addStation(station);
            } else if (station.getLine().equals(stationIndex.getLine(2))) {
                stationIndex.getLine(2).addStation(station);
            } else stationIndex.getLine(3).addStation(station);
        }

        for (Station station : stations) {
            stationIndex.addStation(station);
        }

        List<Station> connections1 = new ArrayList<Station>() {{
            add(stationIndex.getStation("Смузная"));
            add(stationIndex.getStation("Шипучая"));
        }};

        List<Station> connections2 = new ArrayList<Station>() {{
            add(stationIndex.getStation("Сиропная"));
            add(stationIndex.getStation("Мутная"));
        }};

        stationIndex.addConnection(connections1);
        stationIndex.addConnection(connections2);

    }

    @Test
    public void test_distance_to_same_station() {
        List<Station> actualStations = routeCalc.getShortestRoute(stationIndex.getStation("Мятная"),
                stationIndex.getStation("Смузная"));
        List<Station> expectedStations = buildRoute("Мятная -> Чайная -> Смузная");
        assertEquals(expectedStations, actualStations);
    }

    @Test
    public void test_stations_next_to_each_other_on_single_line() {
        List<Station> actualStations = routeCalc.getShortestRoute(stationIndex.getStation("Чайная"),
                stationIndex.getStation("Сахарная"));
        List<Station> expectedStations = buildRoute("Чайная -> Смузная -> Кофейная -> Сахарная");
        assertEquals(expectedStations, actualStations);
    }

    @Test
    public void test_stations_reverse_to_each_other_on_single_line() {
        List<Station> actualStations = routeCalc.getShortestRoute(stationIndex.getStation("Чайная"),
                stationIndex.getStation("Мятная"));
        List<Station> expectedStations = buildRoute("Чайная -> Мятная");
        assertEquals(expectedStations, actualStations);
    }

    @Test
    public void test_opposite_station_on_single_line() {
        List<Station> actualStations = routeCalc.getShortestRoute(stationIndex.getStation("Мятная"),
                stationIndex.getStation("Шипучая"));
        List<Station> expectedStations = buildRoute("Мятная -> Чайная -> Смузная -> Шипучая");
        assertEquals(expectedStations, actualStations);
    }

    @Test
    public void test_opposite_station_wit_one_transfer() {
        List<Station> actualStations = routeCalc.getShortestRoute(stationIndex.getStation("Мятная"),
                stationIndex.getStation("Лимонадная"));
        List<Station> expectedStations = buildRoute("Мятная -> Чайная -> Смузная -> Шипучая -> Лимонадная");
        assertEquals(expectedStations, actualStations);
    }

    @Test
    public void test_opposite_station_wit_two_transfers() {
        List<Station> actualStations = routeCalc.getShortestRoute(stationIndex.getStation("Мятная"),
                stationIndex.getStation("Карамельная"));
        List<Station> expectedStations = buildRoute("Мятная -> Чайная -> Смузная -> Шипучая -> Лимонадная -> "
                + "  Мутная -> Сиропная -> Помадная -> Карамельная");
        assertEquals(expectedStations, actualStations);
    }

    @Test
    public void test_calc_duration_with_two_transfers_22() {
        double actualTime = RouteCalculator.calculateDuration(buildRoute("Мятная -> Чайная -> Смузная -> Шипучая -> Лимонадная -> "
                + "  Мутная -> Сиропная -> Помадная -> Карамельная"));
        double expectedTime = 22.0;
        assertEquals(expectedTime, actualTime);
    }

    private List<Station> buildRoute(String str) {
        String[] stations = str.split(" -+> ");
        return Arrays.stream(stations)
                .map(s -> stationIndex.getStation(s.trim()))
                .collect(Collectors.toList());
    }
}
