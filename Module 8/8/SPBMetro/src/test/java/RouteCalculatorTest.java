import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    Line line1, line2, line3;
    Station station1, station2, station3, station4, station5, station6, station7, station8, station9, station10,
            station11, station12, station13, station14, station15, station16, station17;
    StationIndex stationIndex;
    RouteCalculator routeCalc;

    @Override
    protected void setUp() throws Exception {
        List<Line> lines = new ArrayList<Line>() {{
            add(line1 = new Line(1, "Десертная"));
            add(line2 = new Line(2, "Сладкая"));
            add(line3 = new Line(3, "Напитковая"));
        }};

        List<Station> stations = new ArrayList<Station>() {{
            add(station1 = new Station("Мятная", line1));
            add(station2 = new Station("Чайная", line1));
            add(station3 = new Station("Смузная", line1));
            add(station4 = new Station("Кофейная", line1));
            add(station5 = new Station("Сахарная", line1));
            add(station6 = new Station("Конфетная", line2));
            add(station7 = new Station("Сиропная", line2));
            add(station8 = new Station("Помадная", line2));
            add(station9 = new Station("Карамельная", line2));
            add(station10 = new Station("Халвичная", line2));
            add(station11 = new Station("Козинаковая", line2));
            add(station12 = new Station("Сочная", line3));
            add(station13 = new Station("Шипучая", line3));
            add(station14 = new Station("Лимонадная", line3));
            add(station15 = new Station("Мутная", line3));
            add(station16 = new Station("Водная", line3));
            add(station17 = new Station("Пивная", line3));
        }};

        List<Station> connections1 = new ArrayList<Station>() {{
            add(station3);
            add(station13);
        }};

        List<Station> connections2 = new ArrayList<Station>() {{
            add(station7);
            add(station15);
        }};

        for (Station station : stations) {
            if (station.getLine().equals(line1)) {
                line1.addStation(station);
            }
            else if(station.getLine().equals(line2)) {
                line2.addStation(station);
            }
            else line3.addStation(station);
        }

        stationIndex = new StationIndex();
        for (Line line : lines) {
            stationIndex.addLine(line);
        }
        for (Station station : stations) {
            stationIndex.addStation(station);
        }
        stationIndex.addConnection(connections1);
        stationIndex.addConnection(connections2);
        routeCalc = new RouteCalculator(stationIndex);
    }

    @Test
    public void test_distance_to_same_station() {
        List<Station> actualStations = routeCalc.getShortestRoute(station2, station2);
        List<Station> expectedStations = new ArrayList<Station>(){{
            add(station2);
        }};
        assertEquals(expectedStations, actualStations);
    }

    @Test
    public void test_stations_next_to_each_other_on_single_line() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Station> actualStations = routeCalc.getShortestRoute(station2, station5);
        List<Station> expectedStations = new ArrayList<Station>(){{
            add(station2);
            add(station3);
            add(station4);
            add(station5);
        }};
        assertEquals(expectedStations, actualStations);
    }

    @Test
    public void test_opposite_station_on_single_line(){
        List<Station> actualStations = routeCalc.getShortestRoute(station1, station13);
        List<Station> expectedStations = new ArrayList<Station>(){{
            add(station1);
            add(station2);
            add(station3);
            add(station13);
        }};
        assertEquals(expectedStations, actualStations);
    }

    @Test
    public void test_opposite_station_wit_one_transfer(){
        List<Station> actualStations = routeCalc.getShortestRoute(station11, station12);
        List<Station> expectedStations = new ArrayList<Station>(){{
            add(station11);
            add(station10);
            add(station9);
            add(station8);
            add(station7);
            add(station15);
            add(station14);
            add(station13);
            add(station12);
        }};
        assertEquals(expectedStations, actualStations);
    }

    @Test
    public void test_opposite_station_wit_two_transfers(){
        List<Station> actualStations = routeCalc.getShortestRoute(station11, station5);
        List<Station> expectedStations = new ArrayList<Station>(){{
            add(station11);
            add(station10);
            add(station9);
            add(station8);
            add(station7);
            add(station15);
            add(station14);
            add(station13);
            add(station3);
            add(station4);
            add(station5);
        }};
        assertEquals(expectedStations, actualStations);
    }

    @Override
    protected void tearDown() throws Exception {
    }
}
