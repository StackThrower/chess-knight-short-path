package com.chess;

import com.chess.board.Cell;
import com.chess.board.exeption.CellNotFound;
import com.chess.console.ChessBoardPrinter;
import com.chess.console.ConsoleInputReader;
import com.chess.console.exception.IncorrectConsoleInput;
import com.chess.piece.Piece;
import com.chess.piece.registry.exception.IncorrectRegistrySettings;
import com.chess.piece.service.TraceDebugPiece;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    private static final String EXIT_COMMAND = "quit";

    Set<Piece> pieces;

    public Main() {
    }

    public void process(String input) {

        try {
            pieces = ConsoleInputReader.parseInput(input);

            Set<Piece> tracePiece = new HashSet<>();
            for (Piece piece : pieces) {
                Cell currentPosition = piece.getCurrentPosition();
                Set<Step> traceSteps = StepCalculator.calculate(piece, new Step(currentPosition.getX(), currentPosition.getY(), 1), StepCalculator.DEFAULT_STEP_CALCULATOR_LEVEL);
                for (Step traceStep : traceSteps) {
                    Piece newPiece = (new TraceDebugPiece(new Cell(traceStep.getX(), traceStep.getY()), traceStep.getLevel()));
                    tracePiece.add(newPiece);
                }
            }
            pieces.addAll(tracePiece);

            ChessBoardPrinter chessBoardPrinter = new ChessBoardPrinter(pieces);
            chessBoardPrinter.build();
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
