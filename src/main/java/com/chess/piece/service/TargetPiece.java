package com.chess.piece.service;

import com.chess.board.Cell;
import com.chess.piece.Piece;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Set<Cell> availableMoves(Cell cell) {
        return new HashSet<>();
    }
}
