package cs3500.animator.view;

import java.io.IOException;

import javax.swing.JTextField;

import cs3500.animator.controller.IController;
import cs3500.animator.model.AbsAnimation;
import cs3500.animator.model.AbsMyShape;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorModelView;
import cs3500.animator.model.IMyShape;

/**
 * A class for a SVG formated representation of an AnimatorModel.
 */
public class SVGView implements IView {
  private IAnimatorModelView model;
  private Appendable output;

  /**
   * Default constructor, used in ViewFactory.
   */
  public SVGView() {
    this.model = new AnimatorModel(); //all fields are empty ArrayLists
    this.output = new StringBuilder();
  }

  /**
   * Constructor.
   */
  public SVGView(IAnimatorModelView model, Appendable output) {
    this.model = model;
    this.output = output;
  }

  @Override
  public void display() {
    StringBuilder bgnLines = new StringBuilder();

    bgnLines.append(String.format("<?xml version=\"1.0\"?>%n<svg width=\"1200\" height=\"1200"));
    bgnLines.append("\" viewPort=\"0 0 1200 1200\" version=\"1.1\" ");
    bgnLines.append(String.format("xmlns=\"http://www.w3.org/2000/svg\">%n"));

    try {
      output.append(bgnLines);
    } catch (IOException e) {
      throw new IllegalStateException("This SVGView was not able to append to output");
    }

    for (AbsMyShape shape : model.getShapes()) {
      StringBuilder shapeString = new StringBuilder();
      shapeString.append(shape.toSVG() + "\n");

      for (AbsAnimation anim : model.getAnims()) {
        if (anim.getName().equals(shape.getName())
                && (anim.getType() == AbsAnimation.AnimType.APPEAR)) {
          String appear = String.format("\t\t<animate attributeType=\"XML\" attributeName="
                  + "\"visibility\" from=\"hidden\" to=\"visible\" begin=\"%d\" dur=\"0.001s\" "
                  + "fill=\"freeze\"/>%n", anim.getBgn());
          shapeString.append(appear);
        } else if (anim.getName().equals(shape.getName())
                && (anim.getType() == AbsAnimation.AnimType.DISAPPEAR)) {
          String disappear = String.format("\t\t<animate attributeType=\"XML\" attributeName="
                  + "\"visibility\" from=\"visible\" to=\"hidden\" begin=\"%d\" dur=\"0.001s\" "
                  + "fill=\"freeze\"/>%n", anim.getBgn());
          shapeString.append(disappear);
        } else if (anim.getName().equals(shape.getName())) {
          shapeString.append(anim.toSVG() + "\n");
        }
      }

      if (shape.getShapeType() == IMyShape.ShapeType.RECTANGLE) {
        shapeString.append("</rect>\n"); //closing a shape
      } else if (shape.getShapeType() == IMyShape.ShapeType.OVAL) {
        shapeString.append("</ellipse>\n");
      }

      try {
        output.append(shapeString);
      } catch (IOException e) {
        throw new IllegalStateException("A shape was not able to get appended to the output"
                + " of this SVGView");
      }
    }

    try {
      output.append("</svg>");
    } catch (IOException e) {
      throw new IllegalStateException("Closing line was not able to get appended to the output"
              + " of this SVGView");
    }
  }

  @Override
  public void setModel(IAnimatorModelView model) {
    this.model = model;
  }

  @Override
  public void setOutput(Appendable output) {
    this.output = output;
  }

  @Override
  public JTextField getTickInput() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setTickRateLabel(String s) {
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
  public void svgDisplay() {
    throw new UnsupportedOperationException();
  }
}
