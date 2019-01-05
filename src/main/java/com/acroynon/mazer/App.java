package com.acroynon.mazer;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.acroynon.mazer.build.BackTrackerBuilder;
import com.acroynon.mazer.build.service.MazeBuilder;
import com.acroynon.mazer.common.file.ImageCreator;
import com.acroynon.mazer.common.file.ImageLoader;
import com.acroynon.mazer.common.file.MazeLoader;
import com.acroynon.mazer.common.file.PropertyManager;
import com.acroynon.mazer.common.model.Maze;
import com.acroynon.mazer.solver.RandomWalkSolver;
import com.acroynon.mazer.solver.TurnLeftSolver;
import com.acroynon.mazer.solver.service.MazeSolver;

public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException{

    	PropertyManager propManager = new PropertyManager("./application.properties");
        
        int mazeWidth = propManager.getIntOrDefault("maze.width", 10);
        int mazeHeight = propManager.getIntOrDefault("maze.height", 10);
        String builderAlgorithm = propManager.getStringOrDefault("builder.algorithm", "");
        String builderFilename = propManager.getStringOrDefault("builder.filename", "maze");
        String solverAlgorithm = propManager.getStringOrDefault("solver.algorithm", "");
        String solverFileName = propManager.getStringOrDefault("solver.filename", "solved-maze");
        
    	MazeBuilder builder = getBuilder(builderAlgorithm);
        ImageCreator imageCreator = new ImageCreator();
        MazeLoader loader = new MazeLoader(new ImageLoader());
        
    	Maze maze = new Maze(mazeWidth, mazeHeight);
    	
        builder.build(maze);
        
        imageCreator.saveFile(maze, builderFilename);     
        
        MazeSolver solver = getSolver(solverAlgorithm);
        Maze loadedMaze = loader.loadMaze(builderFilename);
        solver.solve(loadedMaze);
        imageCreator.saveFile(loadedMaze, solverFileName);
    }
    
    public static MazeBuilder getBuilder(String algorithm){
    	if(algorithm == null){ algorithm = ""; }
    	MazeBuilder builder;
    	switch(algorithm.toLowerCase()){
    		case "backtracker":
    			builder = new BackTrackerBuilder();
    			break;
    		default:
    			builder = new BackTrackerBuilder();
    	}
    	return builder;
    }
    
    public static MazeSolver getSolver(String algorithm){
    	if(algorithm == null){ algorithm = ""; }
    	MazeSolver solver;
    	switch(algorithm.toLowerCase()){
    		case "turnleft":
    			solver = new TurnLeftSolver();
    			break;
    		case "randomwalker":
    			solver = new RandomWalkSolver();
    			break;
    		default:
    			solver = new TurnLeftSolver();
    	}
    	return solver;
    }
    
    
}
