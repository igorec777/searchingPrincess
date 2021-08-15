package utils;

class Cell implements Cloneable
{
    int row;
    int column;

    Cell(int row, int column)
    {
        this.row = row;
        this.column = column;
    }

    Cell() {}

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || obj.getClass() != this.getClass() || this.hashCode() != obj.hashCode())
            return false;

        if (this == obj)
            return true;

        Cell cell = (Cell) obj;

        return row == cell.row && column == cell.column;
    }

    @Override
    public int hashCode()
    {
        int result = 17;

        result = 31 * result + row;
        result = 31 * result + column;

        return result;
    }

    @Override
    public Cell clone()
    {
        try
        {
            return (Cell) super.clone();
        }
        catch (CloneNotSupportedException ex)
        {
            System.out.println("clone not supported");

            return null;
        }
    }
}
