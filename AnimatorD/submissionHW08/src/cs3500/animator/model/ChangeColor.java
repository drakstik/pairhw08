package cs3500.animator.model;

import java.util.Objects;

import static cs3500.animator.model.Utils.round;

//CHANGED FROM A5: Class no longer public; fields & some returns are typed ShapeColor instead
//of java.awt.Color
public class ChangeColor extends AbsAnimation {
  ShapeColor newCol;
  ShapeColor prevCol;


  /**
   * Constructor.
   *
   * @param name   this animation's name. Should be the same as the shape it affects.
   * @param bgn    This animation's beginning time.
   * @param end    This animation's ending time.
   * @param newCol the new Color for this animation.
   */
  public ChangeColor(String name, int bgn, int end, ShapeColor prevCol, ShapeColor newCol) {
    super(name, bgn, end, AnimType.CHANGECOLOR);
    this.newCol = newCol;
    this.prevCol = prevCol;
  }

  /**
   * Copy constructor.
   */
  public ChangeColor(ChangeColor anim) {
    this(anim.getName(), anim.getBgn(), anim.getEnd(), anim.getPrevCol(), anim.getNewCol());
  }

  @Override
  public String toString() {
    String result = "";
    if (shape != null) {
      result += this.name + " changes color from ("
              + round(prevCol.r) + "," + round(prevCol.g) + "," + round(prevCol.b)
              + ") to (" + round(newCol.r) + "," + round(newCol.g) + "," + round(newCol.b)
              + ") at t=" + this.bgn;
    } else {
      result += "This animation has no shape to work on";
    }
    return result;
  }

  @Override
  public String toString(double tickRate) {
    String result = "";
    if (shape != null) {
      result += "Shape " + this.name + " changes color from ("
              + round(prevCol.r) + "," + round(prevCol.g) + "," + round(prevCol.b)
              + ") to (" + round(newCol.r) + "," + round(newCol.g) + "," + round(newCol.b)
              + ") from t=" + (this.bgn / tickRate) + "s to t=" + (this.end / tickRate) + "s";
    } else {
      result += "This animation has no shape to work on";
    }
    return result;
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
  public AbsAnimation cloneAnim() {
    return new ChangeColor(this);
  }

  @Override
  public void animateForT(int t) {
    if (shape != null && t >= bgn && t <= end) {
      double tilEnd = end - t;
      double duration = end - bgn;
      double sinceBgn = t - bgn;
      double tweenedColR = prevCol.r * (tilEnd / duration)
              + newCol.r * (sinceBgn / duration);
      double tweenedColG = prevCol.g * (tilEnd / (double) duration)
              + newCol.g * (sinceBgn / duration);
      double tweenedColB = prevCol.b * (tilEnd / (double) duration)
              + newCol.b * (sinceBgn / duration);
      shape.changeColor(new ShapeColor(tweenedColR, tweenedColG, tweenedColB));
    } else if (t > end) {
      shape.changeColor(newCol);
    } else if (shape == null) {
      throw new IllegalArgumentException("This animation was not given a shape before "
              + "animateForT() was called on it");
    }
  }

  /**
   * Getter for the prevCol.
   * @return this.prevCol.
   */
  public ShapeColor getPrevCol() {
    return prevCol;
  }

  @Override
  public String toSVG() {
    Double pcr = prevCol.r * 255;
    int pr = pcr.intValue();
    Double pcg = prevCol.g * 255;
    int pg = pcg.intValue();
    Double pcb = prevCol.b * 255;
    int pb = pcb.intValue();

    Double ncr = newCol.r * 255;
    int nr = ncr.intValue();
    Double ncg = newCol.g * 255;
    int ng = ncg.intValue();
    Double ncb = newCol.b * 255;
    int nb = ncb.intValue();

    //IF statement for looping in SVG format
    if (this.loop) {
      return String.format("\t\t<animate attributeType=\"XML\" attributeName=\"fill\" "
              + "from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\" begin=\"%d\" dur=\"%d\" "
              + "fill=\"freeze\" repeatCount=\"indefinite\"/>", pr, pg, pb, nr, ng, nb,
              bgn, end - bgn);
    } else {
      return String.format("\t\t<animate attributeType=\"XML\" attributeName=\"fill\" "
              + "from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\" begin=\"%d\" dur=\"%d\" "
              + "fill=\"freeze\"/>", pr, pg, pb, nr, ng, nb, bgn, end - bgn);
    }
  }

  /**
   * Getter for the newCol.
   * @return this.newCol.
   */
  public ShapeColor getNewCol() {
    return newCol;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof ChangeColor)) {
      return false; }
    ChangeColor that = (ChangeColor) other;
    return that.hashCode() == this.hashCode();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.bgn, this.end, this.prevCol, this.newCol);
  }
}
