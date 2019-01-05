package com.acroynon.mazer.common.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	// load a bufferedimage from an image file
	public BufferedImage loadImage(String filename){
		BufferedImage image;
		try {
			image = ImageIO.read(new File(filename + ".png"));
			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
