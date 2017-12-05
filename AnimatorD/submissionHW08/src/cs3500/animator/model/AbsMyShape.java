package cs3500.animator.model;


/**
 * Class to represent a shape in an animation.
 */
public abstract class AbsMyShape implements IMyShape {

  protected String name;
  protected double posX;
  protected double posY;
  protected double width;
  protected double height;
  protected ShapeColor col;
  protected boolean visible;
  protected boolean fill;
  protected ShapeType shapeType;

  /**
   * Constructs an AbsMyShape.
   *
   * @param name      this shape's name
   * @param posX      this shape's X position.
   * @param posY      this shape's Y position.
   * @param width     the width of this shape's rectangular base.
   * @param height    the height of this shape's rectangular base.
   * @param col       this shape's color.
   * @param visible   true if this shape is visible, false if not.
   * @param fill      true if this shape is filled, false if not.
   * @param shapeType the type of shape.
   */
  public AbsMyShape(String name, double posX, double posY, double width, double height,
                    ShapeColor col, boolean visible, boolean fill, ShapeType shapeType) {
    this.name = name;
    this.posX = posX;
    this.posY = posY;
    this.width = width;
    this.height = height;
    this.col = col;
    this.visible = visible;
    this.fill = fill;
    this.shapeType = shapeType;
  }

  @Override
  public void changeColor(ShapeColor newCol) {
    this.col = newCol;
  }

  @Override
  public ShapeColor getCol() {
    return this.col;
  }

  @Override
  public String getColAsString() {
    return "(" + col.r + "," + col.g + "," + col.b + ")";
  }

  private void changePosX(double newPosX) {
    this.posX = newPosX;
  }

  private void changePosY(double newPosY) {
    this.posY = newPosY;
  }

  @Override
  public void changePos(double newPosX, double newPosY) {
    this.changePosX(newPosX);
    this.changePosY(newPosY);
  }

  @Override
  public void scale(double newWidth, double newHeight) {
    this.width = newWidth;
    this.height = newHeight;
  }

  @Override
  public void makeVisible() {
    this.visible = true;
  }

  @Override
  public void makeInvisible() {
    this.visible = false;
  }


  @Override
  public double getPosY() {
    return posY;
  }

  @Override
  public double getPosX() {
    return posX;
  }

  @Override
  public double getWidth() {
    return width;
  }

  @Override
  public double getHeight() {
    return height;
  }

  @Override
  public boolean getVisibility() {
    return visible;
  }

  @Override
  public ShapeType getShapeType() {
    return this.shapeType;
  }
}