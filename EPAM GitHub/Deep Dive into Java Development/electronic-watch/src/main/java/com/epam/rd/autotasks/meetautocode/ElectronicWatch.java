package com.epam.rd.autotasks.meetautocode;

import java.util.Scanner;

public class ElectronicWatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //System.out.print("Input: ");
        int sec = scanner.nextInt();
        int hours=0;
        int min=0;
        int secO=0;

        if (sec<60){
            secO=sec;
        }
            else while (sec>=60) {
            //get number of hours
            if (sec/3600>0){
                while (sec>=3600){
                    ++hours;
                    sec-=3600;
                }
                hours=hours%24;
            }

            //get number of minutes
            if (sec/60>0){
                while (sec>=60){
                    ++min;
                    sec-=60;
                }
            }
            //no need to check overflow of minutes or seconds
            //since previous loop checks it
            secO=sec;
        }

        System.out.printf("%01d:%02d:%02d", hours, min, secO);
    }
}
