package com.acroynon.mazer.solver.common;

import com.acroynon.mazer.common.math.Vector2D;
import com.acroynon.mazer.common.model.Maze;
import com.acroynon.mazer.common.model.MazeBlock;

public class PositionFinder {

	public Vector2D findStartLocation(Maze maze){
		return findPathInRow(maze, 0);
	}
	
	public Vector2D findEndLocation(Maze maze){
		return findPathInRow(maze, maze.getHeight()-1);
	}
	
	private Vector2D findPathInRow(Maze maze, int yCoord){
		for(int x=1; x<maze.getWidth()-1; x++){
			if(maze.getValue(new Vector2D(x, yCoord)).equals(MazeBlock.PATH)){
				return new Vector2D(x, yCoord);
			}
		}
		throw new IllegalArgumentException("Maze doesn't have any path in Y coord: " + yCoord);
	}
	
}
