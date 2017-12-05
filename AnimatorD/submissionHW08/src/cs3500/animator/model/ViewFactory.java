package cs3500.animator.model;

import cs3500.animator.view.HybridView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.VisualView;

/**
 * A class to represent a view factory that creates an IView.
 */
public class ViewFactory {

  /**
   * A method for creating a specific type of view.
   * @param s String input to describe which type of view is required.
   * @return an IView of the specified type.
   */
  public IView create(String s) {
    switch (s) {
      case "text":
        return new TextView();
      case "visual":
        return new VisualView();
      case "svg":
        return new SVGView();
      case "interactive":
        return new HybridView();
      default:
        break;
    }
    return null;
  }

}
