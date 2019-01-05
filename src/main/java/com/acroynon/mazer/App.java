package com.acroynon.mazer;

import com.acroynon.mazer.build.BackTrackerBuilder;
import com.acroynon.mazer.build.service.MazeBuilder;
import com.acroynon.mazer.common.file.ImageCreator;
import com.acroynon.mazer.common.file.ImageLoader;
import com.acroynon.mazer.common.file.MazeLoader;
import com.acroynon.mazer.common.model.Maze;
import com.acroynon.mazer.solver.RandomWalkSolver;
import com.acroynon.mazer.solver.TurnLeftSolver;
import com.acroynon.mazer.solver.service.MazeSolver;

public class App 
{
    public static void main( String[] args ){
    	MazeBuilder builder = new BackTrackerBuilder();
        ImageCreator imageCreator = new ImageCreator();
        MazeLoader loader = new MazeLoader(new ImageLoader());
        
    	Maze maze = new Maze(10, 10);
    	
        builder.build(maze);
        
        imageCreator.saveFile(maze, "maze");     
        
        
        //MazeSolver solver = new TurnLeftSolver();
        MazeSolver solver = new RandomWalkSolver();
        Maze loadedMaze = loader.loadMaze("maze.png");
        solver.solve(loadedMaze);
        imageCreator.saveFile(loadedMaze, "solved-maze");
        
        
        
        
        
    }
}
