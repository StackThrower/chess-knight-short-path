package com.chess.piece;

import com.chess.board.Cell;

import java.util.List;

public interface PieceMove {

    List<Cell> availableMoves(Cell currentPosition);

}
