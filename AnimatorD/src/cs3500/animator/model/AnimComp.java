package cs3500.animator.model;

import java.util.Comparator;

/**
 * A class to determine the order of Animations in a List.
 */
class AnimComp implements Comparator<AbsAnimation> {
  @Override
  public int compare(AbsAnimation a1, AbsAnimation a2) {
    return Integer.compare(a1.bgn, a2.bgn);
  }
}
