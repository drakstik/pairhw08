package cs3500.animator.view;

import javax.swing.JTextField;

import cs3500.animator.controller.IController;
import cs3500.animator.model.IAnimatorModelView;

/**
 * Interface class for defining similar behavior across different views.
 * An IView displays the contents of its IAnimatorModelView, which it holds as a field.
 * The contents are "displayed", for example, on the screen, through System.out, or written
 * into a file.
 */
public interface IView {

  /**
   * Display this IView's model.
   */
  void display();

  /**
   * Create a string representation of this HybridView's model, in SVG format; write to
   * console or to a file.
   * @throws UnsupportedOperationException if this view is not HrybridView or SVGView.
   */
  void svgDisplay() throws UnsupportedOperationException;

  /**
   * Change this IView's model field to that IAnimatorModelView.
   * @param model the model for this IView to use.
   */
  void setModel(IAnimatorModelView model);

  /**
   * Change this IView's to write its display to the given filePath.
   * @param output a String representing a filePath.
   * @throws UnsupportedOperationException if called on an IView that does not display to a file.
   */
  void setOutput(Appendable output) throws UnsupportedOperationException;

  /**
   * Get this view's tickInput.
   */
  JTextField getTickInput() throws UnsupportedOperationException;

  /**
   * Set this view's tickRateLabel.
   * @param s the string the tickRateLabel should have.
   */
  void setTickRateLabel(String s) throws UnsupportedOperationException;

  /**
   * Getter method for this view's output field.
   * @return this view's output field.
   */
  Appendable getOutput() throws UnsupportedOperationException;

  /**
   * Set this view's controller to the given controller.
   * @param controller the controller you want to attach to this view.
   */
  void setController(IController controller);
}
