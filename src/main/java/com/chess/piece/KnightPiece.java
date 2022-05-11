package com.chess.piece;

import com.chess.board.Cell;

import java.util.ArrayList;
import java.util.List;

public class KnightPiece extends Piece {

    private final static String SYMBOL = "♞";

    public final static String PIECE_ID = "K";

    public KnightPiece() {
        super();
    }

    public KnightPiece(Cell currentPosition) {
        super(currentPosition, SYMBOL, PIECE_ID);
    }

    @Override
    public List<Cell> availableMoves(Cell currentPosition) {
        return new ArrayList<>();
    }
}
