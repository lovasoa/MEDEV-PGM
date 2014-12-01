/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgm;

/**
 *
 * @author zhaoshuli
 */
public class Main {

  private int x;

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    System.out.println("Hello World");
    System.err.println("我爱你");
    System.out.println("我爱你  欧菲赫");

    String color = "red";
    String colorRGB;
    switch (color) {
      case "black":
        colorRGB = "000000";
        break;
      case "red":
        colorRGB = "ff0000";
        break;
      case "green":
        colorRGB = "008000";
        break;
      case "blue":
        colorRGB = "0000ff";
        break;
      default:
        colorRGB = "Invalid color";
        break;
    }
    System.out.println(colorRGB);
    Mainwin.main(args);
  }

  /**
   *
   * @param useless useless
   * @return 9
   */
  public int doNothing(String useless) {
    return 9;
  }

}
