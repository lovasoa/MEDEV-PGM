/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgm;

import java.awt.image.BufferedImage;
import java.io.*;
import static java.io.StreamTokenizer.*;
import java.util.Scanner;

/**
 *
 * @author zhaoshuli
 */
public class PGMImage {

  private int width = 0;
  private int height = 0;
  private char[][] pixels;

  private double maxgrey = 255;

  PGMImage(File file) throws FileNotFoundException, IOException {
    StreamTokenizer s = new StreamTokenizer(new FileReader(file));
    s.commentChar('#');

    //Header
    //Type
    assert (s.nextToken() == TT_WORD);
    if (!"P2".equals(s.sval)) {
      throw new UnsupportedOperationException("Only P2 supported yet.");
    }

    //width
    assert (s.nextToken() == TT_NUMBER);
    width = (int) s.nval;

    //height
    assert (s.nextToken() == TT_NUMBER);
    height = (int) s.nval;

    //Maximum grey value
    assert (s.nextToken() == TT_NUMBER);
    maxgrey = s.nval;
    
    pixels = new char[height][width];

  }

  BufferedImage toImage() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
