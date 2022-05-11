package com.chess.processor;

import com.chess.piece.Piece;

import java.util.HashSet;
import java.util.Set;

public class StepProcessor {

    public final static int DEFAULT_STEP_CALCULATOR_LEVEL = 3;

    public static Set<Step> calculate(Piece piece, Step currentStep, int level) {
        Set<Step> steps = piece.availableMoves(currentStep);


        if (level > 1) {
            Set<Step> subSteps = new HashSet<>();
            for (Step childStep : steps) {
                Set<Step> subChildSteps = calculate(piece, new Step(childStep.getX(), childStep.getY(), childStep.getLevel() + 1), level - 1);
                subSteps.addAll(subChildSteps);
            }
            steps.addAll(subSteps);
        }

        return steps;
    }

}
