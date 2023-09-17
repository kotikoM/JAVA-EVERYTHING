package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.Cell;
import com.epam.rd.autotasks.chesspuzzles.ChessBoard;
import com.epam.rd.autotasks.chesspuzzles.ChessPiece;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("unused")
@Configuration
public class DefaultBlack implements ChessBoard {
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
        return getPiece('E', 8, 'K');
    }

    @Bean
    public ChessPiece blackQueen() {
        return getPiece('D', 8, 'Q');
    }

    @Bean
    public ChessPiece blackRook1() {
        return getPiece('A', 8, 'R');
    }

    @Bean
    public ChessPiece blackRook2() {
        return getPiece('H', 8, 'R');
    }

    @Bean
    public ChessPiece blackBishop1() {
        return getPiece('C', 8, 'B');
    }

    @Bean
    public ChessPiece blackBishop2() {
        return getPiece('F', 8, 'B');
    }

    @Bean
    public ChessPiece blackKnight1() {
        return getPiece('B', 8, 'N');
    }

    @Bean
    public ChessPiece blackKnight2() {
        return getPiece('G', 8, 'N');
    }

    @Bean
    public ChessPiece blackPawn1() {
        return getPiece('A', 7, 'P');
    }

    @Bean
    public ChessPiece blackPawn2() {
        return getPiece('B', 7, 'P');
    }

    @Bean
    public ChessPiece blackPawn3() {
        return getPiece('C', 7, 'P');
    }

    @Bean
    public ChessPiece blackPawn4() {
        return getPiece('D', 7, 'P');
    }

    @Bean
    public ChessPiece blackPawn5() {
        return getPiece('E', 7, 'P');
    }

    @Bean
    public ChessPiece blackPawn6() {
        return getPiece('F', 7, 'P');
    }

    @Bean
    public ChessPiece blackPawn7() {
        return getPiece('G', 7, 'P');
    }

    @Bean
    public ChessPiece blackPawn8() {
        return getPiece('H', 7, 'P');
    }

    @Override
    public String state() {
        return "RNBQKBNR\n" +
                "PPPPPPPP\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "........";
    }
}
