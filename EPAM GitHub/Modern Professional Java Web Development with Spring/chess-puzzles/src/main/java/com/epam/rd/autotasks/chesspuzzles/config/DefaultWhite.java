package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.Cell;
import com.epam.rd.autotasks.chesspuzzles.ChessBoard;
import com.epam.rd.autotasks.chesspuzzles.ChessPiece;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("unused")
@Configuration
public class DefaultWhite implements ChessBoard {

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
