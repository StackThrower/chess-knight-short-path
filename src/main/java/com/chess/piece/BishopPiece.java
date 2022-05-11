package com.chess.piece;

import com.chess.board.Cell;

import java.util.List;

public class BishopPiece extends Piece{

    private final static String SYMBOL = "♗";
    public final static String PIECE_ID = "B";

    public BishopPiece() {
        super();
    }

    public BishopPiece(Cell currentPosition) {
        super(currentPosition, SYMBOL, PIECE_ID);
    }

    @Override
    public List<Cell> availableMoves(Cell currentPosition) {
        return null;
    }
}
