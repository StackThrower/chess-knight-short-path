package com.chess.piece.service;

import com.chess.processor.Step;
import com.chess.board.Cell;
import com.chess.piece.Piece;

import java.util.HashSet;
import java.util.Set;

public class TargetPiece extends Piece implements ServiceTargetPiece {

    private final static String SYMBOL = "@";
    public final static String PIECE_ID = "T";

    public TargetPiece() {
        super();
    }

    public TargetPiece(Cell currentPosition) {
        super(currentPosition, SYMBOL, PIECE_ID);
    }

    @Override
    public Set<Step> availableMoves(Step currentStep) {
        return new HashSet<>();
    }
}
