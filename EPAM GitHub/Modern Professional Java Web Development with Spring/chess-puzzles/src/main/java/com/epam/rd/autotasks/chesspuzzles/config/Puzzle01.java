package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.Cell;
import com.epam.rd.autotasks.chesspuzzles.ChessBoard;
import com.epam.rd.autotasks.chesspuzzles.ChessPiece;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("unused")
@Configuration
public class Puzzle01 implements ChessBoard {

    private static ChessPiece getPiece(char letter, int number, char piece) {
        return new ChessPiece() {
            @Override
            public Cell getCell() {
                return Cell.cell(letter, number);
            }

            @Override
            public char toChar() {
                return piece;
            }
        };
    }

    @Bean
    public ChessPiece blackKing() {
        return getPiece('C', 5, 'K');
    }

    @Bean
    public ChessPiece blackRook1() {
        return getPiece('E', 8, 'R');
    }

    @Bean
    public ChessPiece blackBishop1() {
        return getPiece('D', 6, 'B');
    }

    @Bean
    public ChessPiece blackBishop2() {
        return getPiece('D', 5, 'B');
    }

    @Bean
    public ChessPiece blackPawn1() {
        return getPiece('F', 6, 'P');
    }

    @Bean
    public ChessPiece blackPawn2() {
        return getPiece('C', 3, 'P');
    }


    @Bean
    public ChessPiece whiteKing() {
        return getPiece('B', 7, 'k');
    }

    @Bean
    public ChessPiece whiteBishop1() {
        return getPiece('E', 2, 'b');
    }

    @Bean
    public ChessPiece whiteKnight1() {
        return getPiece('C', 6, 'n');
    }

    @Bean
    public ChessPiece whitePawn1() {
        return getPiece('D', 2, 'p');
    }

    @Override
    public String state() {
        return "....R...\n" +
                ".k......\n" +
                "..nB.P..\n" +
                "..KB....\n" +
                "........\n" +
                "..P.....\n" +
                "...pb...\n" +
                "........";
    }
}
