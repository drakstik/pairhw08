package cs3500.animator.model;

import util.TweenModelBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the animator model that dictates the animations and shapes to be shown.
 */
public class AnimatorModel implements IAnimatorModel, IAnimatorModelView {
  ArrayList<AbsMyShape> shapes = new ArrayList<>();
  ArrayList<AbsMyShape> initShapes = new ArrayList<>();
  ArrayList<AbsAnimation> initAnims = new ArrayList<>();
  ArrayList<AbsAnimation> anims = new ArrayList<>();

  private boolean loop = false;

  /**
   * Convenience constructor. Initializes fields as empty ArrayLists.
   */
  public AnimatorModel() {
    //USED IN TESTS
  }

  // CHANGED FROM A5: Consolidated data validation into validateAnim, validateShape.

  /**
   * Construct an AnimatorModel.
   *
   * @param shapes a list of shapes.
   * @param anims  a list of animations.
   */
  public AnimatorModel(ArrayList<AbsMyShape> shapes, ArrayList<AbsAnimation> anims) {

    //All shapes must have unique names
    //Note: Took out the check if shapes.size > 1 so all shapes run through validateShape().
    for (int i = 0; i < shapes.size(); i++) {
      AbsMyShape s = shapes.get(i);
      try {
        //fill initShape everytime you add shape.copy()
        this.initShapes.add(validateShape(this.initShapes, s));
        this.shapes.add(validateShape(this.shapes, s.makeCopy()));
      } catch (IllegalArgumentException e) {
        System.out.println("Given shape shares a name with a pre-existing shape.");
      }
    }

    //TODO test this constructor
    // makes sure that if anims = [a, a2, ...], and if "a" and "a2":
    //           -have the same name
    //           -adhere to: -a.bgn < a.end < a2.bgn < a2.end
    //                       -a2.bgn < a2.end < a.bgn < a.end
    //           -"a" and "a2" are not the same animation
    //           -"a" and "a2" are not instances of Appear and/or Disappear
    // if all above specifications pass, then "a" can be added to anims.
    for (int i = 0; i < anims.size(); i++) {
      AbsAnimation a = anims.get(i);
      try {
        this.initAnims.add(validateAnim(this.initAnims, a));
        this.addAnim(a);
      } catch (IllegalArgumentException e) {
        System.out.println(e);
      }
    }

    //Assigns a shape to each corresponding animation
    for (AbsAnimation anim : anims) {
      this.giveCorrectShape(anim);
    }

    //Assigns a shape to each corresponding initAnimation
    for (AbsAnimation anim : initAnims) {
      this.giveCorrectShape(anim);
    }
  }


  public static final class Builder implements TweenModelBuilder<AnimatorModel> {
    private final AnimatorModel model;

    /**
     * Constructs with a model with all fields as empty ArrayLists.
     */
    public Builder() {
      this.model = new AnimatorModel();
    }


    @Override
    public TweenModelBuilder<AnimatorModel> addOval(String name, float cx, float cy, float xRadius,
                                                    float yRadius, float red, float green,
                                                    float blue, int startOfLife, int endOfLife) {
      MyOval o = new MyOval(name, cx, cy, xRadius * 2, yRadius * 2,
              new ShapeColor(red, green, blue),
              false, true);
      this.model.initShapes.add(
              model.validateShape(model.shapes, o));
      this.model.shapes.add(
              model.validateShape(model.shapes, o));

      Appear ovalApp = new Appear(name, startOfLife, endOfLife - 1);
      ovalApp.giveShape(o);
      model.initAnims.add(model.validateAnim(model.initAnims, ovalApp).cloneAnim());
      this.model.anims.add(
              model.validateAnim(model.anims, ovalApp));

      Disappear ovalDisapp = new Disappear(name, endOfLife, endOfLife);
      ovalDisapp.giveShape(o);
      model.initAnims.add(model.validateAnim(model.initAnims, ovalDisapp).cloneAnim());
      this.model.anims.add(
              model.validateAnim(model.anims, ovalDisapp));
      return this;
    }

    @Override
    public TweenModelBuilder<AnimatorModel> addRectangle(String name, float lx, float ly,
                                                         float width, float height, float red,
                                                         float green, float blue, int startOfLife,
                                                         int endOfLife) {
      MyRectangle r = new MyRectangle(name, lx, ly, width, height, new ShapeColor(red, green, blue),
              false, true);
      this.model.initShapes.add(
              model.validateShape(model.shapes, r));
      this.model.shapes.add(
              model.validateShape(model.shapes, r));

      Appear rectApp = new Appear(name, startOfLife, endOfLife - 1);
      rectApp.giveShape(r);
      model.initAnims.add(model.validateAnim(model.initAnims, rectApp).cloneAnim());
      this.model.anims.add(
              model.validateAnim(model.anims, rectApp));

      Disappear rectDisapp = new Disappear(name, endOfLife, endOfLife);
      rectDisapp.giveShape(r);
      model.initAnims.add(model.validateAnim(model.initAnims, rectDisapp).cloneAnim());
      this.model.anims.add(
              model.validateAnim(model.anims, rectDisapp));
      return this;
    }

    @Override
    public TweenModelBuilder<AnimatorModel> addMove(String name, float moveFromX, float moveFromY,
                                                    float moveToX, float moveToY, int startTime,
                                                    int endTime) {
      Move newMove = new Move(name, startTime, endTime, moveFromX, moveFromY, moveToX, moveToY);
      model.giveCorrectShape(newMove);
      model.initAnims.add(model.validateAnim(model.initAnims, newMove).cloneAnim());
      model.anims.add(
              model.validateAnim(model.anims, newMove));
      return this;
    }

    @Override
    public TweenModelBuilder<AnimatorModel> addColorChange(String name, float oldR, float oldG,
                                                           float oldB, float newR,
                                                           float newG, float newB,
                                                           int startTime, int endTime) {
      ChangeColor newCC = new ChangeColor(name, startTime, endTime,
              new ShapeColor(oldR, oldG, oldB), new ShapeColor(newR, newG, newB));
      model.giveCorrectShape(newCC);
      model.initAnims.add(model.validateAnim(model.initAnims, newCC).cloneAnim());
      model.anims.add(model.validateAnim(model.anims, newCC));
      return this;
    }

    @Override
    public TweenModelBuilder<AnimatorModel> addScaleToChange(String name, float fromSx,
                                                             float fromSy, float toSx, float toSy,
                                                             int startTime, int endTime) {
      Scale newScale = new Scale(name, startTime, endTime, fromSx, fromSy, toSx, toSy);
      model.giveCorrectShape(newScale);
      model.initAnims.add(model.validateAnim(model.initAnims, newScale).cloneAnim());
      model.anims.add(model.validateAnim(model.anims, newScale));
      return this;
    }

    @Override
    public AnimatorModel build() {
      return this.model;
    }
  }

  private void giveCorrectShape(AbsAnimation anim) {
    for (IMyShape shape : this.shapes) {
      if (anim.getName().equals(shape.getName())) {
        anim.giveShape(shape);
      }
    }
  }

  /**
   * Used to validate the anims in this constructor.
   *
   * @param anims the animations.
   * @param a     a single animation to be checked against all other
   * @return the answer to the question: should "a" be allowed to be on this.anims.
   */

  public AbsAnimation validateAnim(List<AbsAnimation> anims, AbsAnimation a) {

    if (anims.size() > 0) {
      for (AbsAnimation anim : anims) {
        if (anim.getName().equals(a.getName())) { //if they both operate on the same shape
          if (anim.getType() == a.getType() && !(anim.getEnd() < a.getBgn()
                  || a.getEnd() < anim.getBgn())) {
            throw new IllegalArgumentException("Animation: [" + a.toString()
                    + "] is clashing with pre-existing animation [" + anim.toString() + "]");
          }

          if (((anim.getType() == AbsAnimation.AnimType.DISAPPEAR || anim.getType()
                  == AbsAnimation.AnimType.APPEAR)
                  && (a.getType() == AbsAnimation.AnimType.DISAPPEAR || a.getType()
                  == AbsAnimation.AnimType.APPEAR)) && !(anim.getEnd() < a.getBgn()
                  || a.getEnd() < anim.getBgn())) {
            throw new IllegalArgumentException("Animation: [" + a.toString()
                    + "] is clashing with pre-existing animation [" + anim.toString() + "]");
          }
        }
      }
    }
    return a;
  }

  /**
   * Is the given shape a valid entry to this.shapes? Check the name of s is unique amongst the
   * shapes.
   *
   * @param shapes the list of shapes to check s' name against.
   * @param s      the shape in question.
   * @return the answer to: is s a unique shape amongst shapes.
   */
  public AbsMyShape validateShape(List<AbsMyShape> shapes, AbsMyShape s) {

    for (AbsMyShape s2 : shapes) {
      if (s2.getName().equals(s.getName())) {
        throw new IllegalArgumentException("Invalid IMyShape given to validateShape.");
      }
    }
    s.makeInvisible();
    return s;
  }

  //CHANGED FROM A5: This method replaces showScene()

  /**
   * Returns the scene as a String at controller-specified time.
   *
   * @param time the time of the scene.
   */
  public String snapshot(int time) {

    for (int i = 0; i < initShapes.size(); i++) {
      IMyShape s = initShapes.get(i);
      this.shapes.set(i, s.makeCopy());
    }

    //Assigns a shape to each corresponding animation
    for (AbsAnimation anim : anims) {
      for (IMyShape shape : this.shapes) {
        if (anim.getName().equals(shape.getName())) {
          anim.giveShape(shape);
        }
      }
    }

    //animating each anim forward once
    for (AbsAnimation anim : anims) {
      anim.animateForT(time);
    }


    String result = "Shapes: \n";
    for (IMyShape shape : shapes) {
      if (shape == shapes.get(shapes.size() - 1)) {
        result += shape.toString();
      } else {
        result += shape.toString() + "\n\n";
      }
    }

    return result;
  }

  /**
   * Getter for the anims field.
   *
   * @return this.anims
   */
  public List<AbsAnimation> getAnims() {
    return anims;
  }

  @Override
  public ArrayList<AbsMyShape> getShapes() {
    return this.shapes;
  }

  /**
   * Give a description of this animator model.
   *
   * @return a string description of this animator model.
   */

  public String describeAnimator() {
    String s = "Shapes:\n";

    for (IMyShape shape : initShapes) {
      s += (shape.toString() + "\n");
      for (AbsAnimation anim : anims) {
        if ((anim.getType() == AbsAnimation.AnimType.APPEAR || anim.getType()
                == AbsAnimation.AnimType.DISAPPEAR) && anim.getName().equals(shape.getName())) {
          s += (anim.toString() + "\n");
        }
      }
      s += "\n";
    }

    for (AbsAnimation anim : anims) {
      if ((anim.getType() != AbsAnimation.AnimType.APPEAR && anim.getType()
              != AbsAnimation.AnimType.DISAPPEAR)) {
        s += (anim.toString() + "\n");
      }
    }

    return s.substring(0, s.length() - 1);
  }

  // ADDED SINCE A5
  //Insert an Animation into this.anims, preserving chronological order.
  public void addAnim(AbsAnimation a) {
    anims.add(validateAnim(this.anims, a));
    anims.sort(new AnimComp());
  }

  /**
   * Gives the end time of the animation.
   *
   * @return the latest endTime of any AbsAnimation in the anims field.
   */
  public int getEndTime() {
    int biggest = 0;
    int thisEnd = 0;
    for (AbsAnimation a : anims) {
      thisEnd = a.getEnd();
      if (thisEnd > biggest) {
        biggest = thisEnd;
      }
    }
    return biggest;
  }

  @Override
  public boolean getLoop() {
    return this.loop;
  }

  @Override
  public void setLoop(boolean loop) {
    this.loop = loop;
    for (AbsAnimation anim : anims) {
      anim.setLoop(loop);
    }
  }

  @Override
  public ArrayList<AbsAnimation> getInitAnims() {
    ArrayList<AbsAnimation> result = new ArrayList<>(initAnims.size());

    for (AbsAnimation anim : initAnims) {
      result.add(anim.cloneAnim());
    }
    return result;
  }
}