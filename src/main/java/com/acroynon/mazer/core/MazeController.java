package com.acroynon.mazer.core;

import com.acroynon.mazer.model.Maze;

public class MazeController {

	private MazeValidator validator;
	private MazeBuilder builder;
	
	public MazeController(MazeValidator validator, MazeBuilder builder){
		this.validator = validator;
		this.builder = builder;
	}
	
	public boolean validateMaze(Maze maze){
		return validator.isValid(maze);
	}
	
	public void buildMaze(Maze maze){
		builder.build(maze);
	}
	
	public void invert(Maze maze){
		for(int x=0; x<maze.getWidth(); x++){
			for(int y=0; y<maze.getHeight(); y++){
				Vector2D position = new Vector2D(x, y);
				int newValue = maze.getValue(position)==1?0:1;
				maze.setValue(position, newValue);
			}
		}
	}
	
}
