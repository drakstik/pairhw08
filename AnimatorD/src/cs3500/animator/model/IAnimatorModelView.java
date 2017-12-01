package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Should be the same as IAnimatorModel, minus some methods
 * that are never called publicly but are public for testing.
 */
public interface IAnimatorModelView {

  /**
<<<<<<< HEAD
   * Returns the scene as a String at controller-specified time.
   *
   * @param time the time of the scene.
   */
  String snapshot(int time);

  /**
=======
>>>>>>> b1c9b276cbe0524f2d5ae6defe6064d44ac065e3
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
   * Added the given animation to the list of animation in this model.
   */
  void addAnim(AbsAnimation anim);
}
