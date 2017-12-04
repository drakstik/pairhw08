package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

public interface IAnimatorModel {
  /**
   * Used to validate the anims in this constructor.
   *
   * @param anims the animations.
   * @param a     a single animation to be checked against all other
   * @return the answer to the question: should "a" be allowed to be on this.anims.
   */
  AbsAnimation validateAnim(List<AbsAnimation> anims, AbsAnimation a);

  /**
   * Is the given shape a valid entry to this.shapes? Check the name of s is unique amongst the
   * shapes.
   *
   * @param shapes the list of shapes to check s' name against.
   * @param s      the shape in question.
   * @return the answer to: is s a unique shape amongst shapes.
   */
  AbsMyShape validateShape(List<AbsMyShape> shapes, AbsMyShape s);

  /**
   * Returns the scene as a String at controller-specified time.
   *
   * @param time the time of the scene.
   */
  String snapshot(int time);

  /**
   * Getter for the anims field.
   *
   * @return this.anims
   */
  List<AbsAnimation> getAnims();

  /**
   * Getter for the shapes field.
   *
   * @return this.shapes
   */
  ArrayList<AbsMyShape> getShapes();

  /**
   * Give a description of this animator model.
   *
   * @return a string description of this animator model.
   */
  String describeAnimator();

  /**
   * Gives the end time of the animation.
   * @return the latest endTime of any AbsAnimation in the anims field.
   */
  int getEndTime();

  /**
   * Getter method for the loop field.
   */
  boolean getLoop();

  /**
   * Setter method for the loop field.
   * @param loop the boolean loop field should be set to
   */
  void setLoop(boolean loop);

  /**
   * Getter for a comprehensive list of the IAnimations comprising the animation.
   * @return a deep copy of this model's initAnims field.
   */
  ArrayList<AbsAnimation> getInitAnims();

  /**
   * Added the given animation to the list of animation in this model.
   */
  void addAnim(AbsAnimation anim);

  /**
   * Remove the given shape from the initShapes.
   * @param shape the shape.
   */
  void removeShape(AbsMyShape shape);
}
