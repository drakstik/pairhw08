package cs3500.animator.model;

/**
 * An interface to represent behaviour shared amongst all animations.
 */
public interface IAnimation {

  /**
   * Return a cloneAnim of this animation.
   */
  AbsAnimation cloneAnim();

  /**
   * Get this animation's beginning time.
   *
   * @return this animations bgn.
   */
  int getBgn();

  /**
   * Get this animation's ending time.
   *
   * @return this animations end.
   */
  int getEnd();

  /**
   * Get this animation's name.
   *
   * @return this animations name.
   */
  String getName();

  /**
   * Get this animation's name.
   *
   * @return this animations name.
   */
  int getDuration();

  //CHANGED FROM A5: Renamed from animateForT; now only mutates (no return)
  //now only called if anim needs to animate
  //(i.e. if AbsAnimation.bgn <= current tick && AbsAnimation.end >= current tick)
  //TODO test
  /**
   * Animate this animation's shape to match t.
   * @param t the amount of time that has passed since the bgn
   * @result an IMyShape with the appropriate info.
   */
  void animateForT(int t);

  //TODO test
  /**
   * Give (mutation) this animation a new shape to work on and ensures this animation has the same
   * name as the given shape.
   * @param shape the shape
   */
  void giveShape(IMyShape shape);

  //TODO test
  /**
   * Get this animation's elapsed duration, i.e. how long has it been animating for, given the time
   * @param time the time
   * @return see above.
   */
  int ticksSinceBgn(int time);

  /**
   * Get this animation's type.
   * @return this animation's type.
   */
  AbsAnimation.AnimType getType();

  /**
   * Get this animation's shape, if it has one.
   * @return this animation's shape
   */
  IMyShape getShape();

  /**
   * Create a SVG style representation of this animation.
   */
  String toSVG();

  /**
   * Getter method for the loop field.
   */
  boolean getLoop();

  /**
   * Setter method for the loop field.
   * @param loop the boolean loop field should be set to.
   */
  void setLoop(boolean loop);
}
