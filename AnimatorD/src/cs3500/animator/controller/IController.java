package cs3500.animator.controller;

import javax.swing.JComponent;
import java.awt.event.ActionListener;


/**
 * A controller interface for the animator views and models.
 */
public interface IController extends ActionListener {

  /**
   * Set the tickrate of this controller to tr.
   * Where relevant, the delay of the controller's timer is set to match the new tickrate.
   * @param tr the number of ticks per second.
   */
  void setTickRate(double tr);

  /**
   * Sets up the animation and "displays" it (on-screen, to console, or written to a file).
   */
  void run();

  /**
   * Sets up the JButtons for shape selection.
   * @throws UnsupportedOperationException if this is not a ControllerHybrid.
   */
  JComponent setUpButtons() throws UnsupportedOperationException;

  /**
   * Get this controller's tickRate.
   * @return this controller's tickRate.
   * @throws UnsupportedOperationException if this controller does not have a tickRate.
   */
  double getTickRate() throws UnsupportedOperationException;

  /**
   * Get this controller's loop field.
   * @return this controller's loop field.
   * @throws UnsupportedOperationException if this controller does not have a loop field.
   */
  boolean getLoop() throws UnsupportedOperationException;

  /**
   * Get this controller's current tick.
   * @return this controller's current tick.
   * @throws UnsupportedOperationException if this controller does not have a field t.
   */
  int getT() throws UnsupportedOperationException;

  /**
   * Set this controller's current tick (field t) to tick.
   * @param tick an int representing the new t field.
   * @throws UnsupportedOperationException if this controller does not have a field t.
   */
  void setT(int tick);

  /**
   * Reset this controller's timer.
   * @throws UnsupportedOperationException if this controller does not have a timer.
   */
  void restartTimer();
}
