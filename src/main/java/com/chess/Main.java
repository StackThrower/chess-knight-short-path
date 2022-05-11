package com.chess;

import com.chess.board.Cell;
import com.chess.board.CellNotFound;
import com.chess.console.ChessBoardPrinter;
import com.chess.console.ConsolePiecePositionReader;
import com.chess.console.IncorrectConsoleInput;
import com.chess.piece.BishopPiece;
import com.chess.piece.KnightPiece;
import com.chess.piece.Piece;
import com.chess.piece.registry.IncorrectRegistrySettings;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public Main() {

        List<Piece> pieces = new ArrayList<>();

        try {
            pieces.add(ConsolePiecePositionReader.parseFromString("Ka3"));
            pieces.add(ConsolePiecePositionReader.parseFromString("Bc4"));

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
        Main main = new Main();
    }

}
