package cs3500.animator.model;

import static cs3500.animator.model.Utils.round;

public class MyOval extends AbsMyShape {

  /**
   * Constructor.
   *
   * @param name    this MyOval's name.
   * @param posX    this MyOval's X position.
   * @param posY    this MyOval's Y position.
   * @param width   this MyOval's radius in the X axis (horizontally).
   * @param height  this MyOval's radius in the Y axis (vertically).
   * @param col     this MyOval's color.
   * @param visible true if this MyOval is visible, false if not.
   */
  public MyOval(String name, double posX, double posY, double width, double height, ShapeColor col,
                boolean visible, boolean fill) {
    super(name, posX, posY, width, height, col, visible, fill, ShapeType.OVAL);
  }

  @Override
  public String toString() {
    String result = "";

    result += "Name: " + this.name + "\n";
    result += "Type: Oval \n";
    result += "Center: (" + round(posX) + "," + round(posY) + "), "
            + "X Radius: " + round(width / 2) + ", Y Radius: " + round(height / 2)
            + ", Color: (" + round(col.r)
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
  public MyOval makeCopy() {
    return new MyOval(this.getName(), this.getPosX(), this.getPosY(), this.getWidth(),
            this.getHeight(), this.getCol(), this.getVisibility(), fill);
  }

  @Override
  public String toSVG() {
    String v = "";
    if (this.visible) {
      v = "visible";
    } else {
      v = "hidden";
    }
    Double cxd = posX;
    int cx = cxd.intValue();
    Double cyd = posY;
    int cy = cyd.intValue();
    Double rxd = this.width / 2;
    int rx = rxd.intValue();
    Double ryd = this.height / 2;
    int ry = ryd.intValue();
    Double rd = col.r * 255;
    int r = rd.intValue();
    Double gd = col.g * 255;
    int g = gd.intValue();
    Double bd = col.b * 255;
    int b = bd.intValue();

    return String.format("<ellipse id=\"%s\" cx=\"%d\" cy=\"%d\" rx=\"%d\" "
                    + "ry=\"%d\" fill=\"rgb(%d,%d,%d)\" visibility=\"%s\" >",
            this.name, cx, cy, rx, ry, r, g, b, v);
  }
}