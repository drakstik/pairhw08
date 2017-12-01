package cs3500.animator.model;

import static cs3500.animator.model.Utils.round;

/**
 * Data representation of a rectangle.
 */
public class MyRectangle extends AbsMyShape {

  //TODO test each of the constructors

  /**
   * Constructor.
   * @param name    this MyOval's name.
   * @param height  this rectangle's height.
   * @param width   this rectangle's width.
   * @param posX    this rectangle's x position.
   * @param posY    this rectangle's y position.
   * @param col     this rectangle's color.
   * @param visible true if this rectangle is visible, false if not.
   * @param fill    true if this rectangle is filled, false if not.
   */
  public MyRectangle(String name, double posX, double posY, double width, double height,
                     ShapeColor col, boolean visible, boolean fill) {
    super(name, posX, posY, width, height, col, visible, fill, ShapeType.RECTANGLE);
  }

  @Override
  public MyRectangle makeCopy() {
    return new MyRectangle(this.getName(), this.getPosX(), this.getPosY(), this.getWidth(),
            this.getHeight(), this.getCol(), this.getVisibility(), this.fill);
  }

  @Override
  public String toString() {
    String result = "";

    result += "Name: " + name + "\n";
    result += "Type: rectangle\n";
    result += "Lower-Left Corner: (" + round(posX) + "," + round(posY + height) + "), "
            + "Width: " + round(width) + ", Height: " + round(height) + ", Color: (" + round(col.r)
            + "," + round(col.g) + "," + round(col.b) + ")";

    return result;
  }

  @Override
  public String getName() {
    return this.name;
  }


  @Override
  public boolean getFill() {
    return this.fill;
  }

  @Override
  public String toSVG() {
    String v = "";
    if (this.visible) {
      v = "visible";
    } else {
      v = "hidden";
    }

    Double xd = posX;
    int x = xd.intValue();
    Double yd = posY;
    int y = yd.intValue();
    Double wd = this.width;
    int w = wd.intValue();
    Double hd = this.height;
    int h = hd.intValue();
    Double rd = col.r * 255;
    int r = rd.intValue();
    Double gd = col.g * 255;
    int g = gd.intValue();
    Double bd = col.b * 255;
    int b = bd.intValue();
    return String.format("<rect id=\"%s\" x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" "
                   + "fill=\"rgb(%d,%d,%d)\" visibility=\"%s\" >", this.name,
              x, y, w, h, r, g, b, v);
  }
}
