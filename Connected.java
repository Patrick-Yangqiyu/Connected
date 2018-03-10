import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Connected {
    public static void main(String[] args) {
        if (args.length == 3) {
            String fileName = args[0];
            String startCity = args[1];
            String endCity = args[2];
            CityTest cityTester = new CityTest();

            //read file into stream
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

                stream.forEach(
                        line -> {
                            String[] temp = line.split(",");
                            if (temp.length == 2) {
                                cityTester.addLink(temp[0].trim(), temp[1].trim());
                            }
                        }
                );

            } catch (IOException e) {
                e.printStackTrace();
            }

            //cityTester.display();

            String result = cityTester.isConnected(startCity, endCity) ? "yes" : "no";
            System.out.println(result);
        } else
            System.out.println("ERROR Please check");
    }
}

class City {
    //City Class
    String name;
    //Connected with the start city or not
    boolean isConnected;

    public City(String name) {
        this.name = name;
        isConnected = false;
    }
}

class CityTest {

    private Map<String, List<City>> map;

    public CityTest() {
        map = new TreeMap<>();
    }


    private void addCoonCities(String cityA, String cityB) {
        if (map.containsKey(cityA)) {
            List<City> connCities = map.get(cityA);
            connCities.add(new City(cityB));
            map.put(cityA, connCities);
        } else {
            List<City> connCities = new ArrayList<>();
            connCities.add(new City(cityB));
            map.put(cityA, connCities);
        }
    }


    public void addLink(String startCity, String endCity) {
        addCoonCities(startCity, endCity);
        addCoonCities(endCity, startCity);
    }

    /**
     * Print the city and its connected cities
     */
    public void display() {
        for (String city : map.keySet()) {
            // the city
            System.out.print(city + " CONNECT WITH:");
            // connected cities
            for (City connCity : map.get(city)) {
                System.out.print(connCity.name + ",");
            }
            System.out.println();
        }
    }

    /**
     * Reset cities status
     */
    private void resetAllCities() {
        for (String city : map.keySet()) {
            for (City connCity : map.get(city)) {
                connCity.isConnected = false;
            }
        }
    }


    /**
     * Check whether two specified cities are connected
     */
    public boolean isConnected(String startCity, String endCity) {

        //If just run once, no need to reset.
        //resetAllCities();

        if (map.get(startCity) != null && map.get(endCity) != null) {

            // Start recursion
            recursion(startCity);
            
            // If one of the endCity connect cities is connected
            for (City connCity : map.get(endCity)) {
                if (connCity.isConnected) {
                    System.out.println("**** " + startCity + " is connected to " + endCity);
                    return true;
                }
            }
            // If not
            System.out.println("**** " + startCity + " is not connected to " + endCity);
            return false;
        } else {
            System.out.println("**** " + startCity + " or " + endCity + " is not in the file ");
            //Not in the file
            return false;
        }


    }

    /**
     * Recursive
     * Like domino, set all the connected city isConnected to true.
     *
     */
    private void recursion(String cityName) {
        for (City connCity : map.get(cityName)) {
            if (!connCity.isConnected) {
                connCity.isConnected = true;
                recursion(connCity.name);
            }
        }
    }

}


