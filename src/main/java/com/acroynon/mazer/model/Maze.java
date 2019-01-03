package com.acroynon.mazer.model;

import com.acroynon.mazer.core.Vector2D;

public class Maze {

	private int width;
	private int height;
	private int[][] map;

	public Maze(int width, int height){
		this.width = width;
		this.height = height;
		this.map = new int[width][height];
	}
	
	public void setValue(Vector2D position, int value){
		map[position.getX()][position.getY()] = value;
	}
	
	public int getValue(Vector2D position){
		return map[position.getX()][position.getY()];
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

}
