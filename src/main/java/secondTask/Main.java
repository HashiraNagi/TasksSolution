package secondTask;

import java.util.*;


public class Main {
    //Array with all cities
    static ArrayList<City> citiesArray = new ArrayList<>();

    //start city index
    static int start = 0;
    //end city index
    static int end = 0;

    static int numberOfCities;

    public static void main(String[] args) {

        /*Outer map:
            key = City x
            value = Inner Map:
                    key = City y that connected to City x
                    value = cost of the road from City x to City y
         */
        Map<Integer,Map<Integer,Integer>> allNodes = new HashMap<>();


        int numberOfTest;
        int numberOfPathsToFind;

        Scanner scan = new Scanner(System.in);
        numberOfTest = scan.nextInt();


        for (int yy = 0; yy < numberOfTest; yy++) {

            //List with city of departure and city of arrival
            ArrayList<String> listOfGivenWays = new ArrayList<>();

            numberOfCities = scan.nextInt();

            //Creating a cities
            for (int i = numberOfCities; i > 0; i--) {
                String name = scan.next();
                scan.nextLine();
                int numberOfNeighbors = scan.nextInt();
                scan.nextLine();
                citiesArray.add(new City(name, numberOfNeighbors));
                //Creating connections between cities
                for (int y = numberOfNeighbors; y > 0; y--) {

                    City currentFirstCity = citiesArray.get(citiesArray.size() - 1);
                    int currentInd = currentFirstCity.thisCityIndex;

                    String neighborsIndexAndCost = scan.nextLine();
                    String[] mas = neighborsIndexAndCost.split(" ");
                    City.getDirection(currentInd, Integer.parseInt(mas[0]), Integer.parseInt(mas[1]));


                }

            }

            numberOfPathsToFind = scan.nextInt();
            scan.nextLine();
            for (int i = numberOfPathsToFind; i > 0; i--) {
                listOfGivenWays.add(scan.nextLine());
            }

            //Putting all the information about cities and their connections in variable "allNodes"
            for (int i = 1; i < numberOfCities + 1; i++) {
                Map<Integer, Integer> temp = new HashMap<>();
                for (int x = 0; x < City.allTransits.size(); x++) {
                    if (City.allTransits.get(x).indexOfStart == i) {
                        temp.put(City.allTransits.get(x).indexOfEnd, City.allTransits.get(x).cost);
                    }
                }
                allNodes.put(i, temp);
            }

            //Looking for minimal ways for each pair of inputted city
            for (int i = listOfGivenWays.size(), y = 0; i > 0; i--, y++) {
                String[] mas = listOfGivenWays.get(y).split(" ");
                start = City.getStartPoint(mas[0]);
                end = City.getEndPoint(mas[1]);;
                System.out.println(City.dijkstraAlgorithm(allNodes, start)[end]);

            }

        }

    }
}

