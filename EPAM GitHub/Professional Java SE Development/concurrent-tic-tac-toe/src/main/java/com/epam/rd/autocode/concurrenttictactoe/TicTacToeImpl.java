package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Arrays;

public class TicTacToeImpl implements TicTacToe {

    char lastMark = 'O';
    char[][] table = new char[][] {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
    };

    @Override
    synchronized public void setMark(int x, int y, char mark) {
        if (table[x][y] != ' ') {
            throw new IllegalArgumentException();
        }

        table[x][y] = mark;
        lastMark = mark;
    }

    @Override
    synchronized public  char[][] table() {
        //create identical copy of the table
        return Arrays.stream(table)
                .map(char[]::clone)
                .toArray(char[][]::new);

    }

    @Override
    synchronized public char lastMark() {
        return lastMark;
    }
}

