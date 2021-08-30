package secondTask;
/*
A class that stores all information about connection between cities
 */

public class Connection{
    int indexOfStart;
    int indexOfEnd;
    int cost;
    Connection(int start, int end, int cost){
        indexOfStart = start;
        indexOfEnd = end;
        this.cost = cost;
    }
}
