package com.chess.processor;

import com.chess.board.Cell;
import com.chess.piece.Piece;
import com.chess.processor.exception.InvalidStepProcessorParams;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StepProcessor {

    public final static int TOTAL_STEP_LEVELS = 3;

    public final static int START_STEP_LEVEL_ID = 0;

    private static final List<Step> successStepsTails = new ArrayList<>();

    private static void validateParams(Piece piece, Step currentStep, Set<Cell> targetCells) throws InvalidStepProcessorParams {
        if (piece == null || currentStep == null || targetCells == null)
            throw new InvalidStepProcessorParams();
    }

    public static Set<Step> calculate(Piece piece, Step currentStep, int level, Set<Cell> targetCells) throws InvalidStepProcessorParams {
        validateParams(piece, currentStep, targetCells);

        Set<Step> steps = piece.availableMoves(currentStep);

        findSuccessfulSteps(steps, targetCells);

        if (level > 1) {
            Set<Step> subSteps = new HashSet<>();
            for (Step childStep : steps) {
                Set<Step> subChildSteps = calculate(piece,
                        new Step(childStep.getX(), childStep.getY(), childStep.getLevel() + 1, currentStep),
                        level - 1, targetCells);
                subSteps.addAll(subChildSteps);
            }
            steps.addAll(subSteps);
        }

        steps.add(currentStep); // init start level 1.

        return steps;
    }


    private static void findSuccessfulSteps(Set<Step> steps, Set<Cell> targetCells) {
        for (Cell cell : targetCells) {
            List<Step> successfulSteps = steps.stream()
                    .filter(step -> step.equals(new Step(cell.getX(), cell.getY(), 0)))
                    .collect(Collectors.toList());

            if (successfulSteps.size() > 0) {
                successStepsTails.addAll(successfulSteps);
            }
        }
    }

    public static List<Step> getSuccessStepsTails() {
        return successStepsTails;
    }

    public static void clearSuccessStepsTails() {
        successStepsTails.clear();
    }

}
