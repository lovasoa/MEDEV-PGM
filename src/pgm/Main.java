/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * @author zhaoshuli
 */
public class Main {
	static PGMImage opened;
	static PGMImage transformed;

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		Mainwin.main(args);
	}

	static void openFile(File file) throws IOException {
		opened = new PGMImage(file);
		Mainwin.displayImage(opened.toImage());
	}

	static void histogram() {
		transformed = opened.histogram();
		Mainwin.displayImage(transformed.toImage());
	}

	static void threshold() {
		transformed = opened.threshold();
		Mainwin.displayImage(transformed.toImage());
	}
	
	static void scaleInPlace(double factor) {
		opened = opened.scale(factor);
		transformed = null;
		Mainwin.displayImage(opened.toImage());
	}
	
	static void difference(File file) throws FileNotFoundException, IOException {
		PGMImage other = new PGMImage(file);
		transformed = opened.difference(other);
		Mainwin.displayImage(transformed.toImage());
	}
}
