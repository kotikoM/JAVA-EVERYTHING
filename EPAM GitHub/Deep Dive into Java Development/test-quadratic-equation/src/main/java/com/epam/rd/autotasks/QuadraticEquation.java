package com.epam.rd.autotasks;

public class QuadraticEquation {
    public String solve(double a, double b, double c) {
       if (a==0) {
           throw new IllegalArgumentException();
       }
       else {
           double discriminant = Math.pow(b, 2) - 4 * a * c;

           if (discriminant < 0){
               return "no roots";
           }
           else if (discriminant == 0){
               return Double.toString((-b) / (2 * a));
           }
           else {
               return (((-b) - Math.sqrt(discriminant)) / (2 * a)) + " " +
                       ((-b) + Math.sqrt(discriminant)) / (2 * a);
           }
       }
    }

}