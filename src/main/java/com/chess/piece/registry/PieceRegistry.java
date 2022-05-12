package com.chess.piece.registry;

import com.chess.piece.BishopPiece;
import com.chess.piece.KnightPiece;
import com.chess.piece.Piece;
import com.chess.piece.service.TargetPiece;
import com.chess.piece.service.TraceDebugPiece;

public enum PieceRegistry {

    Bishop(BishopPiece.class),
    Knight(KnightPiece.class),

    // Service pieces
    Target(TargetPiece.class),
    Trace(TraceDebugPiece.class);

    private Class<Piece> piece;

    // FIXME this should be fixed
    @SuppressWarnings("rawtypes")
    PieceRegistry(Class piece) {
        this.piece = piece;
    }

    public Class<Piece> getPiece() {
        return piece;
    }
}
