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
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final String EXIT_COMMAND = "quit";

    Set<Piece> pieces;

    List<String> successfulPaths = new ArrayList<>();

    Set<Piece> tracePieces = new HashSet<>();

    Set<Cell> targetCells = new HashSet<>();

    public Main() {
    }

    private void findAllTargets() {
        for (Piece piece : pieces) {
            if (piece instanceof ServiceTargetPiece) {
                targetCells.add(piece.getCurrentPosition());
            }
        }
    }

    void printResults() {
        System.out.println("\n");

        if (successfulPaths.size() > 0) {
            System.out.println("SUCCESSFUL PATHS:");
        } else {
            System.out.println("NO SUCCESSFUL PATHS");
        }
        List<String> sortedList = successfulPaths.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        for (String path : sortedList) {
            System.out.println(path);
        }
        successfulPaths.clear();
    }

    private void processCalculatedData(Piece piece) {
        List<Step> successTails = StepProcessor.getSuccessStepsTails();
        for (Step traceStep : successTails) {

            StringBuilder path = new StringBuilder();

            Step subTraceStep = traceStep;
            do {
                try {
                    Piece newPiece = new TraceDebugPiece(new Cell(subTraceStep.getX(),
                            subTraceStep.getY()), subTraceStep.getLevel());

                    String pathDelimiter = path.length() > 0 ? " -> " : "";
                    path.insert(0, piece.getPieceId() +
                            CellLabel.getByX(newPiece.getCurrentPosition().getX()).getLabel() +
                            (newPiece.getCurrentPosition().getY() + 1) + pathDelimiter);

                    tracePieces.add(newPiece);

                } catch (CellLabelNotFoundException | CellNotFound e) {
                    // TODO write to log
                }

                subTraceStep = subTraceStep.getParent();

            } while (subTraceStep != null);

            successfulPaths.add(path.toString());
        }

        StepProcessor.clearSuccessStepsTails();
    }

    public void process(String input) {

        try {
            pieces = ConsoleInputReader.parseInput(input);
            tracePieces = new HashSet<>();
            targetCells = new HashSet<>();

            findAllTargets();

            for (Piece piece : pieces) {
                Cell currentPosition = piece.getCurrentPosition();


                StepProcessor.calculate(piece, new Step(currentPosition.getX(),
                                currentPosition.getY(), StepProcessor.START_STEP_LEVEL_ID),
                        StepProcessor.TOTAL_STEP_LEVELS, targetCells);


                processCalculatedData(piece);
            }
            pieces.addAll(tracePieces);

            printBoard();
            printResults();

        } catch (CellNotFound e) {
            System.out.println("Cell not found");
        } catch (IncorrectRegistrySettings e) {
            System.out.println("Internal registry error");
        } catch (IncorrectConsoleInput e) {
            System.out.println("Incorrect console input");
        }
    }

    private void printBoard() throws CellNotFound {
        ChessBoardPrinter chessBoardPrinter = new ChessBoardPrinter(pieces);
        System.out.println(chessBoardPrinter.build());

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
