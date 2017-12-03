package cs3500.animator.controller;

import cs3500.animator.model.AbsMyShape;
import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.provider.Controller_Files.IVisualController;
import cs3500.animator.view.VisualView;
import cs3500.animator.model.Utils;
import javax.swing.Timer;
import javax.swing.JComponent;


import java.awt.event.ActionEvent;

/**
 * A class extending AbsController and acting as a controller for the VisualView.
 */
public class ControllerVisual implements IController, IVisualController {
  IAnimatorModel model;
  VisualView view;
  cs3500.animator.provider.View_Files.VisualView pview; //provider views

  int t = 0;
  double tickRate = 1.0;
  boolean loop;
  Timer timer;
  final Utils utils = new Utils();

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

  /**
   * Constructor for this class.
   * @param model the animation's model.
   * @param pview the animation's view.
   */
  public ControllerVisual(IAnimatorModel model,
                          cs3500.animator.provider.View_Files.VisualView pview) {
    this.model = model;
    this.pview = pview;

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

  @Override
  public void timerAction(int time) {
    model.snapshot(time);
    this.viewDisplay();
  }

  @Override
  public void viewDisplay() {
    pview.setUpPanel(utils.MyShapesToShapes(model.getShapes()),
            utils.MyShapesToColors(model.getShapes()));
  }

//  @Override
//  public Shape addShape(AddShapeOperation addOp) {
//    return null;
//  }
//
//  @Override
//  public Shape moveShape(MoveShapeOperation moveOp, Shape moveShape, int currentTime) {
//    return null;
//  }
//
//  @Override
//  public Shape scaleShape(ScaleShapeOperation scaleOp, Shape scaleShape, int currentTime) {
//    return null;
//  }
//
//  @Override
//  public Color changeShapeColor(ColorShapeOperation colorOp, int currentTime) {
//    return null;
//  }
}
