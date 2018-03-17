package com.epam.lifegame;

/**
 * Created by Viktor_Skapoushchenk on 3/16/2018.
 */
public class Main {
    private static final int FIELD_HEIGHT = 15;
    private static final int FIELD_WIDTH = 40;

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game(FIELD_HEIGHT, FIELD_WIDTH, new CellService());
        game.initFieldWithLiveCells(new Cell(4, 4),
                new Cell(4, 5),
                new Cell(3, 4),
                new Cell(3, 7),
                new Cell(3, 8),
                new Cell(3, 9),
                new Cell(7, 6),
                new Cell(8, 5),
                new Cell(8, 4),
                new Cell(8, 6),
                new Cell(9, 3),
                new Cell(9, 4),
                new Cell(12, 11),
                new Cell(11, 12),
                new Cell(10, 12),
                new Cell(12, 13),
                new Cell(9, 14));
        game.start();
    }
}
