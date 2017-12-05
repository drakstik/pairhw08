package cs3500.animator.model;

public abstract class AbsAnimation implements IAnimation {
  protected int bgn;
  protected int end;
  protected int duration;
  protected String name;
  protected IMyShape shape;
  protected AnimType type;

  protected boolean loop = false;
  //  private ShapeColor prevCol;
  //  private ShapeColor newCol;

  /**
   * Constructor.
   * @param name this animation's name. Should be the same as the shape it affects.
   * @param bgn  the time this animation begins at.
   * @param end  the time this animation ends at.
   */
  public AbsAnimation(String name, int bgn, int end, AnimType type) {
    //TODO test the constructors
    this.type = type;
    this.name = name;
    if (this.type == AnimType.APPEAR || this.type == AnimType.DISAPPEAR) {
      if (bgn <= end) {
        this.bgn = bgn;
        this.end = end;
      } else {
        throw new IllegalArgumentException("bgn has to be less or equal to end!");
      }
    } else {
      if (bgn < end) {
        this.bgn = bgn;
        this.end = end;
      } else {
        throw new IllegalArgumentException("bgn has to be less or equal to end!");
      }
    }
    this.duration = this.end - this.bgn;
  }

  //If animations must have bgn and end then this constructor is useless
  /**
   * Constructor.
   * @param name     this animation's name. Should be the same as the shape it affects.
   * @param duration this animation's duration.
   */
  public AbsAnimation(String name, int duration) {
    this.name = name;
    this.duration = duration;
  }

  /**
   * Constructor.
   * @param name this animation's name. Should be the same as the shape it affects.
   */
  public AbsAnimation(String name) {
    this.name = name;
  }

  /**
   * Turn this animation into a string, with time in secs.
   * @param tickRate tickRate used to calculate seconds.
   * @return this animation into a string, with time in secs
   */
  public abstract String toString(double tickRate);

  /**
   * Enum representing all the different types of anims.
   */
  public enum AnimType {
    MOVE, SCALE, APPEAR, DISAPPEAR, CHANGECOLOR
  }

  public AbsAnimation(IMyShape shape) {
    this.shape = shape;
    this.name = shape.getName();
  }

  @Override
  public int getBgn() {
    return this.bgn;
  }

  @Override
  public int getEnd() {
    return this.end;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getDuration() {
    return this.duration;
  }

  @Override
  public abstract void animateForT(int t);

  @Override
  public int ticksSinceBgn(int time) {
    int x = time - bgn;

    return x;
  }

  @Override
  public AnimType getType() {
    return this.type;
  }

  @Override
  public IMyShape getShape() {
    return this.shape;
  }

  @Override
  public String toSVG() {
    return "";
  }

  @Override
  public boolean getLoop() {
    return this.loop;
  }

  @Override
  public void setLoop(boolean loop) {
    this.loop = loop;
  }
}
