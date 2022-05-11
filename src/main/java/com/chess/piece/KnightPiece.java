package com.chess.piece;

import com.chess.board.Cell;
import com.chess.board.exeption.CellNotFound;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class KnightPiece extends Piece {

    private final static String SYMBOL = "♞";

    public final static String PIECE_ID = "K";


    // This enum can be replaced by two loops. For support reason it can be improved.
    enum StepDirection {
        ONE_LEFT_THREE_TOP((byte) -1, (byte) 2, true),
        ONE_LEFT_THREE_DOWN((byte) -1, (byte) -2, true),

        THREE_LEFT_ONE_TOP((byte) -2, (byte) 1, true),
        THREE_LEFT_ONE_DOWN((byte) -2, (byte) -1, true),

        ONE_DOWN_THREE_LEFT((byte) -2, (byte) 1, true),
        ONE_DOWN_THREE_RIGHT((byte) 2, (byte) 1, true),

        THREE_DOWN_ONE_LEFT((byte) -1, (byte) 2, false),
        THREE_DOWN_ONE_RIGHT((byte) 1, (byte) 2, true),

        ONE_RIGHT_THREE_DOWN((byte) 1, (byte) -2, true),
        ONE_RIGHT_THREE_TOP((byte) 1, (byte) 2, false),

        THREE_RIGHT_ONE_DOWN((byte) 2, (byte) -1, true),
        THREE_RIGHT_ONE_TOP((byte) 2, (byte) 1, false),

        THREE_TOP_ONE_LEFT((byte) -1, (byte) 2, false),
        THREE_TOP_ONE_RIGHT((byte) 1, (byte) 2, false),

        ONE_TOP_THREE_RIGHT((byte) 2, (byte) 1, false),
        ONE_TOP_THREE_LEFT((byte) -2, (byte) 1, false);

        private byte x;

        private byte y;

        private boolean isUnique;

        StepDirection(byte x, byte y, boolean isUnique) {
            this.x = x;
            this.y = y;
            this.isUnique = isUnique;
        }

        public byte getX() {
            return x;
        }

        public byte getY() {
            return y;
        }

        public boolean isUnique() {
            return isUnique;
        }
    }


    public KnightPiece() {
        super();
    }

    public KnightPiece(Cell currentPosition) {
        super(currentPosition, SYMBOL, PIECE_ID);
    }

    @Override
    public Set<Cell> availableMoves(Cell position) {
        final byte x = position.getX();
        final byte y = position.getY();

        Set<Cell> ret = ConcurrentHashMap.newKeySet(); // used recursively

        List<StepDirection> uniqueSteps = Arrays.stream(StepDirection.values())
                .filter(StepDirection::isUnique).collect(Collectors.toList());

        for (StepDirection step : uniqueSteps) {
            byte newX = (byte) (x + step.getX());
            byte newY = (byte) (y + step.getY());

            if (Cell.isValid(newX, newY)) {
                try {
                    ret.add(new Cell(newX, newY));
                } catch (CellNotFound e) {
                    // TODO write to log e.getMessage()
                }
            }
        }

        return ret;

    }
}
