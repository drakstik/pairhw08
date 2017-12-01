package cs3500.animator.controller;

import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.view.VisualView;

import javax.swing.Timer;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;

/**
 * A class extending AbsController and acting as a controller for the VisualView.
 */
public class ControllerVisual implements IController {
  IAnimatorModel model;
  VisualView view;

  int t = 0;
  double tickRate = 1.0;
  boolean loop;
  Timer timer;

  /**
   * Constructor for this class.
   * @param model the animation's model.
   * @param view  the animation's view.
   */
  public ControllerVisual(IAnimatorModel model, VisualView view) {
    this.model = model;
    this.view = view;

    int tickRateMS = ((Double) (1000 / tickRate))
            .intValue();
    timer = new Timer(tickRateMS, this);
    timer.setActionCommand("timer");
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    if (actionEvent.getActionCommand().equals("timer")) {
      model.snapshot(t);
      t++;
    }
    //else { throw new IllegalArgumentException("Unknown actionEvent"); }
  }

  @Override
  public void setTickRate(double tr) {
    this.tickRate = tr;
    int tickRateMS = ((Double) (1000 / tickRate))
            .intValue();
    timer.setDelay(tickRateMS);
  }

  @Override
  public void run() {
    timer.start();
    view.display();
  }

  @Override
  public double getTickRate() {
    return tickRate;
  }

  @Override
  public boolean getLoop() {
    return loop;
  }

  @Override
  public int getT() {
    return t;
  }

  @Override
  public void setT(int tick) {
    throw new UnsupportedOperationException();
  }

  @Override
  public JComponent setUpButtons() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void restartTimer() {
    this.timer.restart();
  }
}
