package com.chess.board;

import com.chess.board.exeption.CellLabelNotFoundException;

import java.util.Arrays;

public enum CellLabel {

    A("A", (byte)0),
    B("B", (byte)1),
    C("C", (byte)2),
    D("D", (byte)3),
    E("E", (byte)4),
    F("F", (byte)5),
    G("G", (byte)6),
    H("H", (byte)7);

    private final String label;
    private final byte x;

    CellLabel(String label, byte x) {
        this.label = label;
        this.x = x;
    }

    public static CellLabel getByLabel(String label) throws CellLabelNotFoundException {
        return Arrays.stream(CellLabel.values())
                .filter(l -> l.label.equalsIgnoreCase(label))
                .findFirst()
                .orElseThrow(CellLabelNotFoundException::new);
    }

    public static CellLabel getByX(byte x) throws CellLabelNotFoundException {
        return Arrays.stream(CellLabel.values())
                .filter(l -> l.x == x)
                .findFirst()
                .orElseThrow(CellLabelNotFoundException::new);
    }


    public String getLabel() {
        return label;
    }

    public byte getX() {
        return x;
    }
}
