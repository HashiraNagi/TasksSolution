package thirdTask;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {

        System.out.println(calculateSum(100));

    }

    public static BigInteger calculateSum(int max){

        BigInteger sum = BigInteger.valueOf(0);
        BigInteger fac = BigInteger.valueOf(1);

        //Calculating 100!
        for (int i = 2; i <= 100; i++) {
            fac = fac.multiply(BigInteger.valueOf(i));
        }
        //While fac > 0 add the last digit in the "fac" to the "sum" and then remove this digit from "fac"
        while (fac.compareTo(BigInteger.valueOf(0))>0) {
            sum = sum.add(fac.remainder(BigInteger.valueOf(10)));
            fac = fac.divide(BigInteger.valueOf(10));
        }
        return sum;
    }
}
