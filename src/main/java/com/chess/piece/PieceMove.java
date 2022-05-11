package com.chess.piece;

import com.chess.Step;
import com.chess.board.Cell;

import java.util.Set;

public interface PieceMove {

    Set<Step> availableMoves(Step currentStep);

}
