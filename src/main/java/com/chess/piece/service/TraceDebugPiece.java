package com.chess.piece.service;

import com.chess.processor.Step;
import com.chess.board.Cell;
import com.chess.piece.Piece;

import java.util.HashSet;
import java.util.Set;

public class TraceDebugPiece extends Piece implements ServicePiece {

    public final static String PIECE_ID = "D";

    public TraceDebugPiece() {
        super();
    }

    public TraceDebugPiece(Cell currentPosition, int level) {
        super(currentPosition, String.valueOf(level), PIECE_ID);
    }

    @Override
    public Set<Step> availableMoves(Step currentStep) {
        return new HashSet<>();
    }
}
