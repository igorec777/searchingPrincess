package utils;

class Prince implements Movable
{
    int walkedTime = 0;
    int level = 0;

    Cell cell;

    Prince(Cell cell)
    {
        this.cell = cell;
    }

    @Override
    public void move(Cell cell, int steps)
    {
        this.cell.row = cell.row;
        this.cell.column = cell.column;

        walkedTime += steps * 5;
    }

    @Override
    public void fall()
    {
        level++;
        walkedTime += 5;
    }
}
