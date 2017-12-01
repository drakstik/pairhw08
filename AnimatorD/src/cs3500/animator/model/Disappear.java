package cs3500.animator.model;

import java.util.Objects;

public class Disappear extends AbsAnimation {

  /**
   * Constructor.
   *
   * @param name this animation's name. Should be the same as the shape it affects.
   * @param bgn  the time at which the animation begins.
   * @param end  the time at which the animation begins.
   */
  public Disappear(String name, int bgn, int end) {
    super(name, bgn, end, AnimType.DISAPPEAR);
  }

  /**
   * Give (mutation) this animation a new shape to work on and ensures this animation has the same
   * name as the given shape.
   *
   * @param shape the shape
   */
  public void giveShape(IMyShape shape) {
    this.shape = shape;
    this.name = shape.getName();
  }

  @Override
  public AbsAnimation cloneAnim() {
    return new Disappear(this.name, this.bgn, this.end);
  }

  @Override
  public void animateForT(int t) {
    if (shape != null && t >= bgn) {
      shape.makeInvisible();
    } else if (shape == null) {
      throw new IllegalArgumentException("This animation was not given a shape before "
              + "animateForT() was called on it");
    }
  }

  @Override
  public String toString() {
    String result = "";
    if (shape != null) {
      result += this.name + " disappears at t=" + this.bgn;
    } else {
      result += "This animation has no shape to work on";
    }
    return result;
  }

  @Override
  public String toString(double tickRate) {
    String result = "";
    if (shape != null) {
      result += "Disappears at t=" + (this.bgn / tickRate) + "s";
    } else {
      result += "This animation has no shape to work on";
    }
    return result;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Disappear)) {
      return false; }
    Disappear that = (Disappear) other;
    return that.hashCode() == this.hashCode();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.bgn, this.end);
  }
}
