package cs3500.animator.model;

import java.awt.Shape;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Utils {
  // round the given double to one decimal place (e.g. for String display)
  // modified from jpdymond's answer from
  // https://stackoverflow.com/questions/22186778/using-math-round-to-round-to-one-decimal-place
  public static double round(double value) {
    int scale = (int) Math.pow(10, 1);
    return (double) Math.round(value * scale) / scale;
  }

  /**
   * Turn the given ArrayList of MyShapes into a LinkedHashMap with K: String V: Shape.
   * @param myShapes ArrayList of MyShapes.
   * @result LinkedHashMap with K: String V: Shape.
   */
  public LinkedHashMap<String, Shape> MyShapesToShapes(ArrayList<AbsMyShape> myShapes) {
    LinkedHashMap<String, Shape> l = new LinkedHashMap<String,Shape>();

    for (int i = 0; i < myShapes.size(); i++) {
      AbsMyShape ms = myShapes.get(i);
      Shape s;

      if (ms.getShapeType() == IMyShape.ShapeType.RECTANGLE) {
        s = new Rectangle2D.Double(ms.getPosX(), ms.getPosY(), ms.getWidth(), ms.getHeight());
      } else {
        s = new Ellipse2D.Double(ms.getPosX(), ms.getPosY(), ms.getWidth(), ms.getHeight());
      }

      if (ms.getVisibility()) {
        l.put(ms.getName(), s);
      }
    }

    return l;
  }

  /**
   * Turn the given ArrayList of MyShapes into a LinkedHashMap with K: String V: Color.
   * @param myShapes ArrayList of MyShapes.
   * @result LinkedhashMap with K: String V: Color.
   */
  public LinkedHashMap<String, Color> MyShapesToColors(ArrayList<AbsMyShape> myShapes) {
    LinkedHashMap<String, Color> l = new LinkedHashMap<String, Color>();

    for (int i = 0; i < myShapes.size(); i++) {
      AbsMyShape ms = myShapes.get(i);
      Color c = ms.getCol().scToColor();
      l.put(ms.getName(), c);
    }

    return l;
  }
}
