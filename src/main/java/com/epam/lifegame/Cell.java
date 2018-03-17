package com.epam.lifegame;

import java.util.Objects;
import java.util.Set;

public class Cell {
    private int row;
    private int column;
    private Set<Cell> neighbours;

    private boolean isAlive;
    private boolean isChangedNextRound;
    private boolean isAlreadyAnalized;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Set<Cell> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Set<Cell> neighbours) {
        this.neighbours = neighbours;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isChangedNextRound() {
        return isChangedNextRound;
    }

    public void setChangedNextRound(boolean changedNextRound) {
        isChangedNextRound = changedNextRound;
    }

    public boolean isAlreadyAnalized() {
        return isAlreadyAnalized;
    }

    public void setAlreadyAnalized(boolean alreadyAnalized) {
        isAlreadyAnalized = alreadyAnalized;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return row == cell.row &&
                column == cell.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "row=" + row +
                ", column=" + column +
                ", isAlive=" + isAlive +
                '}';
    }
}
