package com.acroynon.mazer.solver;

import java.util.ArrayList;
import java.util.List;

import com.acroynon.mazer.common.math.MathUtil;
import com.acroynon.mazer.common.math.Vector2D;
import com.acroynon.mazer.common.model.Maze;
import com.acroynon.mazer.common.model.MazeBlock;
import com.acroynon.mazer.solver.service.MazeSolver;

public class RandomWalkSolver implements MazeSolver{

	private PositionFinder finder;
	
	public RandomWalkSolver(){
		finder = new PositionFinder();
	}
	
	@Override
	public void solve(Maze maze){
		Vector2D startLocation = finder.findStartLocation(maze);
		Vector2D endLocation = finder.findEndLocation(maze);
		Vector2D currentLocation = startLocation;
		// mark start location
		maze.setValue(currentLocation, MazeBlock.SOLVE);
		// move down
		currentLocation = currentLocation.add(new Vector2D(0, 1));
		// random walk
		while(!currentLocation.equals(endLocation)){
			maze.setValue(currentLocation, MazeBlock.SOLVE);
			List<Vector2D> validLocations = getValidNeighbours(currentLocation, maze);
			currentLocation = validLocations.get(MathUtil.randomIntBetween(0,  validLocations.size()));
		}
		
		// mark end location
		maze.setValue(currentLocation, MazeBlock.SOLVE);
		
	}
	
	public List<Vector2D> getNeighbours(Vector2D position, Maze maze){
		List<Vector2D> neighbours = new ArrayList<Vector2D>();
		neighbours.add(position.add(new Vector2D(1, 0)));
		neighbours.add(position.add(new Vector2D(-1, 0)));
		neighbours.add(position.add(new Vector2D(0, 1)));
		neighbours.add(position.add(new Vector2D(0, -1)));
		return neighbours;
	}
	
	public List<Vector2D> getValidNeighbours(Vector2D position, Maze maze){
		List<Vector2D> neighbours = getNeighbours(position, maze);
		List<Vector2D> valid = new ArrayList<Vector2D>();
		for(Vector2D v : neighbours){
			if(isValid(v, maze)){
				valid.add(v);
			}
		}
		return valid;
	}
	
	public boolean isValid(Vector2D position, Maze maze){
		// out of bounds
		if(position.getX() <= 0 || position.getX() >= maze.getWidth() ||
				position.getY() <= 0 || position.getY() >= maze.getHeight()){
			return false;
		}
		// is a wall
		if(maze.getValue(position) == MazeBlock.WALL){
			return false;
		}
		return true;
	}
	
}
