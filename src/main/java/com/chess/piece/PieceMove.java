package com.chess.piece;

import com.chess.processor.Step;

import java.util.Set;

public interface PieceMove {

    Set<Step> availableMoves(Step currentStep);

}
