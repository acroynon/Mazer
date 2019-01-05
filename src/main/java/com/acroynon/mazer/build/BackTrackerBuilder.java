package com.acroynon.mazer.build;

import java.util.ArrayList;
import java.util.List;

import com.acroynon.mazer.build.service.MazeBuilder;
import com.acroynon.mazer.common.math.MathUtil;
import com.acroynon.mazer.common.math.Vector2D;
import com.acroynon.mazer.common.model.Maze;
import com.acroynon.mazer.common.model.MazeBlock;

public class BackTrackerBuilder implements MazeBuilder{

	private Maze maze;	
	private List<Vector2D> visited;
	private List<Vector2D> path;
	
	public BackTrackerBuilder(){
		visited = new ArrayList<>();
		path = new ArrayList<>();
	}
	
	@Override
	public void build(Maze maze){
		this.maze = maze;
		path.add(new Vector2D(1, 1));
		while(path.size() > 0){
			Vector2D position = path.get(0);
			visited.add(position);
			maze.setValue(position, MazeBlock.PATH);
			List<Vector2D> neighbours = getValidNeighbours(position);
			// if no neighbours, go back
			if(neighbours.size() == 0){
				path.remove(0);
			}else{
				// pick a random neighbour
				int r = MathUtil.randomIntBetween(0, neighbours.size());
				Vector2D next = neighbours.get(r);
				path.add(0, next);
			}
		}
		// find locations that could be a start or end (at top and bottom of picture)
		List<Vector2D> validStartPositions = getValidStartPositions();
		List<Vector2D> validEndPositions = getValidEndPositions();
		// pick random start and end location from lists
		maze.setValue(validStartPositions.get(MathUtil.randomIntBetween(0, validStartPositions.size())), MazeBlock.PATH);
		maze.setValue(validEndPositions.get(MathUtil.randomIntBetween(0, validEndPositions.size())), MazeBlock.PATH);
	}
	
	private List<Vector2D> getValidNeighbours(Vector2D position){
		List<Vector2D> neighbours = getNeighbours(position);
		List<Vector2D> valid = new ArrayList<>();
		for(Vector2D v : neighbours){
			// out of bounds
			if(v.getX() <= 0 || v.getX() >= maze.getHeight() - 1 ||
					v.getY() <= 0 || v.getY() >= maze.getWidth() - 1){
				continue;
			}
			if(isValid(v)){
				valid.add(v);
			}
		}
		return valid;
	}
	
	private List<Vector2D> getNeighbours(Vector2D position){
		List<Vector2D> neighbours = new ArrayList<>();
		neighbours.add(position.add(new Vector2D(1, 0)));
		neighbours.add(position.add(new Vector2D(-1, 0)));
		neighbours.add(position.add(new Vector2D(0, 1)));
		neighbours.add(position.add(new Vector2D(0, -1)));
		return neighbours;
	}	
	
	private boolean isValid(Vector2D position){
		if(isVisited(position)){
			return false;
		}
		Vector2D topLeft = new Vector2D(position.getX() - 1, position.getY() - 1);
		Vector2D topMid = new Vector2D(position.getX(), position.getY() - 1);
		Vector2D topRight = new Vector2D(position.getX() + 1, position.getY() - 1);
		
		Vector2D midLeft = new Vector2D(position.getX() - 1, position.getY());
		Vector2D midRight = new Vector2D(position.getX() + 1, position.getY());
		
		Vector2D botLeft = new Vector2D(position.getX() - 1, position.getY() + 1);
		Vector2D botMid = new Vector2D(position.getX(), position.getY() + 1);
		Vector2D botRight = new Vector2D(position.getX() + 1, position.getY() + 1);
		//top left corner
		if(isVisited(topLeft, midLeft, topMid)){
			return false;
		}
		//top right corner
		if(isVisited(topRight, midRight, topMid)){
			return false;
		}
		//bottom left corner
		if(isVisited(botLeft, midLeft, botMid)){
			return false;
		}
		//bottom right corner
		if(isVisited(botRight, midRight, botMid)){
			return false;
		}
		
		// / (divisble squares visited)
		if(isVisited(topRight, botLeft)){
			return false;
		}
		// \ (divisble squares visited)
		if(isVisited(topLeft, botRight)){
			return false;
		}
		// - (divisble squares visited)
		if(isVisited(midLeft, midRight)){
			return false;
		}
		// | (divisble squares visited)
		if(isVisited(topMid, botMid)){
			return false;
		}
		
		return true;
	}
	
	private boolean isVisited(Vector2D... list){
		boolean result = true;
		for(Vector2D v : list){
			if(!isVisited(v)){
				return false;
			}
		}
		return result;
	}
	
	private boolean isVisited(Vector2D position){
		if(position.getX() < 0 || position.getY() < 0 ||
				position.getX() > maze.getWidth() - 1 || position.getY() > maze.getHeight() - 1){
			return true;
		}
		return visited.contains(position);
	}

	public List<Vector2D> getValidStartPositions(){
		List<Vector2D> valid = new ArrayList<>();
		for(int x = 1; x<maze.getWidth() - 2; x++){
			if(maze.getValue(new Vector2D(x, 1)) == MazeBlock.PATH){
				valid.add(new Vector2D(x, 0));
			}
		}
		return valid;
	}
	
	public List<Vector2D> getValidEndPositions(){
		List<Vector2D> valid = new ArrayList<>();
		for(int x = 1; x<maze.getWidth() - 2; x++){
			if(maze.getValue(new Vector2D(x, maze.getHeight() - 2)) == MazeBlock.PATH){
				valid.add(new Vector2D(x, maze.getHeight() - 1));
			}
		}
		return valid;
	}
}
