package cs3500.animator.provider.Controller_Files;

import java.util.ArrayList;

import cs3500.animator.model.ShapeOperation;

public interface ISVGController {

  /**
   * Retreives Data describing every new shape created, and the changes made to them.
   */
  void getDataFromModel();

  /**
   * Generates an array list of operations arranged according to the the shape to which they occur.
   * Meaning the first operation occuring to every shape MUST be 'ADD' followed by any further
   * animation components.
   */
  ArrayList<ShapeOperation> generateOperationsArray();

  /**
   * Given the arrayList of shape operations generated in the generateOperationArray function,
   * generates a StringBuilder containing the whole animation in the SVG format set to be exported
   * in an XML document.
   */
  StringBuilder generateXML(ArrayList<ShapeOperation> shapeOps);

  /**
   * Return a string generated in the generateXML function that describes the animation.
   */
  void describeAnimation();

  /**
   * Implements a counting sort Algorithm in order to create a sorted list of all actions, based on
   * the time at which they occur. From least to greatest.
   */
  ArrayList timeSortShapeOperations();
}
