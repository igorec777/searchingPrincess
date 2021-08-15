package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.DataInitializer.initData;
import static utils.OptimalWayBuilder.paveShortestWay;


public class SearchPrincess
{
    static final List<String[][]> levels;

    static Prince prince;

    static Cell princessCell;

    static int[][] cellSteps;

    static int levelsCount;
    static int rowsCount;
    static int columnsCount;

    static
    {
        levels = new ArrayList<>(levelsCount);
    }

    private SearchPrincess() {}

    public static int findPrincess(String filename)
    {
        initData(filename);

        startTrip();

        return prince.walkedTime;
    }

    private static void startTrip()
    {
        while (!isFoundPrincess())
        {
            if (isInLowestLevel())
            {
                moveToPrincess();
            }
            else
            {
                moveToFreeCell();

                prince.fall();
            }
        }
    }

    private static boolean isFoundPrincess()
    {
        if (prince.level == levelsCount - 1)
            return prince.cell.equals(princessCell);

        return false;
    }

    private static boolean isInLowestLevel()
    {
        return prince.level  == levelsCount - 1;
    }

    private static void moveToPrincess()
    {
        resetSteps();
        paveShortestWay(prince.cell, princessCell);
        prince.move(princessCell, getSteps(princessCell));
    }

    private static void moveToFreeCell()
    {
        Cell dest = new Cell(0, 0);
        Cell minCell = dest.clone();

        int minSteps = Integer.MAX_VALUE;

        resetSteps();

        for (int i = 0; i < rowsCount; i++)
        {
            for (int j = 0; j < columnsCount; j++)
            {
                dest.row = i;
                dest.column = j;

                if (isFreeCell(dest) && isCanFall(dest))
                {
                    if (getSteps(dest) == Integer.MAX_VALUE)
                        paveShortestWay(prince.cell, dest);

                    if (getSteps(dest) < minSteps)
                    {
                        minSteps = getSteps(dest);
                        minCell = dest.clone();
                    }
                }
            }
        }
        prince.move(minCell, minSteps);
    }

    private static void resetSteps()
    {
        for (int i = 0; i < rowsCount; i++)
        {
            Arrays.fill(cellSteps[i], Integer.MAX_VALUE);
        }
        cellSteps[prince.cell.row][prince.cell.column] = 0;
    }

    static void setSteps(Cell cell, int steps)
    {
        cellSteps[cell.row][cell.column] = steps;
    }

    static int getSteps(Cell dest)
    {
        return cellSteps[dest.row][dest.column];
    }

    private static boolean isCanFall(Cell cell)
    {
        String cellToFall;

        cellToFall = levels.get(prince.level + 1)[cell.row][cell.column];

        return cellToFall.equals(".") || cellToFall.equals("2");
    }

    static boolean isFreeCell(Cell cell)
    {
        String cellToMove;

        try
        {
            cellToMove = levels.get(prince.level)[cell.row][cell.column];
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            return false;
        }
        return cellToMove.equals(".") || cellToMove.equals("1") || cellToMove.equals("2");
    }
}