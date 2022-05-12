package com.chess.console;

import com.chess.board.Cell;
import com.chess.board.exeption.CellNotFound;
import com.chess.piece.Piece;

import java.util.Set;

public class ChessBoardPrinter {

    Set<Piece> pieces;

    public ChessBoardPrinter(Set<Piece> pieces) {
        this.pieces = pieces;
    }

    public String build() throws CellNotFound {

        StringBuilder sb = new StringBuilder();

        sb.append(buildBoardLabels());
        sb.append(buildBoardRowLine());
        for (byte y = 7; y >= 0; y--) {
            sb.append(buildRowNumber(y));
            for (byte x = 0; x <= 7; x++) {
                sb.append(printCell(x % 2 != 0, x, y));
            }
            sb.append("\n");
            sb.append(buildBoardRowLine());
        }

        return sb.toString();
    }

    String buildBoardLabels() {
        return "     A   B   C   D   E   F   G   H\n";
    }

    String buildBoardRowLine() {
        return "---|---|---|---|---|---|---|---|---|-\n";
    }

    String buildRowNumber(byte x) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(x + 1);
        sb.append(" |");
        return sb.toString();

    }

    String printCell(boolean startsWithBlack, byte x, byte y) throws CellNotFound {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");

        boolean isShapeFilled = false;

        for (Piece piece : pieces)
            if (piece.isCurrentPosition(new Cell(x, y))) {
                isShapeFilled = true;
                sb.append(piece.getSymbol());
                break;
            }

        if (!isShapeFilled) {
            if (y % 2 == 0)
                sb.append(startsWithBlack ? "x" : " ");
            else
                sb.append(startsWithBlack ? " " : "x");
        }
        sb.append(" |");

        return sb.toString();
    }


}
