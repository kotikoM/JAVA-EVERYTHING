package com.epam.rd.autotasks;

import java.util.Scanner;

public class Average {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sum=0; //sum
        int count=0; //counter for amount of variables


        int number;
        //not stopping until user inputs 0
        while ((number=scanner.nextInt())!=0){
            sum+=number;
            count+=1;
        }

        System.out.println(sum/count);
    }

}