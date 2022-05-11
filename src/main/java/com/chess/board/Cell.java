package com.chess.board;

public class Cell {

    private CellLabel label;

    private byte x;
    private byte y;

    public Cell(String labelStr, byte y) throws CellNotFound {

        try {
            label = CellLabel.getByLabel(labelStr);
            this.x = label.getX();

            if (this.x >= BoardConfig.MAX_X_CELLS || y >= BoardConfig.MAX_Y_CELLS)
                throw new CellNotFound();
            this.y = y;

        } catch (CellLabelNotFoundException e) {
            throw new CellNotFound();
        }
    }


    public Cell(byte x, byte y) throws CellNotFound {
        if (x >= BoardConfig.MAX_X_CELLS || y >= BoardConfig.MAX_Y_CELLS)
            throw new CellNotFound();

        this.x = x;
        this.y = y;

        try {
            label = CellLabel.getByX(x);
        } catch (CellLabelNotFoundException e) {
            throw new CellNotFound();
        }
    }

    @Override
    public boolean equals(Object object) {
        Cell position = (Cell) object;

        return this.x == position.x && this.y == position.y;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + x + y;
        return hash;
    }
}
