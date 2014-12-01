/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgm;

import static java.io.StreamTokenizer.TT_EOF;
import static java.io.StreamTokenizer.TT_WORD;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

/**
 * 
 * @author zhaoshuli
 */
public class PGMImage {

	private int width = 0;
	private int height = 0;
	private byte[] pixels;

	private double maxgrey = 255;

	/**
	 * Creates an image from raw pixel data bytes (-127=black, +128=white)
	 * @param width
	 * @param height
	 * @param pixels
	 */
	public PGMImage(int width, int height, byte[] pixels) {
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}
	
	/**
	 * Creates an image from a PGM file
	 * @param file The file to read
	 * @throws FileNotFoundException
	 * @throws IOException If there is a problem while reading the file
	 * @throws UnsupportedOperationException if the image is not in the PGM P2 format
	 */
	PGMImage(File file) throws FileNotFoundException, IOException {
		StreamTokenizer s = new StreamTokenizer(new FileReader(file));
		s.commentChar('#');
		s.parseNumbers();
		s.wordChars('A', 'Z');
		s.whitespaceChars('\0', ' ');

		// Header
		// Type
		if (s.nextToken() != TT_WORD || !s.sval.equals("P2")) {
			throw new UnsupportedOperationException("Only P2 supported yet.");
		}

		// width
		s.nextToken();
		width = (int) s.nval;

		// height
		s.nextToken();
		height = (int) s.nval;

		// Maximum grey value
		s.nextToken();
		maxgrey = s.nval;

		int numPixels = width * height;
		pixels = new byte[numPixels];
		for (int i = 0; s.nextToken() != TT_EOF && i < numPixels; i++) {
			byte val = (byte) (s.nval * 255 / maxgrey);
			pixels[i] = val;
		}
	}

	/**
	 * Converts the image to a {@link BufferedImage}
	 * @return The image
	 */
	BufferedImage toImage() {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = image.getRaster();
		raster.setDataElements(0, 0, width, height, pixels);
		return image;
	}
	

}
