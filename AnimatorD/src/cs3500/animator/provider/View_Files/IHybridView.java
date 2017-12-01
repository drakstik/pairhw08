package cs3500.animator.provider.View_Files;

import cs3500.animator.controller.HybridController;
import cs3500.animator.view.ISVGView;
import cs3500.animator.view.IVisualView;

public interface IHybridView extends IVisualView, ISVGView {

  /**
   * Set listener function for start button.
   */
  void setStartListener(HybridController listener);

  /**
   * Set listener function for Pause/Play button.
   */
  void setPausePlayListener(HybridController listener);

  /**
   * Set listener function for restart button.
   */
  void setRestartListener(HybridController listener);

  /**
   * Set listener function for new speed button.
   */
  void setNewSpeedListener(HybridController listener);

  /**
   * Pause the view timer.
   */
  void pauseTimer();

  /**
   * Play the view timer.
   */
  void playTimer();

  /**
   * Change the view timer speed.
   */
  void changeSpeed(int newTicksPerSec);
}
