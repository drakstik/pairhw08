package cs3500.animator.provider.viewFiles;

/**
 * The view interface for the text view: The output should follow the format set here:
 * Shapes: Name: R Type: rectangle Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0,
 * Color: (1.0,0.0,0.0) Appears at t=0.5s Disappears at t=50.0s.
 * Name: C Type: oval Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)
 * Appears at t=3.0s Disappears at t=50.0s.
 * Shape R moves from (200.0,200.0) to (300.0,300.0) from t=5.0s to t=25.0s Shape C moves from
 * (500.0,100.0) to (500.0,400.0) from t=10.0s to t=35.0s Shape C changes color from (0.0,0.0,1.0)
 * to (0.0,1.0,0.0) from t=25.0s to t=40.0s Shape R scales from Width: 50.0, Height: 100.0 to Width:
 * 25.0, Height: 100.0 from t=25.5s to t=35.0s Shape R moves from (300.0,300.0) to (200.0,200.0)
 * from t=35.0s to t=50.0s.
 */
public interface ITextView extends View {

  /**
   * Sets the JFrame visibility to true, so it can be seen.
   */
  void display();

  /**
   * Repaints the JFrame canvas, effectively refreshing the animation.
   */
  void refresh();

  /**
   * Appends a description of the animation to the output string.
   */
  void describeAnimation(String controllerStringOut);

}
