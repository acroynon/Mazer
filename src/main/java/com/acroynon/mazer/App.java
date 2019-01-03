package com.acroynon.mazer;

import com.acroynon.mazer.core.MazeBuilder;
import com.acroynon.mazer.core.MazeController;
import com.acroynon.mazer.core.MazeValidator;
import com.acroynon.mazer.io.ImageCreator;
import com.acroynon.mazer.model.Maze;

public class App 
{
    public static void main( String[] args )
    {
    	MazeValidator validator = new MazeValidator();
    	MazeBuilder builder = new MazeBuilder();
    	MazeController controller = new MazeController(validator, builder);
        
    	Maze maze = new Maze(10, 10);
    	
        controller.buildMaze(maze);
        controller.invert(maze);
        
        ImageCreator imageCreator = new ImageCreator("maze", maze);
        imageCreator.saveFile();     
        
    }
}
