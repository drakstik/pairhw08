package cs3500.animator.provider.Controller_Files;

import java.util.ArrayList;

import cs3500.animator.model.ColorShapeOperation;
import cs3500.animator.model.ScaleShapeOperation;
import cs3500.animator.model.ShapeOperation;
import cs3500.animator.model.AddShapeOperation;
import cs3500.animator.model.MoveShapeOperation;

public interface ITextController {

  /**
   * Retreives Data describing every new shape created, and the changes made to them.
   */
  void getDataFromModel();

  /**
   * Generates text output describing the animation: calls the helper functions
   * generateShapesDialogue, and generateChangesDialogue.
   */
  void generateOutput();

  /**
   * Given an arrayList containing all 'Add Shape' Operations, generates the text string describing
   * each shape according to the designated format.
   */
  void generateShapesDialogue(ArrayList<AddShapeOperation> list);

  /**
   * Given an arrayList containing all Operations except for 'Add Shape', generates the text string
   * describing animation arranged in order of the time at which they occur. Calls the helper
   * functions: generateMoveDialogue, generateScaleDialogue, and generateColorDialogue.
   */
  void generateChangesDialogue(ArrayList<ShapeOperation> list);

  /**
   * Given an arrayList containing all 'Move Shape' operations, generates the text string describing
   * the animation arranged in order of the time at which they occur.
   */
  void generateMoveDialogue(MoveShapeOperation moveOperation);

  /**
   * Given an arrayList containing all 'Scale Shape' operations, generates the text string
   * describing the animation arranged in order of the time at which they occur.
   */
  void generateScaleDialogue(ScaleShapeOperation scaleOperation);

  /**
   * Given an arrayList containing all 'Color Shape' operations, generates the text string
   * describing the animation arranged in order of the time at which they occur.
   */
  void generateColorDialogue(ColorShapeOperation colorOperation);

  /**
   * Outputs the string describing the animation, that is built over the course of the functions
   * above.
   */
  String describeAnimation();

  /**
   * Implements a counting sort Algorithm in order to create a sorted list of all actions, based on
   * the time at which they occur. From least to greatest.
   */
  ArrayList timeSortShapeOperations();

}




