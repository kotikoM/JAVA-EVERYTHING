package com.epam.rd.autotasks;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Factorial {
    public String factorial(String n) {

        if (n == null) {
            throw new IllegalArgumentException();
        }

        try {
            Integer.parseInt(n);
        }
        catch (NumberFormatException e){
            throw new IllegalArgumentException();
        }

        if (Integer.parseInt(n) < 0) {
            throw new IllegalArgumentException();
        }



        Integer integer = Integer.parseInt(n);

        if (integer < 0) {
            throw new IllegalArgumentException();
        }

        BigInteger result = BigInteger.valueOf(1);

        for (int i = 1; i <= integer; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Factorial factorial = new Factorial();

        System.out.println(factorial.factorial("3.1"));
    }

}
