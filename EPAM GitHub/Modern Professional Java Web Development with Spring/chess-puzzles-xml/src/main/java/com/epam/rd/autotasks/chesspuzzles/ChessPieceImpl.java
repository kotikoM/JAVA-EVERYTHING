package com.epam.rd.autotasks.chesspuzzles;

public class ChessPieceImpl implements ChessPiece{
    private Cell cell;

    private char pieceChar;

    public ChessPieceImpl(char letter, int num, char pieceChar){
        this.cell = Cell.cell(letter, num);
        this.pieceChar = pieceChar;
    }
    @Override
    public Cell getCell() {
        return cell;
    }

    @Override
    public char toChar() {
        return pieceChar;
    }
}
