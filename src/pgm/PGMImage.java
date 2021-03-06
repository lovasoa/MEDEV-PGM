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
import java.io.FileWriter;
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
	 * Creates an image from raw pixel data bytes (-128=black, +127=white)
	 * 
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
	 * 
	 * @param file
	 *            The file to read
	 * @throws FileNotFoundException
	 * @throws IOException
	 *             If there is a problem while reading the file
	 * @throws UnsupportedOperationException
	 *             if the image is not in the PGM P2 format
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
	 * 
	 * @return The image
	 */
	BufferedImage toImage() {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = image.getRaster();
		raster.setDataElements(0, 0, width, height, pixels);
		return image;
	}

	/**
	 * Generates an histogram of the image. the x-axis represents color levels
	 * the y-axis represents the frequency of the color level in the image
	 * 
	 * @return The histogram
	 */
	public PGMImage histogram() {
		int w = 256;
		int h = 100;
		byte[] data = new byte[w * h];
		int[] levels = new int[w];
		int maxlevel = 0;
		for (int i = 0; i < pixels.length; i++) {
			int level = ((pixels[i] & 0xFF) * (w - 1)) / 255;
			levels[level]++;
			if (levels[level] > maxlevel) {
				maxlevel = levels[level];
			}
		}
		for (int x = 0; x < w; x++) {
			int val = (maxlevel == 0) ? 0 : (levels[x] * h) / maxlevel;
			for (int y = h - 1 - val; y >= 0; y--) {
				data[x + y * w] = (byte) 0xFF;
			}
		}
		return new PGMImage(w, h, data);
	}

	/**
	 * Returns a black&white image generated from the current image
	 * 
	 * @param level
	 *            the level (between 0 and 255) above which a pixel is
	 *            considered white
	 * @return
	 */
	public PGMImage threshold(int level) {
		byte[] data = new byte[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
			data[i] = ((int) pixels[i] > level) ? (byte) 0xFF : (byte) 0x00;
		}
		return new PGMImage(width, height, data);
	}

	/**
	 * Returns a black&white image with level set at 127
	 * 
	 * @return
	 */
	public PGMImage threshold() {
		return threshold(0);
	}

	/**
	 * Scales the image up or down.
	 * 
	 * @param w
	 *            The width of the new image
	 * @param h
	 *            The height of the new image
	 * @return the new image
	 */
	public PGMImage scale(int w, int h) {
		byte[] data = new byte[w * h];
		// Iteration over the pixels of the new image
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int oldx = x * width / w;
				int oldy = y * height / h;
				data[x + y * w] = pixels[oldx + oldy * width];
			}
		}
		return new PGMImage(w, h, data);
	}

	/**
	 * Scales the image up or down.
	 * 
	 * @param factor
	 *            the ratio between the width of the returned image and the
	 *            width of the current image
	 * @return The scaled image
	 */
	public PGMImage scale(double factor) {
		int w = (int) (factor * width);
		int h = (int) (factor * height);
		return scale(w, h);
	}

	/**
	 * Generates an image that is the difference between this image and an other
	 * one. The difference will be the size of this image (the other will be
	 * scaled up or down)
	 * 
	 * @param other
	 *            the other image
	 * @return the difference
	 */
	public PGMImage difference(PGMImage other) {
		byte[] otherpixs = other.scale(width, height).getPixels();
		assert (otherpixs.length == pixels.length);
		byte[] data = new byte[pixels.length];

		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) Math.abs(otherpixs[i] - pixels[i]);
		}
		return new PGMImage(width, height, data);
	}

	/**
	 * Exports the image to a pgm file
	 * 
	 * @param file
	 * @throws IOException
	 *             if the file cannot be written to
	 */
	public void export(File file) throws IOException {
		FileWriter w = new FileWriter(file);
		w.write("P2\n");
		w.write("#" + file.getName() + " (exported by MEDEV-PGM)\n");
		w.write(width + " " + height + "\n");
		w.write(255 + "\n");
		int charsInLine = 0;
		for (int i = 0; i < pixels.length; i++) {
			String chars = Integer.toString(pixels[i] & 0xFF);
			if (chars.length() + charsInLine > 70) {
				// No more than 70 chars per line
				w.write('\n');
				charsInLine = 0;
			} else {
				w.write(' ');
			}
			w.write(chars);
			charsInLine += chars.length();
		}
		w.close();
	}

	/**
	 * Give access to the pixels. All pixel values are between 0x00 (black) and
	 * 0xFF (white)
	 * 
	 * @return The array of pixels of the image
	 */
	public byte[] getPixels() {
		return pixels;
	}
}
