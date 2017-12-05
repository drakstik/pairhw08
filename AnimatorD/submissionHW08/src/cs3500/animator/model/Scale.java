package cs3500.animator.model;

import java.util.Objects;

import static cs3500.animator.model.Utils.round;

public class Scale extends AbsAnimation {
  private double startWidth;
  private double startHeight;
  private double newWidth;
  private double newHeight;

  //TODO test each of the constructors

  /**
   * Constructor.
   *
   * @param name      this animation's name. Should be the same as the shape it affects.
   * @param bgn       this animation's beginning time.
   * @param end       this animation's ending time.
   * @param newWidth  The new width this animation is supposed to scale a shape by
   * @param newHeight the new height this animation is supposed to scale a shape by
   */
  public Scale(String name, int bgn, int end, double startWidth, double startHeight,
               double newWidth, double newHeight) {
    super(name, bgn, end, AnimType.SCALE);
    if (newWidth > 0) {
      this.newWidth = newWidth;
    } else {
      throw new IllegalArgumentException("Error in creating Scale(): newWidth is below 0");
    }

    if (newHeight > 0) {
      this.newHeight = newHeight;
    } else {
      throw new IllegalArgumentException("Error in creating Scale(): newHeight is below 0");
    }

    if (startWidth > 0) {
      this.startWidth = startWidth;
    } else {
      throw new IllegalArgumentException("Error creating a Move(): newPosY is below 0");
    }

    if (startWidth > 0) {
      this.startHeight = startHeight;
    } else {
      throw new IllegalArgumentException("Error creating a Move(): newPosY is below 0");
    }
  }

  /**
   * Copy constructor.
   */
  public Scale(Scale anim) {
    this(anim.getName(), anim.getBgn(), anim.getEnd(), anim.getStartHeight(), anim.getStartWidth(),
            anim.getNewWidth(), anim.getNewHeight());
  }

  @Override
  public AbsAnimation cloneAnim() {
    return new Scale(this);
  }

  @Override
  public void animateForT(int t) {
    if (shape != null && t >= bgn && t <= end) {
      double tilEnd = end - t;
      double duration = end - bgn;
      double sinceBgn = t - bgn;
      double tweenedWidth = startWidth * (tilEnd / duration)
              + newWidth * (sinceBgn / duration);
      double tweenedHeight = startHeight * (tilEnd / (double) duration)
              + newHeight * (sinceBgn / duration);
      shape.scale(tweenedWidth, tweenedHeight);
    } else if (t > end) {
      shape.scale(newWidth, newHeight);
    } else if (shape == null) {
      throw new IllegalArgumentException("This animation was not given a shape before "
              + "animateForT() was called on it");
    }
  }

  /**
   * Give (mutation) this animation a new shape to work on and ensures this animation has the same
   * name as the given shape.
   *
   * @param shape the shape
   */
  public void giveShape(IMyShape shape) {
    this.shape = shape;
    this.name = shape.getName();
  }

  @Override
  public String toString() {
    String result = "";
    if (shape != null) {
      result += this.name + " changes (width, height) from (" + round(this.startWidth) + ","
              + round(this.startHeight) + ") to (" + round(this.newWidth) + ","
              + round(this.newHeight)
              + ") at t=" + this.bgn
              + " to t=" + this.end;
    } else {
      result += "This animation has no shape to work on";
    }
    return result;
  }

  @Override
  public String toString(double tickRate) {
    String result = "";
    if (shape != null) {
      result += "Shape " + this.name + " scales from Width:" + round(this.startWidth)
              + ", Height: "
              + round(this.startHeight) + " to Width: " + round(this.newWidth) + ", Height: "
              + round(this.newHeight)
              + " from t=" + (this.bgn / tickRate) + "s"
              + " to t=" + (this.end / tickRate) + "s";
    } else {
      result += "This animation has no shape to work on";
    }
    return result;
  }

  /**
   * Getter for the startWidth of a shape.
   */
  public double getStartWidth() {
    return startWidth;
  }

  /**
   * Getter for the startHeight of a shape.
   */
  public double getStartHeight() {
    return startHeight;
  }

  @Override
  public String toSVG() {
    Double strW = startWidth;
    int sW = strW.intValue();
    Double strH = startHeight;
    int sH = strH.intValue();
    Double nwW = newWidth;
    int nW = nwW.intValue();
    Double nwH = newHeight;
    int nH = nwH.intValue();

    //IF statement for looping in SVG format
    if (this.loop) {
      if (startHeight == newHeight) {
        return String.format("\t\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                        + "attributeName=\"width\" from=\"%d\" to=\"%d\" fill=\"freeze\" "
                        + "repeatCount=\"indefinite\"/>",
                bgn, end - bgn, sW, nW);
      } else if (startWidth == newWidth) {
        return String.format("\t\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                        + "attributeName=\"height\" from=\"%d\" to=\"%d\" fill=\"freeze\" "
                        + "repeatCount=\"indefinite\"/>",
                bgn, end - bgn, sH, nH);
      } else {
        return String.format("\t\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                        + "attributeName=\"width\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n"
                        + "\t\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                        + "attributeName=\"height\" from=\"%d\" to=\"%d\" fill=\"freeze\" "
                        + "repeatCount=\"indefinite\"/>",
                bgn, end - bgn, sW, nW, bgn, end - bgn, sH, nH);
      }
    }

    if (startHeight == newHeight) {
      return String.format("\t\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
              + "attributeName=\"width\" from=\"%d\" to=\"%d\" fill=\"freeze\" />",
              bgn, end - bgn, sW, nW);
    } else if (startWidth == newWidth) {
      return String.format("\t\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
              + "attributeName=\"height\" from=\"%d\" to=\"%d\" fill=\"freeze\" />",
              bgn, end - bgn, sH, nH);
    } else {
      return String.format("\t\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                      + "attributeName=\"width\" from=\"%d\" to=\"%d\" fill=\"freeze\" />"
                      + "\n\t\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                      + "attributeName=\"height\" from=\"%d\" to=\"%d\" fill=\"freeze\" />",
              bgn, end - bgn, sW, nW, bgn, end - bgn, sH, nH);
    }
  }

  public double getNewWidth() {
    return newWidth;
  }

  public double getNewHeight() {
    return newHeight;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Scale)) {
      return false; }
    Scale that = (Scale) other;
    return that.hashCode() == this.hashCode();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.bgn, this.end, this.startWidth, this.startHeight,
            this.newWidth, this.newHeight);
  }
}
