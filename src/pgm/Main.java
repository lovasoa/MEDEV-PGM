/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgm;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author zhaoshuli
 */
public class Main {
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Mainwin.main(args);
  }

  static void openFile(File file) throws IOException {
    PGMImage pgm = new PGMImage(file);
    Mainwin.displayImage(pgm.toImage());
  }
}
