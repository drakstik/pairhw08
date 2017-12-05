package cs3500.animator.provider.modelFiles;

import java.awt.Color;

import javax.swing.text.Position;

import cs3500.animator.model.IMyShape;

/**
 * Interface for the Shape superclass.
 */
public interface ShapeInterface {
  //
  //  /**
  //   * Moves the shape to a new provided position.
  //   *
  //   * @return the MoveShapeOperation corresponding to this move.
  //   */
  //  MoveShapeOperation move(Position newPosition, int timeStart, int timeEnd);
  //
  //  /**
  //   * Change the color of the shape to the new provided color.
  //   *
  //   * @return the ColorShapeOperation corresponding to this color change.
  //   */
  //  ColorShapeOperation changeColor(Color newColor, int startTime, int endTime);
  //
  //  /**
  //   * Scales the shape to new provided dimensions.
  //   *
  //   * @return the ScaleShapeOperation corresponding to this shape scale change.
  //   */
  //  ScaleShapeOperation scale(int timeStart, int timeEnd, Object... params);

  /**
   * Set the time at which the shape will appear and disappear.
   *
   * @param timeIn  the time at which the shape will appear
   * @param timeOut the time at which the shape will disappear
   */
  void setTimeInOut(int timeIn, int timeOut);

  /**
   * Checks to see if the shape is in the animation at the input time.
   *
   * @param atTime The time at which the shape isInAnimation test will take place
   */
  boolean isInAnimation(int atTime);

  /**
   * Getter for the position of the shape.
   */
  Position getPosition();

  /**
   * Getter for the color of the shape.
   */
  Color getColor();

  /**
   * Getter for the shape name.
   */
  String getName();

  /**
   * Getter for the shape type.
   */
  IMyShape.ShapeType getType();
  //
  //  /**
  //   * Getter for the AddShapeOperation created when adding shape to animation.
  //   */
  //  AddShapeOperation getAddOperation(int timeStart, int timeEnd, Position position);
}


