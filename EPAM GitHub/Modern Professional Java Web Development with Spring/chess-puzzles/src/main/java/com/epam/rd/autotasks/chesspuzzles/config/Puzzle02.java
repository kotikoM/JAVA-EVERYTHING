package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.Cell;
import com.epam.rd.autotasks.chesspuzzles.ChessBoard;
import com.epam.rd.autotasks.chesspuzzles.ChessPiece;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("unused")
@Configuration
public class Puzzle02 implements ChessBoard {

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
        return getPiece('D', 3, 'K');
    }

    @Bean
    public ChessPiece blackQueen() {
        return getPiece('F', 4, 'Q');
    }

    @Bean
    public ChessPiece blackPawn1() {
        return getPiece('E', 2, 'P');
    }

    @Bean
    public ChessPiece whiteKing() {
        return getPiece('B', 2, 'k');
    }

    @Bean
    public ChessPiece whiteQueen() {
        return getPiece('H', 8, 'q');
    }
    @Override
    public String state() {
        return ".......q\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                ".....Q..\n" +
                "...K....\n" +
                ".k..P...\n" +
                "........";
    }
}
