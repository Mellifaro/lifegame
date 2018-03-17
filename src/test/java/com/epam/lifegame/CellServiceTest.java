package com.epam.lifegame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CellServiceTest {
    private CellService cellService = new CellService();

    @Test
    public void testCellServiceWorkSuccess(){
        Game game = new Game(3, 3, cellService);
        game.initFieldWithLiveCells(new Cell(1, 1));
        Cell cell = game.getGameField()[1][1];
        boolean isAliveBeforeCalling = cell.isAlive();
        boolean isChangedNextRoundBeforeCalling = cell.isChangedNextRound();
        boolean isAlreadyAnalizedBeforeCalling = cell.isAlreadyAnalized();

        cellService.analyzeCell(cell);

        assertEquals(isAliveBeforeCalling, cell.isAlive());
        assertNotEquals(isChangedNextRoundBeforeCalling, cell.isChangedNextRound());
        assertNotEquals(isAlreadyAnalizedBeforeCalling, cell.isAlreadyAnalized());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCellServiceWorkWithNull(){
        Game game = new Game(3, 3, cellService);
        game.initFieldWithLiveCells(new Cell(1, 1));
        cellService.analyzeCell(null);
    }
}
