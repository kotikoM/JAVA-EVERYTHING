package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.Cell;
import com.epam.rd.autotasks.chesspuzzles.ChessBoard;
import com.epam.rd.autotasks.chesspuzzles.ChessPiece;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("unused")
@Configuration
public class Puzzle03 implements ChessBoard {

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
        return getPiece('G', 5, 'K');
    }

    @Bean
    public ChessPiece blackQueen() {
        return getPiece('D', 7, 'Q');
    }

    @Bean
    public ChessPiece blackRook1() {
        return getPiece('C', 1, 'R');
    }

    @Bean
    public ChessPiece blackRook2() {
        return getPiece('C', 3, 'R');
    }

    @Bean
    public ChessPiece blackBishop1() {
        return getPiece('B', 4, 'B');
    }

    @Bean
    public ChessPiece blackPawn1() {
        return getPiece('B', 7, 'P');
    }

    @Bean
    public ChessPiece blackPawn2() {
        return getPiece('E', 7, 'P');
    }

    @Bean
    public ChessPiece blackPawn3() {
        return getPiece('A', 6, 'P');
    }

    @Bean
    public ChessPiece blackPawn4() {
        return getPiece('D', 6, 'P');
    }

    @Bean
    public ChessPiece blackPawn5() {
        return getPiece('G', 6, 'P');
    }

    @Bean
    public ChessPiece whiteKing() {
        return getPiece('H', 1, 'k');
    }

    @Bean
    public ChessPiece whiteRook1() {
        return getPiece('F', 7, 'r');
    }

    @Bean
    public ChessPiece whiteRook2() {
        return getPiece('H', 8, 'r');
    }

    @Bean
    public ChessPiece whiteBishop1() {
        return getPiece('D', 1, 'b');
    }


    @Bean
    public ChessPiece whitePawn1() {
        return getPiece('G', 2, 'p');
    }

    @Bean
    public ChessPiece whitePawn2() {
        return getPiece('H', 2, 'p');
    }

    @Bean
    public ChessPiece whitePawn3() {
        return getPiece('D', 5, 'p');
    }

    @Override
    public String state() {
        return ".......r\n" +
                ".P.QPr..\n" +
                "P..P..P.\n" +
                "...p..K.\n" +
                ".B......\n" +
                "..R.....\n" +
                "......pp\n" +
                "..Rb...k";
    }
}
