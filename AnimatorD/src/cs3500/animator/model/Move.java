package cs3500.animator.model;

import java.util.Objects;

import static cs3500.animator.model.Utils.round;

public class Move extends AbsAnimation {
  private double newPosX;
  private double newPosY;
  private double startPosX;
  private double startPosY;

  /**
   * Constructor.
   *
   * @param name    this animation's name. Should be the same as the shape it affects.
   * @param bgn     this animation's beginning time.
   * @param end     this animation's ending time.
   * @param newPosX the new X position.
   * @param newPosY the new Y position.
   */
  public Move(String name, int bgn, int end, double startPosX,
              double startPosY, double newPosX, double newPosY) {
    super(name, bgn, end, AnimType.MOVE);
    this.newPosX = newPosX;
    this.newPosY = newPosY;
    this.startPosX = startPosX;
    this.startPosY = startPosY;
  }

  /**
   * Copy constructor.
   */
  public Move(Move anim) {
    this(anim.getName(), anim.getBgn(), anim.getEnd(), anim.getStartPosX(), anim.getStartPosY(),
            anim.getNewPosX(), anim.getNewPosY());
  }

  //Used in testing
  /**
   * Constructor.
   *
   * @param name    this animation's name. Should be the same as the shape it affects.
   * @param newPosY the new Y position.
   */
  public Move(String name, double newPosX, double newPosY) {
    super(name);
    this.newPosX = newPosX;
    this.newPosY = newPosY;
    this.type = AnimType.MOVE;
  }

  @Override
  public AbsAnimation cloneAnim() {
    return new Move(this);
  }

  @Override
  public void animateForT(int t) {
    if (shape != null && t >= bgn && t <= end) {
      double tilEnd = end - t;
      double duration = end - bgn;
      double sinceBgn = t - bgn;
      double tweenedPosX = startPosX * (tilEnd / duration)
              + newPosX * (sinceBgn / duration);
      double tweenedPosY = startPosY * (tilEnd / (double) duration)
              + newPosY * (sinceBgn / duration);
      shape.changePos(tweenedPosX, tweenedPosY);
    } else if (t > end) {
      shape.changePos(newPosX, newPosY);
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
      result += this.name + " moves from (" + round(this.startPosX) + ","
              + round(this.startPosY) + ") to ("
              + round(this.newPosX) + "," + round(this.newPosY)
              + ") at t=" + this.bgn + " to t=" + this.end;
    } else {
      result += "This animation has no shape to work on";
    }
    return result;
  }

  @Override
  public String toString(double tickRate) {
    String result = "";
    if (shape != null) {
      result += "Shape " + this.name + " moves from (" + round(this.startPosX) + ","
              + round(this.startPosY) + ")"
              + " to (" + round(this.newPosX) + "," + round(this.newPosY) + ") from t="
              + (this.bgn / tickRate) + "s" + " to t=" + (this.end / tickRate) + "s";
    } else {
      result += "This animation has no shape to work on";
    }
    return result;
  }

  /**
   * Getter for the startPosX of a shape.
   */
  public double getStartPosX() {
    return startPosX;
  }

  /**
   * Getter for the startPosY of a shape.
   */
  public double getStartPosY() {
    return startPosY;
  }

  @Override
  public String toSVG() {
    Double spX = startPosX;
    int sX = spX.intValue();
    Double spY = startPosY;
    int sY = spY.intValue();
    Double npX = newPosX;
    int nX = npX.intValue();
    Double npY = newPosY;
    int nY = npY.intValue();

    //IF statement for looping in SVG format
    if (this.loop) {
      if (startPosY == newPosY) {
        return String.format("\t\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                        + "attributeName=\"x\" from=\"%d\" to=\"%d\" fill=\"freeze\" "
                        + "repeatCount=\"indefinite\"/>",
                bgn, end - bgn, sX, nX);
      } else if (startPosX == newPosX) {
        return String.format("\t\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                        + "attributeName=\"y\" from=\"%d\" to=\"%d\" fill=\"freeze\" "
                        + "repeatCount=\"indefinite\"/>",
                bgn, end - bgn, sY, nY);
      } else {
        return String.format("\t\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                        + "attributeName=\"x\" from=\"%d\" to=\"%d\" fill=\"freeze\" />%n"
                        + "\t\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                        + "attributeName=\"y\" from=\"%d\" to=\"%d\" fill=\"freeze\" "
                        + "repeatCount=\"indefinite\"/>",
                bgn, end - bgn, sX, nX,
                bgn, end - bgn, sY, nY);
      }
    }

    if (startPosY == newPosY) {
      return String.format("\t\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                      + "attributeName=\"x\" from=\"%d\" to=\"%d\" fill=\"freeze\" />",
              bgn, end - bgn, sX, nX);
    } else if (startPosX == newPosX) {
      return String.format("\t\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                      + "attributeName=\"y\" from=\"%d\" to=\"%d\" fill=\"freeze\" />",
              bgn, end - bgn, sY, nY);
    } else {
      return String.format("\t\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                      + "attributeName=\"x\" from=\"%d\" to=\"%d\" fill=\"freeze\" />%n"
                      + "\t\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                      + "attributeName=\"y\" from=\"%d\" to=\"%d\" fill=\"freeze\" />",
              bgn, end - bgn, sX, nX,
              bgn, end - bgn, sY, nY);
    }
  }

  public double getNewPosX() {
    return newPosX;
  }

  /**
   * Getter for this.newPosY.
   * @return this.newPosY.
   */
  public double getNewPosY() {
    return newPosY;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Move)) {
      return false; }
    Move that = (Move) other;
    return that.hashCode() == this.hashCode();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.bgn, this.end, this.startPosY, this.startPosX,
            this.newPosX, this.newPosY);
  }
}
