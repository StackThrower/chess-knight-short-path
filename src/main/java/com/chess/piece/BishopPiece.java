package com.chess.piece;

import com.chess.board.Cell;

import java.util.HashSet;
import java.util.Set;

public class BishopPiece extends Piece {

    private final static String SYMBOL = "♗";
    public final static String PIECE_ID = "B";

    public BishopPiece() {
        super();
    }

    public BishopPiece(Cell position) {
        super(position, SYMBOL, PIECE_ID);
    }

    @Override
    public Set<Cell> availableMoves(Cell position) {
        return new HashSet<>();
    }


}
