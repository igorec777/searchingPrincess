package utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class SearchPrincessTest
{

    @Test
    public void findPrincess1()
    {
        String filename = "input1";

        assertEquals(SearchPrincess.findPrincess(filename), 60);
    }

    @Test
    public void findPrincess2()
    {
        String filename = "input2";

        assertEquals(SearchPrincess.findPrincess(filename), 70);
    }

    @Test
    public void findPrincess3()
    {
        String filename = "input3";

        assertEquals(SearchPrincess.findPrincess(filename), 80);
    }
}