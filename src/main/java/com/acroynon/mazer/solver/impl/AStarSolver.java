package com.acroynon.mazer.solver.impl;

import java.util.ArrayList;
import java.util.List;

import com.acroynon.mazer.common.math.Vector2D;
import com.acroynon.mazer.common.model.Maze;
import com.acroynon.mazer.common.model.MazeBlock;
import com.acroynon.mazer.solver.common.PositionFinder;
import com.acroynon.mazer.solver.service.MazeSolver;

public class AStarSolver implements MazeSolver{

	private PositionFinder finder;
	private List<Vector2D> evaluated;
	private Vector2D start;
	private Vector2D end;
	private List<Vector2D> path;
	
	public AStarSolver(){
		finder = new PositionFinder();
		evaluated = new ArrayList<Vector2D>();
		path = new ArrayList<Vector2D>();
	}
	
	@Override
	public void solve(Maze maze){ 
		start = finder.findStartLocation(maze);
		end = finder.findEndLocation(maze);
		path.add(start);
		
		Vector2D current = start;
		
		while(!current.equals(end)){
			current = path.get(0);
			evaluated.add(current);
			List<Vector2D> validNext = getValidNeighbours(current, maze);
			// if no spaces then back track
			if(validNext.size() == 0){ path.remove(0); continue; }
			path.add(0, calculateLowestCost(validNext));
		}
		// add end point to path
		path.add(current);
		// fill in path with solve blocks
		for(Vector2D v : path){
			maze.setValue(v, MazeBlock.SOLVE);
		}
	}
	
	// get the lowest cost neighbour
	public Vector2D calculateLowestCost(List<Vector2D> list){
		int lowestCost = 0;
		Vector2D best = null;
		for(Vector2D v : list){
			int cost = calculateCost(v);
			if(best == null || cost < lowestCost){
				best = v;
				lowestCost = cost;
			}
		}
		return best;
	}
	
	// distance from start + distance to end
	public int calculateCost(Vector2D v){
		return distBetween(start, v) + distBetween(end, v);
	}
	
	// simple xDiff + yDiff (because we don't move diagonally) 
	public int distBetween(Vector2D a, Vector2D b){
		int x = Math.max(a.getX(), a.getX()) - Math.min(a.getX(), b.getX());
		int y = Math.max(a.getY(), a.getY()) - Math.min(a.getY(), b.getY());
		return y + x;
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
		// Is a wall
		if(maze.getValue(position).equals(MazeBlock.WALL)){
			return false;
		}
		// already evaluated
		if(evaluated.contains(position)){
			return false;
		}
		return true;
	}
	
}
