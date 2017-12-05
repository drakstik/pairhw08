package cs3500.animator.provider.controllerFiles;

public interface IHybridController {

  /**
   * Checks time and looping in animation.
   */
  void timeCheck(int time);

  /**
   * Start the animation.
   */
  void start();

  /**
   * Pause or play the animation, depending on the current state.
   */
  void pausePlay();

  /**
   * Check if animation is paused.
   */
  boolean isPaused();

  /**
   * Remove shape from the model.
   */
  void removeShapeName(String shapeString);

  /**
   * Restart the animation.
   */
  void restart();

  /**
   * enable looping in the animation.
   */
  void enableLoop();

  /**
   * Change the animation speed.
   */
  void changeSpeed(int ticksPerSec);

  /**
   * Display the animation view.
   */
  void viewDisplay();

}
