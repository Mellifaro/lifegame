package com.epam.lifegame;

import java.util.HashSet;
import java.util.Set;

public class Game {
    private final int fieldHeight;
    private final int fieldWidth;
    private Cell[][] gameField;
    private Set<Cell> liveCells = new HashSet<>();
    private CellService cellService;

    public Game(int height, int width, CellService cellService) {
        this.fieldHeight = height;
        this.fieldWidth = width;
        this.cellService = cellService;
        gameField = prepareGameField();
    }

    private Cell[][] prepareGameField(){
        Cell[][] gameField = new Cell[fieldHeight][fieldWidth];
        for(int i = 0; i < fieldHeight; i++){
            for(int j = 0; j < fieldWidth; j++){
                gameField[i][j] = new Cell(i, j);//row i, column j
            }
        }
        createNeighboursForCells(gameField);
        return gameField;
    }

    private void createNeighboursForCells(Cell[][] gameField) {
        for (Cell[] row : gameField) {
            for (Cell cell : row) {
                createNeighboursForOneCells(cell, gameField);
            }
        }
    }

    private void createNeighboursForOneCells(Cell cell, Cell[][] gameField) {
        Set<Cell> neighbours = new HashSet<>();
        int minRow = (cell.getRow() - 1) > 0 ? (cell.getRow() - 1) : 0;
        int maxRow = (cell.getRow() + 1) <= (fieldHeight - 1) ? (cell.getRow() + 1) : (fieldHeight - 1) ;
        int minColumn = (cell.getColumn() - 1) > 0 ? (cell.getColumn() - 1) : 0;
        int maxColumn = (cell.getColumn() + 1) <= (fieldWidth - 1) ? (cell.getColumn() + 1) : (fieldWidth - 1);

        for(int i = minRow; i <= maxRow; i++){
            for(int j = minColumn; j <= maxColumn; j++){
                if (cell.getRow() != i || cell.getColumn() != j) {
                    neighbours.add(gameField[i][j]);
                }
            }
        }
        cell.setNeighbours(neighbours);
    }

    void start() throws InterruptedException {
        while (true){
            makeIteration();
            Thread.sleep(1000);
        }
    }

    public void makeIteration(){
        liveCells.forEach(cellService::analyzeCell);
        rebuildField();
        printField();
    }

    public void initFieldWithLiveCells(Cell ... cells){
        for(Cell cell : cells){
            int row = cell.getRow();
            int column = cell.getColumn();
            Cell foundCell = gameField[row][column];

            foundCell.setAlive(true);
            foundCell.setAlreadyAnalized(false);
            liveCells.add(foundCell);
        }

    }

    private void rebuildField(){
        for(int i = 0; i < fieldHeight; i++){
            for(int j = 0; j < fieldWidth; j++){
                Cell cell = gameField[i][j];
                if(cell.isChangedNextRound()){
                    if(cell.isAlive()){
                        cell.setAlive(false);
                        liveCells.remove(cell);
                    }else{
                        cell.setAlive(true);
                        liveCells.add(cell);
                    }
                }
                cell.setChangedNextRound(false);
                cell.setAlreadyAnalized(false);
            }
        }
    }


    private void printField(){
        for (Cell[] row : gameField) {
            for (Cell cell : row) {
                String str = cell.isAlive() ? "X" : ".";
                System.out.print(str);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public Cell[][] getGameField() {
        return gameField;
    }

    public void setGameField(Cell[][] gameField) {
        this.gameField = gameField;
    }

    public Set<Cell> getLiveCells() {
        return liveCells;
    }

    public void setLiveCells(Set<Cell> liveCells) {
        this.liveCells = liveCells;
    }

    public CellService getCellService() {
        return cellService;
    }

    public void setCellService(CellService cellService) {
        this.cellService = cellService;
    }
}
