package com.chess.piece;

import com.chess.board.Cell;
import com.chess.board.exeption.CellNotFound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BishopPiece extends Piece {

    // This enum can be replaced by two loops. For support reason it can be improved.
    enum StepDirection {
        ONE_LEFT_THREE_TOP((byte) -1, (byte) -3, true),
        ONE_LEFT_THREE_DOWN((byte) -1, (byte) 3, true),

        THREE_LEFT_ONE_TOP((byte) -3, (byte) -1, true),
        THREE_LEFT_ONE_DOWN((byte) -3, (byte) 1, true),

        ONE_DOWN_THREE_LEFT((byte) -3, (byte) -1, true),
        ONE_DOWN_THREE_RIGHT((byte) 3, (byte) -1, true),

        THREE_DOWN_ONE_LEFT((byte) -1, (byte) -3, false),
        THREE_DOWN_ONE_RIGHT((byte) 1, (byte) -3, true),

        ONE_RIGHT_THREE_DOWN((byte) 1, (byte) 3, true),
        ONE_RIGHT_THREE_TOP((byte) 1, (byte) -3, false),

        THREE_RIGHT_ONE_DOWN((byte) 3, (byte) 1, true),
        THREE_RIGHT_ONE_TOP((byte) 3, (byte) -1, false),

        THREE_TOP_ONE_LEFT((byte) -1, (byte) -3, false),
        THREE_TOP_ONE_RIGHT((byte) 1, (byte) -3, false),

        ONE_TOP_THREE_RIGHT((byte) 3, (byte) -1, false),
        ONE_TOP_THREE_LEFT((byte) -3, (byte) -1, false);

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
            return x;
        }

        public boolean isUnique() {
            return isUnique;
        }
    }


    private final static String SYMBOL = "♗";
    public final static String PIECE_ID = "B";

    public BishopPiece() {
        super();
    }

    public BishopPiece(Cell currentPosition) {
        super(currentPosition, SYMBOL, PIECE_ID);
    }

    @Override
    public List<Cell> availableMoves(Cell position) {

        final byte x = position.getX();
        final byte y = position.getY();

        List<Cell> ret = new ArrayList<>();

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
