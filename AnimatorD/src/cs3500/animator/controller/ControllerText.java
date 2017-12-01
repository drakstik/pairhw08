package cs3500.animator.controller;

import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.view.TextView;

import javax.swing.JComponent;
import java.awt.event.ActionEvent;

/**
 * A  controller that interfaces between a model and an svgView to create
 * a text file representing the model.
 */
public class ControllerText implements IController {
  IAnimatorModel model;
  TextView view;
  double tickRate = 1.0;

  /**
   * Constructs a controller for an animation with a TextView.
   * @param model the animation's model.
   * @param view  the animation's view.
   */
  public ControllerText(IAnimatorModel model, TextView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setTickRate(double tr) {
    this.tickRate = tr;
  }

  @Override
  public void run() {
    view.display();
  }

  @Override
  public JComponent setUpButtons() {
    throw new UnsupportedOperationException();
  }

  @Override
  public double getTickRate() {
    return tickRate;
  }

  @Override
  public boolean getLoop() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getT() {
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
