package cs3500.animator.model;

public interface IMyShape {

  /**
   * Represents all the children of this class, which in turn represent shapeTypes.
   */
  enum ShapeType {
    RECTANGLE, OVAL
  }

  /**
   * Change this shape's color.
   *
   * @param newCol change this shape's col to newCol.
   */
  void changeColor(ShapeColor newCol);

  /**
   * Get this shape's color as a String.
   */
  String getColAsString();

  /**
   * Get this shape's color.
   */
  ShapeColor getCol();

  /**
   * Change this shape's posX and posY.
   *
   * @param newPosX change this shape's posX to the newPosX
   */
  void changePos(double newPosX, double newPosY);

  /**
   * Change this shape's width and height.
   *
   * @param newWidth  change this shape's width to the newWidth
   * @param newHeight change this shape's height to the newHeight
   */
  void scale(double newWidth, double newHeight);

  /**
   * Makes this shape visible.
   */
  void makeVisible();

  /**
   * Makes this shape invisible.
   */
  void makeInvisible();

  /**
   * Get this shape's name.
   */
  String getName();


  /**
   * Get this shape's x position.
   *
   * @return this shape's x position
   */
  double getPosX();

  /**
   * Get this shape's y position.
   *
   * @return this shape's y position
   */
  double getPosY();

  /**
   * Get this shape's distance between it widest two points, horizontally.
   *
   * @return this shape's y position
   */
  double getWidth();

  /**
   * Get this shape's distance between it widest two points, vertically.
   *
   * @return this shape's y position
   */
  double getHeight();

  //TODO test

  /**
   * Get this shape's fill field.
   *
   * @return this fill field.
   */
  boolean getFill();

  //TODO test

  /**
   * Get this shape's visible field.
   *
   * @return this visible field.
   */
  boolean getVisibility();

  /**
   * Get this shape's type.
   *
   * @return this shape's type.
   */
  AbsMyShape.ShapeType getShapeType();

  /**
   * Create a copy of this IMyShape.
   */
  AbsMyShape makeCopy();

  /**
   * Create a SVG style representation of this shape.
   */
  String toSVG();
}