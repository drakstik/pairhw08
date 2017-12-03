package cs3500.animator.provider.Model_Files;

import cs3500.animator.model.IMyShape;

/**
 * This is the interface of the ShapeOperation Class, which represents an operation made by the
 * model on a shape.
 */
public interface IShapeOperation {

  /**
   * Getter for the timeStart variable.
   */
  int getTimeStart();

  /**
   * Getter for the timeEnd variable.
   */
  int getTimeEnd();

  /**
   * Getter for the shapeName variable.
   */
  String getShapeName();

  /**
   * Getter for the shapeType variable.
   */
  IMyShape.ShapeType getShapeType();
//
//  /**
//   * Getter for the operation variable.
//   */
//  OperationType getOperation();
}
