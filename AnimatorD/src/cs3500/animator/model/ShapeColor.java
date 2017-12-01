package cs3500.animator.model;

/**
 * Represent the color of a SimpleShape.
 */
public class ShapeColor {
  double r;
  double g;
  double b;

  /**
   * Constructor.
   *
   * @param r the red value.
   * @param g the green value.
   * @param b the blue value.
   */
  public ShapeColor(double r, double g, double b) {
    if (r < 0.0 || r > 1.0) {
      throw new IllegalArgumentException("r param given to ShapeColor constructor "
              + "must be between 0 and 1.0");
    } else if (g < 0 || g > 1.0) {
      throw new IllegalArgumentException("g param given to ShapeColor constructor "
              + "must be between 0 and 1.0");
    } else if (b < 0 || b > 1.0) {
      throw new IllegalArgumentException("b param given to ShapeColor constructor "
              + "must be between 0 and 1.0");
    } else {
      this.r = r;
      this.g = g;
      this.b = b;
    }
  }

  @Override
  public String toString() {
    return "(" + Double.toString(this.r) + "," + Double.toString(this.g) + ","
            + Double.toString(this.b) + ")";
  }

  /**
   * get the red value of this color.
   *
   * @return this.r
   */
  public double getR() {
    return r;
  }

  /**
   * get the green value of this color.
   *
   * @return this.g
   */
  public double getG() {
    return g;
  }

  /**
   * get the blue value of this color.
   *
   * @return this.b
   */
  public double getB() {
    return b;
  }

}
