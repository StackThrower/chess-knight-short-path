package com.chess.piece;

import com.chess.board.Cell;

public abstract class Piece implements PieceMove {

    private Cell position;

    private String symbol;

    private String pieceId;

    public Piece() {}

    public Piece(Cell position, String symbol, String pieceId) {
        this.position = position;
        this.symbol = symbol;
        this.pieceId = pieceId;
    }

    public Cell getCurrentPosition() {
        return position;
    }

    public boolean isCurrentPosition(Cell position) {
        return this.position.equals(position);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPieceId() {
        return pieceId;
    }

    public Cell getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object object) {
        Piece piece = (Piece) object;

        return this.position.equals(piece.getPosition());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + position.getX() + position.getY();
        return hash;
    }

}
