package com.epam.lifegame;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CellService.class)
public class GameServiceTest {
    private final CellService cellService = PowerMockito.mock(CellService.class);
    private Game game = new Game(3, 3, cellService);

    @Before
    public void init(){
        game = new Game(3, 3, cellService);
    }

    @Test
    public void testInitFieldWithLiveCells(){
        boolean isCellAliveBeforeInit = game.getGameField()[1][1].isAlive();
        game.initFieldWithLiveCells(new Cell(1, 1));
        boolean  isCellAliveAfterInit = game.getGameField()[1][1].isAlive();
        assertNotEquals(isCellAliveBeforeInit, isCellAliveAfterInit);
    }

    @Test
    public void testCellServiceAnalyze(){
        when(cellService.analyzeCell(game.getGameField()[1][1])).thenCallRealMethod();

        game.initFieldWithLiveCells(new Cell(1, 1));
        Cell cell = game.getGameField()[1][1];
        cellService.analyzeCell(cell);

        verify(cellService, atLeast(1)).analyzeCell(cell);
    }

    @Test
    public void testNexIterationWhenNeighboursAreDead(){
        when(cellService.analyzeCell(any())).thenCallRealMethod();
        game.initFieldWithLiveCells(new Cell(1, 1));
        assertTrue(game.getGameField()[1][1].isAlive());
        game.makeIteration();

        for (Cell[] row : game.getGameField()) {
            for (Cell cell : row) {
                assertFalse(cell.isAlive());
            }
        }
    }

    @Test
    public void testNextIterationWhenOnlyOneNeighbourAlive(){
        when(cellService.analyzeCell(any())).thenCallRealMethod();
        game.initFieldWithLiveCells(new Cell(1, 1), new Cell(1, 2));

        game.makeIteration();

        for (Cell[] row : game.getGameField()) {
            for (Cell cell : row) {
                if(cell.isAlive()){
                    assertFalse(cell.isAlive());
                }
            }
        }
    }

    @Test
    public void testNextIterationWhenTwoNeighboursAreAlive(){
        when(cellService.analyzeCell(any())).thenCallRealMethod();
        game.initFieldWithLiveCells(new Cell(1, 1), new Cell(1, 2), new Cell(1, 0));

        game.makeIteration();

        int liveCells = 0;
        for (Cell[] row : game.getGameField()) {
            for (Cell cell : row) {
                if(cell.isAlive()){
                    if(cell.isAlive()){
                        liveCells++;
                    }
                }
            }
        }
        assertEquals(3, liveCells);
    }

}
