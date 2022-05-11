package com.chess.piece.service;

import com.chess.board.Cell;
import com.chess.piece.Piece;

import java.util.HashSet;
import java.util.Set;

public class TraceDebugPiece extends Piece {

    private final static String SYMBOL = "D";
    public final static String PIECE_ID = "D";

    public TraceDebugPiece() {
        super();
    }

    public TraceDebugPiece(Cell currentPosition) {
        super(currentPosition, SYMBOL, PIECE_ID);
    }

    @Override
    public Set<Cell> availableMoves(Cell cell) {
        return new HashSet<>();
    }
}
