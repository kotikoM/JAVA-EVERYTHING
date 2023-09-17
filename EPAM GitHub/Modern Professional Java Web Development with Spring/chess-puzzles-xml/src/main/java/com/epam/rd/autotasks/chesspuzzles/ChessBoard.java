package com.epam.rd.autotasks.chesspuzzles;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface ChessBoard {
    class ChessBoardImpl implements ChessBoard{

        private final Map<String, ChessPiece> board = new HashMap<>();

        private ChessBoardImpl(Collection<ChessPiece> pieces) {
            for (ChessPiece piece : pieces) {
                Cell position = piece.getCell();
                board.put(position.letter + Integer.toString(position.number), piece);
            }
        }

        @Override
        public String state() {
            StringBuilder stateBuilder = new StringBuilder();
            for (int row = 8; row >= 1; row--) {
                for (char col = 'A'; col <= 'H'; col++) {
                    String position = col + Integer.toString(row);
                    ChessPiece piece = board.get(position);
                    if (piece != null) {
                        stateBuilder.append(piece.toChar());
                    } else {
                        stateBuilder.append('.');
                    }
                }
                if (row != 1) {
                    stateBuilder.append('\n');
                }
            }
            return stateBuilder.toString();
        }
    }
    static ChessBoard of(Collection<ChessPiece> pieces){
        return new ChessBoardImpl(pieces);
    }

    String state();
}
