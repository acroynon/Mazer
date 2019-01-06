package com.acroynon.mazer.solver.file;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.acroynon.mazer.common.math.Vector2D;
import com.acroynon.mazer.common.model.Maze;
import com.acroynon.mazer.common.model.MazeBlock;

public class MazeLoader {

	private ImageLoader graphicsLoader;
	
	public MazeLoader(ImageLoader graphicsLoader){
		this.graphicsLoader = graphicsLoader;
	}
	
	// create a maze from a buggered image
	public Maze loadMaze(String filename){
		BufferedImage bi = graphicsLoader.loadImage(filename);
		int width = bi.getWidth();
		int height = bi.getHeight();
		Maze maze = new Maze(width, height);
		for(int x=0; x<width; x++){
			for(int y=0; y<height; y++){
				Color c = new Color(bi.getRGB(x,y));
				maze.setValue(new Vector2D(x,y), MazeBlock.findByColor(c));
			}
		}
		return maze;
	}
	
}
