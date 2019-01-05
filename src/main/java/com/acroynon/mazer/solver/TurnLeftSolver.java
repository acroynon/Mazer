package com.acroynon.mazer.solver;

import java.util.ArrayList;
import java.util.List;

import com.acroynon.mazer.common.math.Vector2D;
import com.acroynon.mazer.common.model.Maze;
import com.acroynon.mazer.common.model.MazeBlock;
import com.acroynon.mazer.solver.service.MazeSolver;

public class TurnLeftSolver implements MazeSolver{
	
	private PositionFinder positionFinder;
	private List<Vector2D> path;
	
	public TurnLeftSolver(){
		positionFinder = new PositionFinder();
		path = new ArrayList<Vector2D>();
	}
	
	@Override
	public void solve(Maze maze){
		Vector2D startLocation = positionFinder.findStartLocation(maze);
		Vector2D endLocation = positionFinder.findEndLocation(maze);
		
		// add start location to path (and face down)
		Vector2D currentPosition = startLocation;
		Vector2D currentDirection = new Vector2D(0, 1);
		path.add(currentPosition);
	
		while(!currentPosition.equals(endLocation)){
			currentPosition = path.get(0);
			maze.setValue(currentPosition, MazeBlock.SOLVE);
			// if no valid spaces around currentlocation then go back
			if(getValidNeighbours(currentPosition, maze).size() == 0){
				path.remove(0);
				continue;
			}
			Vector2D nextLocation = currentPosition.add(currentDirection);
			if(nextLocation.getX() <= 0 || nextLocation.getY() <= 0 || maze.getValue(nextLocation) != MazeBlock.PATH){
				// turn left
				int newX = currentDirection.getX()!=0?0:currentDirection.getY();
				int newY = currentDirection.getY()!=0?0:currentDirection.getX()*-1;
				currentDirection.setX(newX);
				currentDirection.setY(newY);
			}else{
				path.add(0, nextLocation);
				currentPosition = nextLocation;
			}
		}	
		// mark end location
		maze.setValue(currentPosition, MazeBlock.SOLVE);
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
		// not a path sqaure (e.g. a wall or already been there)
		if(maze.getValue(position) != MazeBlock.PATH){
			return false;
		}
		return true;
	}
	
}
