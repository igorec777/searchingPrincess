package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static utils.DataInitializer.Positions.AREAS_PARAMS;
import static utils.DataInitializer.Positions.MAZE_PARAMS;
import static utils.SearchPrincess.*;


public class DataInitializer
{
    static List<String> data;

    enum Positions
    {
        MAZE_PARAMS(0),
        AREAS_PARAMS(2);

        int position;

        Positions(int position) {
            this.position = position;
        }
    }

    static void initData(String filename)
    {
        readData(filename);

        initMaze();
        initLevels();
    }

    private static void readData(String filename)
    {
        Path path = Path.of("src/input/" + filename+ ".txt");

        try
        {
            data = Files.readAllLines(path);
        }
        catch (IOException e)
        {
            System.out.println("Can't read input file");
            System.exit(-1);
        }
    }

    private static void initMaze()
    {
        String[] mazeParams = data.get(MAZE_PARAMS.position).split(" ");

        levelsCount = Integer.parseInt(mazeParams[0]);
         rowsCount = Integer.parseInt(mazeParams[1]);
        columnsCount = Integer.parseInt(mazeParams[2]);

        cellSteps = new int[rowsCount][columnsCount];
    }

    private static void initLevels()
    {
        levels.clear();

        for (int k = 0; k < levelsCount; k++)
        {
            AREAS_PARAMS.position  = 2 + k * (rowsCount + 1);
            levels.add(initArea());
        }
    }

    private static String[][] initArea()
    {
        String[] line;
        String[][] area = new String[rowsCount][columnsCount];

        for (int i = 0; i < rowsCount; i++)
        {
            line = data.get(i + AREAS_PARAMS.position).split("");

            for (int j = 0; j < columnsCount; j++) {

                if (line[j].equals("1"))
                {
                    initPrince(new Cell(i, j));
                }
                else if (line[j].equals("2"))
                {
                    initPrincessPos(new Cell(i, j));
                }
                area[i][j] = line[j];
            }
        }
        return area;
    }

    private static void initPrince(Cell cell)
    {
        prince = new Prince(cell);
    }

    private static void initPrincessPos(Cell cell)
    {
        princessCell = cell;
    }
}
