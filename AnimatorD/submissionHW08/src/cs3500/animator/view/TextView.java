package cs3500.animator.view;

import cs3500.animator.controller.IController;
import cs3500.animator.model.AbsAnimation;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorModelView;
import cs3500.animator.model.IMyShape;

import java.io.IOException;

import javax.swing.JTextField;

/**
 * A class for a text-based representation for an AnimatorModel.
 */
public class TextView implements IView {
  private IAnimatorModelView model;
  private double tickRate; //number of ticks per second
  private Appendable output;

  /**
   * Constructor.
   */
  public TextView() {
    this.model = new AnimatorModel();
    tickRate = 1;
    output = new StringBuilder();
  }

  /**
   * Constructor.
   *
   * @param model    the model to work on.
   * @param tickRate the number of ticks per second.
   */
  public TextView(IAnimatorModelView model, int tickRate) {
    this.model = model;
    this.tickRate = tickRate;
    output = new StringBuilder();
  }

  @Override
  public void display() {
    try {
      output.append(this.textDisplay());
    } catch (IOException e) {
      System.out.println("Encountered an internal input/output error while displaying "
              + "the animation in text.");
    }
  }

  /**
   * Describe this textview's model.
   *
   * @return A string representation of this textView's model.
   */
  public String textDisplay() {

    String s = "Shapes:\n";

    for (IMyShape shape : model.getShapes()) {
      s += (shape.toString() + "\n");
      for (AbsAnimation anim : model.getAnims()) {
        if ((anim.getType() == AbsAnimation.AnimType.APPEAR || anim.getType()
                == AbsAnimation.AnimType.DISAPPEAR) && anim.getName().equals(shape.getName())) {
          s += (animToString(anim) + "\n");
        }
      }
      s += "\n";
    }

    for (AbsAnimation anim : model.getAnims()) {
      if ((anim.getType() != AbsAnimation.AnimType.APPEAR && anim.getType()
              != AbsAnimation.AnimType.DISAPPEAR)) {
        s += (animToString(anim) + "\n");
      }
    }

    return s.substring(0, s.length() - 1);
  }

  /**
   * Turns an animation into a string.
   * @param a the animation.
   * @return a String representation of the given animation.
   */
  public String animToString(AbsAnimation a) {
    String result = "";
    switch (a.getType()) {
      case APPEAR:
        result += a.toString(tickRate);
        return result;
      case DISAPPEAR:
        result += a.toString(tickRate);
        return result;
      case CHANGECOLOR:
        result += a.toString(tickRate);
        return result;
      case MOVE:
        result += a.toString(tickRate);
        return result;
      case SCALE:
        result += a.toString(tickRate);
        return result;
      default:
        throw new IllegalArgumentException("Bad animation passed through");
    }
  }

  /**
   * Change this IView's model field to that IAnimatorModelView.
   * @param model the model for this IView to use.
   */
  public void setModel(IAnimatorModelView model) {
    this.model = model;
  }

  public void setOutput(Appendable output) {
    this.output = output;
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
    return output;
  }

  @Override
  public void setController(IController controller) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getXMLData() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void svgDisplay() {
    throw new UnsupportedOperationException();
  }
}
