package com.epam.lifegame;

import java.util.Set;

public final class CellService {

    public Cell analyzeCell(Cell cell){
        if(cell == null){
            throw new IllegalArgumentException("Cell can't be null");
        }
        if(cell.isAlreadyAnalized()){
            return cell;
        }
        Set<Cell> neighbours = cell.getNeighbours();
        int liveCellsAround = (int)neighbours.stream().filter(Cell::isAlive).count();

        if(cell.isAlive()){
            switch (liveCellsAround){
                case 2:
                case 3:
                    cell.setChangedNextRound(false);
                    break;
                default:
                    cell.setChangedNextRound(true);
                    break;
            }
            cell.setAlreadyAnalized(true);
            neighbours.forEach(this::analyzeCell);
        }else{
            switch (liveCellsAround){
                case 3:
                    cell.setChangedNextRound(true);
                    break;
            }
            cell.setAlreadyAnalized(true);
        }
        return cell;
    }
}
