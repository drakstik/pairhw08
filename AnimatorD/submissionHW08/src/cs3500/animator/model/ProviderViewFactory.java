package cs3500.animator.model;

import cs3500.animator.provider.viewFiles.View;
import cs3500.animator.provider.viewFiles.TextView;
import cs3500.animator.provider.viewFiles.VisualView;
import cs3500.animator.provider.viewFiles.SVGView;
import cs3500.animator.provider.viewFiles.HybridView;

public class ProviderViewFactory {

  /**
   * A method for creating a specific type of view.
   *
   * @param s String input to describe which type of view is required.
   * @return an IView of the specified type.
   */
  public View create(String s, int speed, String outPutFileName) {
    switch (s) {
      case "text":
        return new TextView(speed, outPutFileName);
      case "visual":
        return new VisualView(speed);
      case "svg":
        return new SVGView(speed, outPutFileName);
      case "interactive":
        return new HybridView(speed, outPutFileName);
      default:
        break;
    }
    return null;
  }
}
