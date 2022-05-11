package com.chess;

import com.chess.board.Cell;
import com.chess.piece.Piece;

import java.util.Set;

public class StepCalculator {

    final static int DEFAULT_STEP_CALCULATOR_LEVEL = 3;


    public static Set<Cell> calculate(Piece piece, Cell cell, int level) {
        Set<Cell> steps = piece.availableMoves(cell);

        if(level > 1) {
            for(Cell childCell: steps) {
                Set<Cell> childCells = calculate(piece, childCell, --level);

                steps.addAll(childCells);
            }

        } else {
            return steps;
        }

        return steps;
    }

}
