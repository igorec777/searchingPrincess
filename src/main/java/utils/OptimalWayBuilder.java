package utils;

import java.util.ArrayList;
import java.util.List;

import static utils.SearchPrincess.*;

class OptimalWayBuilder
{
    static void paveShortestWay(Cell source, Cell dest)
    {
        List<Cell> explored = new ArrayList<>();
        List<Cell> reachable = new ArrayList<>();

        reachable.add(source.clone());

        Cell currentCell;

        while (reachable.size() > 0)
        {
            currentCell = chooseUnexploredCell(reachable);

            if (currentCell.equals(dest))
            {
                return;
            }

            reachable.remove(currentCell);
            explored.add(currentCell);

            List<Cell> adjacentCells = getAdjacentCells(currentCell, explored);

            for (Cell adjacent: adjacentCells)
            {
                if (!reachable.contains(adjacent))
                {
                    reachable.add(adjacent);
                }

                int adjacentSteps = getSteps(adjacent);
                int currCellSteps = getSteps(currentCell);

                if (currCellSteps + 1 < adjacentSteps)
                {
                    setSteps(adjacent, currCellSteps + 1);
                }
            }
        }
    }

    private static Cell chooseUnexploredCell(List<Cell> reachable)
    {
        Cell bestCell = null;

        for (Cell r: reachable)
        {
            if (bestCell == null || getSteps(bestCell) > getSteps(r))
                bestCell = r.clone();
        }
        return bestCell;
    }

    private static List<Cell> getAdjacentCells(Cell source, List<Cell> explored)
    {
        List<Cell> adjacents = new ArrayList<>();

        Cell cell = new Cell();

        cell.row = source.row - 1;
        cell.column = source.column;

        if (isFreeCell(cell) && !explored.contains(cell))
            adjacents.add(cell.clone());

        cell.row = source.row + 1;

        if (isFreeCell(cell) && !explored.contains(cell))
            adjacents.add(cell.clone());

        cell.row = source.row;
        cell.column = source.column - 1;

        if (isFreeCell(cell) && !explored.contains(cell))
            adjacents.add(cell.clone());

        cell.column = source.column + 1;

        if (isFreeCell(cell) && !explored.contains(cell))
            adjacents.add(cell.clone());

        return adjacents;
    }
}
