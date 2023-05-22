package com.epam.rd.autotasks;

import java.util.Scanner;

import static java.lang.Math.sqrt;

public class QuadraticEquation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        double discriminant=Math.pow(b, 2)-4*a*c;

        if (discriminant==0){
            System.out.println((-b)/2*a);
        }
        else if (discriminant>0){
            System.out.print((((-b)-(Math.sqrt(discriminant)))/(2*a))+" "+
                    (((-b)+(Math.sqrt(discriminant)))/(2*a)));
        }
        else
            System.out.println("no roots");


    }

}