package cs3500.animator.provider.View_Files;

import cs3500.animator.provider.Controller_Files.IHybridController;

public interface IHybridView extends IVisualView, ISVGView {

  /**
   * Set listener function for start button.
   */
  void setStartListener(IHybridController listener);

  /**
   * Set listener function for Pause/Play button.
   */
  void setPausePlayListener(IHybridController listener);

  /**
   * Set listener function for restart button.
   */
  void setRestartListener(IHybridController listener);

  /**
   * Set listener function for new speed button.
   */
  void setNewSpeedListener(IHybridController listener);

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
