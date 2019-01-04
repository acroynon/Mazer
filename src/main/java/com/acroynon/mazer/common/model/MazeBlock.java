package com.acroynon.mazer.common.model;

import java.awt.Color;

public enum MazeBlock {
	
	WALL(Color.BLACK),
	PATH(Color.WHITE),
	SOLVE(Color.RED);
	
	public Color color;
	
	MazeBlock(Color color){
		this.color = color;
	}
	
	public static MazeBlock findByColor(Color c){
		for(MazeBlock mb : MazeBlock.values()){
			if(mb.color.equals(c)){
				return mb;
			}
		}
		throw new IllegalArgumentException("Color not found: " + c);
	}
	
}
