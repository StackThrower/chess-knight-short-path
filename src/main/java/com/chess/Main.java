package com.chess;

import com.chess.board.Cell;
import com.chess.board.CellLabel;
import com.chess.board.exeption.CellLabelNotFoundException;
import com.chess.board.exeption.CellNotFound;
import com.chess.console.ChessBoardPrinter;
import com.chess.console.ConsoleInputReader;
import com.chess.console.exception.IncorrectConsoleInput;
import com.chess.piece.Piece;
import com.chess.piece.registry.exception.IncorrectRegistrySettings;
import com.chess.piece.service.ServiceTargetPiece;
import com.chess.piece.service.TraceDebugPiece;
import com.chess.processor.Step;
import com.chess.processor.StepProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    private static final String EXIT_COMMAND = "quit";

    Set<Piece> pieces;

    List<String> successfulPaths = new ArrayList<>();

    public Main() {
    }

    public void process(String input) {

        try {
            pieces = ConsoleInputReader.parseInput(input);

            Set<Piece> tracePieces = new HashSet<>();
            Set<Cell> targetCells = new HashSet<>();

            // Find all targets
            for (Piece piece : pieces) {
                if (piece instanceof ServiceTargetPiece) {
                    targetCells.add(piece.getCurrentPosition());
                }
            }

            for (Piece piece : pieces) {
                Cell currentPosition = piece.getCurrentPosition();


                Set<Step> debugSteps = StepProcessor.calculate(piece, new Step(currentPosition.getX(),
                                currentPosition.getY(), 1),
                        StepProcessor.DEFAULT_STEP_CALCULATOR_LEVEL, targetCells);

                List<Step> successTails = StepProcessor.getSuccessStepsTails();
                for (Step traceStep : successTails) {

                    StringBuilder sb = new StringBuilder();

                    Step subTraceStep = traceStep;
                    do {

                        Piece newPiece = new TraceDebugPiece(new Cell(subTraceStep.getX(),
                                subTraceStep.getY()), subTraceStep.getLevel());

                        try {
                            if (sb.length() > 0) {
                                sb.insert(0, piece.getPieceId() +
                                        CellLabel.getByX(newPiece.getCurrentPosition().getX()).getLabel() +
                                        (newPiece.getCurrentPosition().getY() + 1) + " -> ");
                            } else {
                                sb.insert(0, piece.getPieceId() +
                                        CellLabel.getByX(newPiece.getCurrentPosition().getX()).getLabel() +
                                        (newPiece.getCurrentPosition().getY() + 1));
                            }

                        } catch (CellLabelNotFoundException e) {
                            // TODO write to log
                        }
                        tracePieces.add(newPiece);

                        subTraceStep = subTraceStep.getParent();

                    } while (subTraceStep != null);

                    successfulPaths.add(sb.toString());
                }

                StepProcessor.clearSuccessStepsTails();
            }
            pieces.addAll(tracePieces);

            ChessBoardPrinter chessBoardPrinter = new ChessBoardPrinter(pieces);
            chessBoardPrinter.build();

            System.out.println("\n");

            if(successfulPaths.size() > 0) {
                System.out.println("SUCCESSFUL PATHS:");
            } else {
                System.out.println("NO SUCCESSFUL PATHS");
            }


            for(String path: successfulPaths) {
                System.out.println(path);
            }


        } catch (CellNotFound e) {
            System.out.println("Cell not found");
        } catch (IncorrectRegistrySettings e) {
            System.out.println("Internal registry error");
        } catch (IncorrectConsoleInput e) {
            System.out.println("Incorrect console input");
        }
    }

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        boolean quit = false;
        String input;

        Main main = new Main();
        do {
            try {
                input = reader.readLine();

                if (EXIT_COMMAND.equalsIgnoreCase(input)) {
                    quit = true;
                } else {
                    main.process(input);
                }

            } catch (IOException e) {
                System.out.println("Incorrect console input");
            }
        } while (!quit);

    }

}
