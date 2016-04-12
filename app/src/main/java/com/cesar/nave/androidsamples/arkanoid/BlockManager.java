package com.cesar.nave.androidsamples.arkanoid;

import java.util.ArrayList;
import java.util.List;

public class BlockManager
{
    private static BlockManager instance;

    private int lines, columns;
    public List<Block> blocks;

    private BlockManager()
    {
        lines = 4;
        columns = 6;

        blocks = new ArrayList<>();

        SetupBlocks();
    }

    public static BlockManager getInstance()
    {
        if(instance == null) instance = new BlockManager();

        return instance;
    }

    public void SetupBlocks()
    {
        blocks.clear();

        for(int i = 0; i < lines; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                Block block = new Block(i, j, columns);
                blocks.add(block);
            }
        }
    }
}