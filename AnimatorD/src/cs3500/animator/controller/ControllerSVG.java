package cs3500.animator.controller;

import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.view.SVGView;

import javax.swing.JComponent;
import java.awt.event.ActionEvent;

/**
 * A controller that interfaces between a model and an svgView to create
 * an SVG file representing the model.
 */
public class ControllerSVG implements IController {
  IAnimatorModel model;
  SVGView view;
  boolean loop;

  /**
   * Constructs a controller for an animation with an svgView.
   * @param model the animation's model.
   * @param view  the animation's view.
   */
  public ControllerSVG(IAnimatorModel model, SVGView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setTickRate(double tr) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void run() {
    view.display();
  }

  @Override
  public JComponent setUpButtons() throws UnsupportedOperationException {
    return null;
  }

  @Override
  public double getTickRate() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean getLoop() {
    return loop;
  }

  @Override
  public int getT() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setT(int tick) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void restartTimer() {
    throw new UnsupportedOperationException();
  }
}
