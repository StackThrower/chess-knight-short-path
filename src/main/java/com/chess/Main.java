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
import java.util.List;
import java.util.Set;

public class Main {

    private static final String EXIT_COMMAND = "quit";

    Set<Piece> pieces;

    public Main() {
    }

    public void process(String input) {

        try {
            pieces = ConsoleInputReader.parseInput(input);

            for (Piece piece : pieces) {
                Set<Cell> traceCells = StepCalculator.calculate(piece, piece.getCurrentPosition(), 1);
                for (Cell traceCell : traceCells) {
                    pieces.add(new TraceDebugPiece(traceCell));
                }
            }


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
