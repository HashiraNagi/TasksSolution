package firstTask;

import java.util.Scanner;
/*
For resolving this task I decide to use "Catalan number"
 */
public class Main {

    public static void main(String[] args) {
        //Asking user input number of opening and closing brackets
        System.out.println("Input number of opening and closing brackets: ");
        Scanner scan = new Scanner(System.in);
        /*Add 1 to entered number because Catalan number algorithm start with 0
        (0 it is a void, no one pair of bracket. This ) */
        Integer N = scan.nextInt()+1;
        System.out.println("The number of all possible placement options is " + calculate(N));

    }

    //Function that calculate Catalan Number
    public static long calculate(Integer N){
        /*Creating a Catalan Number array.
        Index = number of opening and closing brackets.
        The value of the array element with this index = the number of possible combinations.
         */
        long[] CatalanNumbers = new long[N];
        //A void, we сan place one way
        CatalanNumbers[0] = 1;
        int maxIndex = N-1;
        long result = 0;
        /*
        To calculate the next number, you need to take the first number from the beginning of the array
        and multiply by the first number from the end of the array, then add the second number from the beginning of the array
        multiplied by the second number from the end of the array, and so on until the array ends.
         */
        for(int i=1,lengthOfArray = 0;N>1;N--,i++,lengthOfArray++){
            for(int length = lengthOfArray,countdownIndex = 0;length>=0;length--,countdownIndex++){
                result = result + CatalanNumbers[length]*CatalanNumbers[countdownIndex];
            }
            CatalanNumbers[i] = result;
            result = 0;
        }
        return CatalanNumbers[maxIndex];
    }

}

