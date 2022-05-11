package com.chess.piece.registry;

import com.chess.piece.BishopPiece;
import com.chess.piece.KnightPiece;
import com.chess.piece.Piece;
import com.chess.piece.service.TargetPiece;

public enum PieceRegistry {

    Bishop(BishopPiece.class),
    Knight(KnightPiece.class),
    Target(TargetPiece.class);

    private Class<Piece> piece;

    PieceRegistry(Class piece) {
        this.piece = piece;
    }

    public Class<Piece> getPiece() {
        return piece;
    }
}
