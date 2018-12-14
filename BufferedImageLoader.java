package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	private BufferedImage img;
	
	public BufferedImage loadImage(String path) throws IOException {
	
		img = ImageIO.read(new File(path));;
		return img;
	}
}
