package cs3500.animator.provider.modelFiles;


import java.awt.Shape;
import java.awt.Color;

import javax.swing.text.Position;

/**
 * This is the interface of the AnimationModel.
 */
public interface IAnimationModel {

  /**
   * Adds a Shape to the animation for the given time interval.
   */
  void addShape(Shape shape, int timeStart, int timeEnd);

  /**
   * Changes a shapes position in the model over a specific time period.
   */
  void moveShape(String shapeName, Position endPosition, int timeStart, int timeEnd);

  /**
   * Changes a shapes scale in the model over a specific time period.
   */
  void scaleShape(String shapeName, int timeStart, int timeEnd, Object... params);

  /**
   * Changes a shapes colour in the model over a specific time interval.
   */
  void changeShapeColor(String shapeName, Color newColor, int timeStart, int timeEnd);

  /**
   * Removes all operation associated with a specific shape.
   */
  void removeShape(String shapeName);

//  /**
//   * Getter for the model's list of addShapeOperations.
//   */
//  ArrayList<AddShapeOperation> getAddShapeOperations();
//
//  /**
//   * Getter for the model's list of moveShapeOperations.
//   */
//  ArrayList<MoveShapeOperation> getMoveShapeOperations();
//
//  /**
//   * Getter for the model's list of ScaleShapeOperations.
//   */
//  ArrayList<ScaleShapeOperation> getScaleShapeOperations();
//
//  /**
//   * Getter for the model's list of ColorShapeOperations.
//   */
//  ArrayList<ColorShapeOperation> getColorShapeOperations();
}
