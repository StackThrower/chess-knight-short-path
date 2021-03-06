package com.chess.processor;

import com.chess.board.BoardConfig;

public class Step {

    int level;

    private byte x;
    private byte y;

    private Step parent;

    public Step(byte x, byte y, int level, Step parent) {
        this.level = level;
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    public Step(byte x, byte y, int level) {
        this.level = level;
        this.x = x;
        this.y = y;
        this.parent = null;
    }

    public static boolean isValid(byte x, byte y) {
        return x >= 0 && x < BoardConfig.MAX_X_CELLS &&
                y >= 0 && y < BoardConfig.MAX_Y_CELLS;
    }

    public byte getX() {
        return x;
    }

    public byte getY() {
        return y;
    }

    @Override
    public boolean equals(Object object) {
        Step position = (Step) object;

        return this.x == position.x && this.y == position.y;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + x + y;
        return hash;
    }

    public int getLevel() {
        return level;
    }

    public Step getParent() {
        return parent;
    }
}
