package com.chess.piece;

import com.chess.board.Cell;

import java.util.List;
import java.util.Set;

public interface PieceMove {

    Set<Cell> availableMoves(Cell currentPosition);

}
