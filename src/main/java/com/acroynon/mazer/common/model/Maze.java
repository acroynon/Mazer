package com.acroynon.mazer.common.model;

import com.acroynon.mazer.common.math.Vector2D;

public class Maze {

	private int width;
	private int height;
	private MazeBlock[][] map;

	public Maze(int width, int height){
		this.width = width;
		this.height = height;
		this.map = new MazeBlock[width][height];
		setDefaultValues();
	}
	
	public void setValue(Vector2D position, MazeBlock value){
		map[position.getX()][position.getY()] = value;
	}
	
	public MazeBlock getValue(Vector2D position){
		return map[position.getX()][position.getY()];
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	private void setDefaultValues(){
		for(int x=0; x<getWidth(); x++){
			for(int y=0; y<getHeight(); y++){
				this.map[x][y] = MazeBlock.WALL;
			}
		}
	}
}
