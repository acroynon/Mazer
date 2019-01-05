package com.acroynon.mazer.common.file;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.acroynon.mazer.common.math.Vector2D;
import com.acroynon.mazer.common.model.Maze;
import com.acroynon.mazer.common.model.MazeBlock;

public class ImageCreator {
	
	// save a maze to a file
	public boolean saveFile(Maze maze, String filename){
		BufferedImage bi = createBufferedImage(maze);
        drawMaze(maze, bi);                
        return writeFile(bi, filename);
	}
	
	// create a buffered image the same dimensions of a maze
	private BufferedImage createBufferedImage(Maze maze){
		int width = maze.getWidth();
		int height = maze.getHeight();
		return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	
	// write buffered image to a file
	private boolean writeFile(BufferedImage bufferedImage, String filename){
		boolean result = true;
		File file = new File(filename + ".png");
		try {
			ImageIO.write(bufferedImage, "png", file);
		} catch (IOException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	// draw maze to buffered image
	private void drawMaze(Maze maze, BufferedImage bufferedImage){
		Graphics2D g = bufferedImage.createGraphics();
		for(int x=0; x<maze.getWidth(); x++){
			for(int y=0; y<maze.getHeight(); y++){
				MazeBlock val = maze.getValue(new Vector2D(x, y));
				Color c = val.color;
				g.setColor(c);
				g.fillRect(x, y, 1, 1);
			}
		}
	}
	
}
