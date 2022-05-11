package com.chess.piece;

import com.chess.board.Cell;

public abstract class Piece implements PieceMove {

    private Cell currentPosition;

    private String symbol;

    private String pieceId;

    public Piece() {}

    public Piece(Cell currentPosition, String symbol, String pieceId) {
        this.currentPosition = currentPosition;
        this.symbol = symbol;
        this.pieceId = pieceId;
    }

    public boolean isCurrentPosition(Cell position) {
        return currentPosition.equals(position);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPieceId() {
        return pieceId;
    }
}
