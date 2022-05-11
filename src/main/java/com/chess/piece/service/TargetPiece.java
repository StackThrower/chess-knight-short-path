package com.chess.piece.service;

import com.chess.board.Cell;
import com.chess.piece.Piece;

import java.util.List;

public class TargetPiece extends Piece {

    private final static String SYMBOL = "@";
    public final static String PIECE_ID = "T";

    public TargetPiece() {
        super();
    }

    public TargetPiece(Cell currentPosition) {
        super(currentPosition, SYMBOL, PIECE_ID);
    }

    @Override
    public List<Cell> availableMoves(Cell currentPosition) {
        return null;
    }
}
