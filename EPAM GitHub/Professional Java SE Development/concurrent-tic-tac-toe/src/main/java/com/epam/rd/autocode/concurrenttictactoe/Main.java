package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Arrays;

public class Main {

    private static char[][] table =
            {{'X', 'O', 'O'},
            {'X', 'X', 'X'},
            {'O', ' ', ' '}};

    public static boolean isWinner(){
        return checkRows() || checkColumns() || checkDiagonals();
    }

    private static boolean checkRows(){
        return checkLine(table[0])
                || checkLine(table[1])
                || checkLine(table[2]);
    }

    private static boolean checkColumns(){

        return checkLine(table[0][0], table[1][0], table[2][0])
                || checkLine(table[0][1], table[1][1], table[2][1])
                || checkLine(table[0][2], table[1][2], table[2][2]);
    }

    private static boolean checkDiagonals() {
        return checkLine(table[0][0], table[1][1], table[2][2])
                || checkLine(table[0][2], table[1][1], table[2][0]);
    }

    private static boolean checkLine(char... line){
        return Arrays.equals(line, new char[]{'X', 'X', 'X'}) ||
                Arrays.equals(line, new char[]{'O', 'O', 'O'}) ;
    }

    public static void main(String[] args) {
        System.out.println(checkRows());
    }
}
