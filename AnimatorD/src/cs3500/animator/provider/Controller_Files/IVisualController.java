package cs3500.animator.provider.Controller_Files;

import java.awt.Shape;
import java.awt.Color;

/**
 * Interface for the VisualController.
 */
public interface IVisualController {

  /**
   * Function called when view timer action listener triggers. Function parses operations from model
   * and uses the following functions to create java.awt.Shape objects to send to the view.
   */
  void timerAction(int time);
//
//  /**
//   * Creates a new java.awt.Shape object based on the AddShapeOperation addOp.
//   */
//  Shape addShape(AddShapeOperation addOp);
//
//  /**
//   * Creates a new java.awt.Shape object based on the MoveShapeOperation moveOp and the current
//   * time.
//   */
//  Shape moveShape(MoveShapeOperation moveOp, java.awt.Shape moveShape, int currentTime);
//
//  /**
//   * Creates a new java.awt.Shape object based on the ScaleShapeOperation moveOp and the current
//   * time.
//   */
//  Shape scaleShape(ScaleShapeOperation scaleOp, java.awt.Shape scaleShape,
//                            int currentTime);
//
//  /**
//   * Creates a new java.awt.Shape object based on the ColorShapeOperation moveOp and the current
//   * time.
//   */
//  Color changeShapeColor(ColorShapeOperation colorOp, int currentTime);

  /**
   * Call the display function of the view.
   */
  void viewDisplay();
}
