package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.Cell;
import com.epam.rd.autotasks.chesspuzzles.ChessBoard;
import com.epam.rd.autotasks.chesspuzzles.ChessPiece;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("unused")
@Configuration
public class Default implements ChessBoard {

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

    @Bean
    public ChessPiece whiteKing() {
        return getPiece('E', 1, 'k');
    }

    @Bean
    public ChessPiece whiteQueen() {
        return getPiece('D', 1, 'q');
    }

    @Bean
    public ChessPiece whiteRook1() {
        return getPiece('A', 1, 'r');
    }

    @Bean
    public ChessPiece whiteRook2() {
        return getPiece('H', 1, 'r');
    }

    @Bean
    public ChessPiece whiteBishop1() {
        return getPiece('C', 1, 'b');
    }

    @Bean
    public ChessPiece whiteBishop2() {
        return getPiece('F', 1, 'b');
    }

    @Bean
    public ChessPiece whiteKnight1() {
        return getPiece('B', 1, 'n');
    }

    @Bean
    public ChessPiece whiteKnight2() {
        return getPiece('G', 1, 'n');
    }

    @Bean
    public ChessPiece whitePawn1() {
        return getPiece('A', 2, 'p');
    }

    @Bean
    public ChessPiece whitePawn2() {
        return getPiece('B', 2, 'p');
    }

    @Bean
    public ChessPiece whitePawn3() {
        return getPiece('C', 2, 'p');
    }

    @Bean
    public ChessPiece whitePawn4() {
        return getPiece('D', 2, 'p');
    }

    @Bean
    public ChessPiece whitePawn5() {
        return getPiece('E', 2, 'p');
    }

    @Bean
    public ChessPiece whitePawn6() {
        return getPiece('F', 2, 'p');
    }

    @Bean
    public ChessPiece whitePawn7() {
        return getPiece('G', 2, 'p');
    }

    @Bean
    public ChessPiece whitePawn8() {
        return getPiece('H', 2, 'p');
    }

    @Override
    public String state() {
        return "RNBQKBNR\n" +
                "PPPPPPPP\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "pppppppp\n" +
                "rnbqkbnr";
    }
}
