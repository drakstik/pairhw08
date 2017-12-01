package cs3500.animator.view;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.JTextField;

import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorModelView;

/**
 * A class extending JFrame to create a visual UI represention of an Animator model.
 */
public class VisualView extends JFrame implements IView {

  private static final int DEFAULT_WINDOW_WIDTH = 1000;
  private static final int DEFAULT_WINDOW_HEIGHT = 1000;

  private IAnimatorModelView model;

  /**
   * Convinience Constructor.
   */
  public VisualView() {
    this.model = new AnimatorModel();
  }

  /**
   * Constructs a VisualView.
   *
   * @param model    the model.
   */
  public VisualView(IAnimatorModelView model) {
    this.model = model;
  }

  @Override
  public void display() {
    this.setTitle("Animator");
    this.setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());

    AnimatorPanel panel = new AnimatorPanel(model);
    panel.setPreferredSize(new Dimension(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT));
    JScrollPane scrollPane = new JScrollPane(panel);
    this.add(scrollPane, BorderLayout.CENTER);

    this.setVisible(true);

    while (true) {
      this.repaint();
    }
  }

  /**
   * Change this IView's model field to that IAnimatorModelView.
   */
  public void setModel(IAnimatorModelView model) {
    this.model = model;
  }

  public void setOutput(Appendable output) {
    throw new UnsupportedOperationException("Cannot set an output for a VisualView: always displays"
            + " to the screen.");
  }

  @Override
  public JTextField getTickInput() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setTickRateLabel(String s) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Appendable getOutput() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setController(IController controller) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void svgDisplay() {
    throw new UnsupportedOperationException();
  }
}
