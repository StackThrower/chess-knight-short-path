package com.chess.processor;


import com.chess.board.Cell;
import com.chess.board.exeption.CellNotFound;
import com.chess.piece.KnightPiece;
import com.chess.piece.Piece;
import com.chess.processor.exception.InvalidStepProcessorParams;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StepProcessorTest {

    @Test
    void nullableTestForStepProcessor() {

        assertThrows(InvalidStepProcessorParams.class, () -> StepProcessor.calculate(null, null, 0, null));

    }

    @Test
    void findTargetCellByKnightInOneStep() throws CellNotFound, InvalidStepProcessorParams {

        Piece piece = new KnightPiece(new Cell((byte) 0, (byte) 0));

        Set<Cell> targetCells = new HashSet<>();
        Cell targetCell = new Cell((byte) 1, (byte) 2);
        targetCells.add(targetCell);

        final int TOTAL_STEP_LEVELS = 1;

        StepProcessor.calculate(piece, new Step(piece.getPosition().getX(), piece.getCurrentPosition().getY(),
                StepProcessor.START_STEP_LEVEL_ID), TOTAL_STEP_LEVELS, targetCells);

        List<Step> tails = StepProcessor.getSuccessStepsTails();


        assertEquals(tails.size(), 1);

        assertEquals(new Cell(tails.get(0).getX(),tails.get(0).getY()), targetCell);

        StepProcessor.clearSuccessStepsTails();
    }


    // TODO implement more tests with unique aspects.

}