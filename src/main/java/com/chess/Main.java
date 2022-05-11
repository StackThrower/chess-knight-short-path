package com.chess;

import com.chess.board.CellNotFound;
import com.chess.console.ChessBoardPrinter;
import com.chess.console.ConsoleInputReader;
import com.chess.console.ConsolePieceReader;
import com.chess.console.IncorrectConsoleInput;
import com.chess.piece.Piece;
import com.chess.piece.registry.IncorrectRegistrySettings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String EXIT_COMMAND = "quit";

    List<Piece> pieces;
    public Main() { }

    public void process(String input) {

        try {
            pieces = ConsoleInputReader.parseInput(input);

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
