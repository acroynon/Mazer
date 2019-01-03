package com.acroynon.mazer.io;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.acroynon.mazer.core.Vector2D;
import com.acroynon.mazer.model.Maze;

public class ImageCreator {

	private String filename;
	private Maze maze;
	
	public ImageCreator(String filename, Maze maze){
		 this.filename = filename;
		 this.maze = maze;
	}
	
	public boolean saveFile(){
		BufferedImage bi = createBufferedImage();
        drawMaze(bi);                
        return writeFile(bi);
	}
	
	private BufferedImage createBufferedImage(){
		int width = maze.getWidth();
		int height = maze.getHeight();
		return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	
	private boolean writeFile(BufferedImage bufferedImage){
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
	
	private void drawMaze(BufferedImage bufferedImage){
		Graphics2D g = bufferedImage.createGraphics();
		for(int x=0; x<maze.getWidth(); x++){
			for(int y=0; y<maze.getHeight(); y++){
				int val = maze.getValue(new Vector2D(x, y));
				Color c = (val==0?Color.WHITE:Color.BLACK);
				g.setColor(c);
				g.fillRect(x, y, 1, 1);
			}
		}
	}
	
}
