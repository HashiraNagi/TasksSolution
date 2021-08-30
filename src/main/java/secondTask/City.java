package secondTask;
import java.util.*;

/*
A class that stores and processes information about cities.
 */

public class City {

    String name;
    static int index = 1;
    public int thisCityIndex = index;
    int numberOfNeighbors;

    //Array that contains all connections between cities
    static ArrayList<Connection> allTransits = new ArrayList<>();

    static int startId=0;
    static int endId=0;

    //Constructor of city
    City(String name, int numberOfNeighbors){
        this.name = name;
        this.numberOfNeighbors = numberOfNeighbors;
        index++;
    }

    //Method that creates new connection between two cities
    public static void getDirection(int start, int end, int cost){
        allTransits.add(new Connection(start,end,cost));
    }


    public static int getStartPoint(String start) {
        for (City c : Main.citiesArray) {
            if (c.name.equals(start)) {
                return c.thisCityIndex;

            }
        }
        return -1;
    }

    public static int getEndPoint(String end) {
            for (City c : Main.citiesArray) {
                if (c.name.equals(end)) {
                    return c.thisCityIndex;

                }
            }
            return -1;
        }



        /*
            Algorithm for finding the shortest path (dijkstra algorithm)
            1. Pick first node and calculate distances to all nodes(if there is no path,
            then we assume that the cost is equal to infinity) At the moment, these are the shortest paths.
            2. Pick next node with minimal distance and calculate distances to adjacent nodes
            3. Compare the resulting minimum paths with the previous minimum paths. If resulting path is smaller replace previous path
            4. Repeat 2 and 3 steps until all cities have been visited
        */
        public static int[] dijkstraAlgorithm(Map<Integer,Map<Integer,Integer>> nodes, int start) {

        //INITIALIZATION FIRST POSITION (start of 1 step)
            int[] masWithCosts = new int[Main.numberOfCities + 1];
            Set<Integer> visited = new HashSet<>();


                for (int ii = 1; ii < Main.numberOfCities + 1; ii++) {
                    Map<Integer, Integer> temp = new HashMap<>(nodes.get(start));
                    if(temp.get(ii) != null) {
                        masWithCosts[ii] = temp.get(ii);
                    }
                    else {
                        masWithCosts[ii] = Integer.MAX_VALUE;
                    }
                }
                visited.add(start);
        //END OF INITIALIZATION (end of 1 step)


        //2 and 3 steps
            while (visited.size() < Main.numberOfCities){
            start = findMin(masWithCosts, visited);
            Map<Integer, Integer> temp = new HashMap<>(nodes.get(start));
            for(int i = 1; i < Main.numberOfCities + 1; i++){
                if( temp.get(i) != null) {
                    if(masWithCosts[i]>temp.get(i)+masWithCosts[start]){
                        masWithCosts[i] = temp.get(i)+masWithCosts[start];
                    }
                }

            }


            visited.add(start);
            }
            return masWithCosts;
        }

        //Function that finds minimal way
        public static int findMin(int[] mas,Set<Integer> visited){
        int min=Integer.MAX_VALUE;
        int indexOfMin=0;
        int[] returnedMas = new int[2];
        for(int i = 1; i<mas.length; i++){
            if(mas[i]<min && !visited.contains(i)){
                min = mas[i];
                indexOfMin = i;
            }
        }
        return indexOfMin;
        }

}