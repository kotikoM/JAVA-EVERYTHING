package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Arrays;

public class PlayerImpl implements Player {

    private TicTacToe ticTacToe;
    private char mark;
    private PlayerStrategy strategy;


    public PlayerImpl(TicTacToe ticTacToe, char mark, PlayerStrategy strategy) {
        this.ticTacToe = ticTacToe;
        this.mark = mark;
        this.strategy = strategy;
    }

    public synchronized boolean isWinner() {
        char[][] table = ticTacToe.table();
        return checkRows(table) || checkColumns(table) || checkDiagonals(table);
    }

    private synchronized boolean checkRows(char[][] table) {
        return checkLine(table[0])
                || checkLine(table[1])
                || checkLine(table[2]);
    }

    private synchronized boolean checkColumns(char[][] table) {
        return checkLine(table[0][0], table[1][0], table[2][0])
                || checkLine(table[0][1], table[1][1], table[2][1])
                || checkLine(table[0][2], table[1][2], table[2][2]);
    }

    private synchronized boolean checkDiagonals(char[][] table) {
        return checkLine(table[0][0], table[1][1], table[2][2])
                || checkLine(table[0][2], table[1][1], table[2][0]);
    }

    private synchronized boolean checkLine(char... line) {
        return Arrays.equals(line, new char[]{'X', 'X', 'X'})
                || Arrays.equals(line, new char[]{'O', 'O', 'O'});
    }

    private synchronized boolean isFreePlace() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTacToe.table()[i][j] == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

        @Override
    public void run() {
        while (!isWinner()) {
            synchronized (ticTacToe) {
                if (mark != ticTacToe.lastMark() && !isWinner() && isFreePlace()) {
                    Move move = strategy.computeMove(mark, ticTacToe);
                    ticTacToe.setMark(move.row, move.column, mark);
                }
            }
        }
    }
}
