
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import cs3500.animator.model.AbsAnimation;
import cs3500.animator.model.AbsMyShape;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.Appear;
import cs3500.animator.model.ChangeColor;
import cs3500.animator.model.Disappear;
import cs3500.animator.model.Move;
import cs3500.animator.model.MyOval;
import cs3500.animator.model.Scale;
import cs3500.animator.model.ShapeColor;

import static junit.framework.TestCase.assertEquals;

public class AnimatorModelTest {
  //Testing a true case of validateShape and a false case.
  @Test
  public void validateShapeTest1() {
    MyOval o = new MyOval("o", 10.0, 10.0, 10.0, 10.0, new ShapeColor(0, 0, 1), true, true);
    MyOval o1 = new MyOval("o1", 10.0, 10.0, 10.0, 10.0, new ShapeColor(0, 0, 1), true, true);
    MyOval o2 = new MyOval("o1", 10.0, 10.0, 10.0, 10.0, new ShapeColor(0, 0, 1), true, true);
    MyOval o3 = new MyOval("o3", 10.0, 10.0, 10.0, 10.0, new ShapeColor(0, 0, 1), true, true);

    ArrayList<AbsMyShape> lShapes = new ArrayList<AbsMyShape>(Arrays.asList(o, o1, o2, o3));

    AnimatorModel am = new AnimatorModel();

    assertEquals(o,
            am.validateShape(lShapes.subList(1, lShapes.size() - 1), lShapes.get(0)));

  }

  @Test(expected = IllegalArgumentException.class)
  public void validateShapeTest2() {
    MyOval o = new MyOval("o", 10.0, 10.0, 10.0, 10.0, new ShapeColor(0, 0, 1), true, true);
    MyOval o1 = new MyOval("o1", 10.0, 10.0, 10.0, 10.0, new ShapeColor(0, 0, 1), true, true);
    MyOval o2 = new MyOval("o1", 10.0, 10.0, 10.0, 10.0, new ShapeColor(0, 0, 1), true, true);
    MyOval o3 = new MyOval("o3", 10.0, 10.0, 10.0, 10.0, new ShapeColor(0, 0, 1), true, true);

    ArrayList<AbsMyShape> lShapes = new ArrayList<AbsMyShape>(Arrays.asList(o, o1, o2, o3));

    AnimatorModel am = new AnimatorModel();

    assertEquals(false,
            am.validateShape(lShapes.subList(1, lShapes.size() - 1), lShapes.get(1)));
  }

  @Test
  public void validateAnimTest1() {
    Appear ap = new Appear("o", 3, 6);
    ChangeColor cc = new ChangeColor("o", 3, 9, new ShapeColor(1, 0, 0), new ShapeColor(1, 0, 0));
    Disappear dp = new Disappear("o1", 3, 6);
    Disappear dp2 = new Disappear("o", 4, 12);
    Move m = new Move("o1", 2, 12, 1, 1, 13, 14);
    Move m2 = new Move("o1", 3, 12, 1, 1, 13, 14);
    Move m3 = new Move("o", 3, 12, 1, 1, 13, 14);
    Scale sc = new Scale("o3", 2, 12, 1, 1, 13, 14);

    //ap and dp are not targeting the same shape, but start at the same time
    ArrayList<AbsAnimation> lanims = new ArrayList<>(Arrays.asList(ap, dp));

    //m and m2 have the same target shape, and their bgn and end interloc
    ArrayList<AbsAnimation> lanims2 = new ArrayList<>(Arrays.asList(m, m2));

    //dp2 and ap target the same shape and interloc times
    ArrayList<AbsAnimation> lanims3 = new ArrayList<>(Arrays.asList(dp2, ap));

    // -- and -- target the same shape and have interlocking times, but should return true
    ArrayList<AbsAnimation> lanims4 = new ArrayList<>(Arrays.asList(cc, m3));


    AnimatorModel am = new AnimatorModel();

    assertEquals(ap, am.validateAnim(lanims.subList(1, lanims.size()), lanims.get(0)));
    assertEquals(cc, am.validateAnim(lanims4.subList(1, lanims4.size()), lanims4.get(0)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void validateAnimsTest2() {
    Appear ap = new Appear("o", 3, 6);
    ChangeColor cc = new ChangeColor("o", 3, 9, new ShapeColor(1, 0, 0), new ShapeColor(1, 0, 0));
    Disappear dp = new Disappear("o1", 3, 6);
    Disappear dp2 = new Disappear("o", 4, 12);
    Move m = new Move("o1", 2, 12, 1, 1, 13, 14);
    Move m2 = new Move("o1", 3, 12, 1, 1, 13, 14);
    Move m3 = new Move("o", 3, 12, 1, 1, 13, 14);
    Scale sc = new Scale("o3", 2, 12, 1, 1, 13, 14);

    //ap and dp are not targeting the same shape, but start at the same time
    ArrayList<AbsAnimation> lanims = new ArrayList<>(Arrays.asList(ap, dp));

    //m and m2 have the same target shape, and their bgn and end interloc
    ArrayList<AbsAnimation> lanims2 = new ArrayList<>(Arrays.asList(m, m2));

    //dp2 and ap target the same shape and interloc times
    ArrayList<AbsAnimation> lanims3 = new ArrayList<>(Arrays.asList(dp2, ap));

    // -- and -- target the same shape and have interlocking times, but should return true
    ArrayList<AbsAnimation> lanims4 = new ArrayList<>(Arrays.asList(cc, m3));


    AnimatorModel am = new AnimatorModel();

    assertEquals(false, am.validateAnim(lanims2.subList(1, lanims2.size()), lanims2.get(0)));
    assertEquals(false, am.validateAnim(lanims3.subList(1, lanims3.size()), lanims3.get(0)));
  }


  @Test
  public void snapshotTest() {
    MyOval o = new MyOval("o", 10.0, 10.0, 10.0, 10.0, new ShapeColor(0, 0, 1), true, true);
    MyOval o1 = new MyOval("o1", 10.0, 10.0, 10.0, 10.0, new ShapeColor(0, 0, 1), true, true);
    MyOval o2 = new MyOval("o2", 10.0, 10.0, 10.0, 10.0, new ShapeColor(0, 0, 1), true, true);
    MyOval o3 = new MyOval("o3", 10.0, 10.0, 10.0, 10.0, new ShapeColor(0, 0, 1), true, true);

    //ap and dp do not conflict
    Appear ap = new Appear("o", 3, 6);
    ChangeColor cc = new ChangeColor("o", 3, 9, new ShapeColor(0, 0, 1), new ShapeColor(1, 0, 0));
    Disappear dp = new Disappear("o1", 3, 6);
    Move m = new Move("o1", 2, 12, 10.0, 10.0, 13, 14);
    Scale sc = new Scale("o3", 2, 12, 10.0, 10.0, 13, 14);

    ArrayList<AbsMyShape> lshapes = new ArrayList<>(Arrays.asList(o, o1, o2, o3));
    ArrayList<AbsAnimation> lanims = new ArrayList<>(Arrays.asList(ap, cc, dp, m, sc));


    AnimatorModel am = new AnimatorModel(lshapes, lanims);

    assertEquals("Shapes: \n" + "Name: o\n"
                    + "Type: Oval \nCenter: (10.0,10.0), X Radius: 5.0, Y Radius:"
                    + " 5.0, Color: (0.2,0.0,0.8)\n"
                    + "\n" + "Name: o1\n" + "Type: Oval \n" + "Center:"
                    + " (10.6,10.8), X Radius: 5.0, Y Radius: 5.0, Color: (0.0,0.0,1.0)\n"
                    + "\n" + "Name: o2\n" + "Type: Oval \n" + "Center:"
                    + " (10.0,10.0), X Radius: 5.0, Y Radius: 5.0, Color: (0.0,0.0,1.0)\n"
                    + "\n" + "Name: o3\n" + "Type: Oval \n"
                    + "Center: (10.0,10.0), X Radius: 5.3, Y Radius: 5.4, Color: (0.0,0.0,1.0)",
            am.snapshot(4));

    assertEquals(2, m.ticksSinceBgn(4));

    assertEquals(
            "Shapes: \n" + "Name: o\n"
                    + "Type: Oval \nCenter: (10.0,10.0), X Radius: 5.0, Y Radius:"
                    + " 5.0, Color: (0.0,0.0,1.0)\n"
                    + "\n" + "Name: o1\n" + "Type: Oval \n" + "Center:"
                    + " (10.0,10.0), X Radius: 5.0, Y Radius: 5.0, Color: (0.0,0.0,1.0)\n"
                    + "\n" + "Name: o2\n" + "Type: Oval \n" + "Center:"
                    + " (10.0,10.0), X Radius: 5.0, Y Radius: 5.0, Color: (0.0,0.0,1.0)\n"
                    + "\n" + "Name: o3\n" + "Type: Oval \n"
                    + "Center: (10.0,10.0), X Radius: 5.0, Y Radius: 5.0, Color: (0.0,0.0,1.0)",
            am.snapshot(1));
  }

  @Test
  public void describeAnimatorTest() {
    MyOval o = new MyOval("o", 10.0, 10.0, 10.0, 10.0, new ShapeColor(0, 0, 1), true, true);
    MyOval o1 = new MyOval("o1", 10.0, 10.0, 10.0, 10.0, new ShapeColor(0, 0, 1), true, true);
    MyOval o2 = new MyOval("o2", 10.0, 10.0, 10.0, 10.0, new ShapeColor(0, 0, 1), true, true);
    MyOval o3 = new MyOval("o3", 10.0, 10.0, 10.0, 10.0, new ShapeColor(0, 0, 1), true, true);

    //ap and dp do not conflict
    Appear ap = new Appear("o", 3, 6);
    ChangeColor cc = new ChangeColor("o", 3, 9, new ShapeColor(0, 0, 1), new ShapeColor(1, 0, 0));
    Disappear dp = new Disappear("o1", 3, 6);
    Move m = new Move("o1", 2, 12, 10, 10, 13, 14);
    Scale sc = new Scale("o3", 2, 12, 10, 10, 13, 14);

    ArrayList<AbsMyShape> lshapes = new ArrayList<>(Arrays.asList(o, o1, o2, o3));
    ArrayList<AbsAnimation> lanims = new ArrayList<>(Arrays.asList(ap, cc, dp, m, sc));


    AnimatorModel am = new AnimatorModel(lshapes, lanims);

    assertEquals("Shapes:\n" + "Name: o\n" + "Type: Oval \n"
            + "Center:" + " (10.0,10.0), X Radius: 5.0, Y Radius: 5.0, Color: (0.0,0.0,1.0)\n"
            + "o appears at t=3\n" + "\n" + "Name: o1\n" + "Type: Oval \n"
            + "Center:" + " (10.0,10.0), X Radius: 5.0, Y Radius: 5.0, Color: (0.0,0.0,1.0)\n"
            + "o1 disappears at t=3\n" + "\n" + "Name: o2\n" + "Type: Oval \n"
            + "Center:" + " (10.0,10.0), X Radius: 5.0, Y Radius: 5.0, Color: (0.0,0.0,1.0)\n"
            + "\n" + "Name: o3\n" + "Type: Oval \n"
            + "Center:" + " (10.0,10.0), X Radius: 5.0, Y Radius: 5.0, Color: (0.0,0.0,1.0)\n"
            + "\n"
            + "o1 moves from (10.0,10.0) to (13.0,14.0) at t=2 to t=12\n"
            + "o3 changes (width, height) from (10.0,10.0) to (13.0,14.0) at t=2 to t=12\n"
            + "o changes color from (0.0,0.0,1.0) to (1.0,0.0,0.0) at t=3", am.describeAnimator());

    am.snapshot(3);

    assertEquals("Shapes:\n" + "Name: o\n" + "Type: Oval \n"
            + "Center: (10.0,10.0), X Radius: 5.0, Y Radius: 5.0, Color: (0.0,0.0,1.0)\n"
            + "o appears at t=3\n" + "\n" + "Name: o1\n" + "Type: Oval \n" + "Center:"
            + " (10.0,10.0), X Radius: 5.0, Y Radius: 5.0, Color: (0.0,0.0,1.0)\n"
            + "o1 disappears at t=3\n" + "\n" + "Name: o2\n" + "Type: Oval \n"
            + "Center: (10.0,10.0), X Radius: 5.0, Y Radius: 5.0, Color: (0.0,0.0,1.0)\n" + "\n"
            + "Name: o3\n" + "Type: Oval \n"
            + "Center:" + " (10.0,10.0), X Radius: 5.0, Y Radius: 5.0, Color: (0.0,0.0,1.0)\n"
            + "\n" + "o1 moves from (10.0,10.0) to (13.0,14.0) at t=2 to t=12\n"
            + "o3 changes (width, height) from (10.0,10.0) to (13.0,14.0) at t=2 to t=12\n"
            + "o changes color from (0.0,0.0,1.0) to (1.0,0.0,0.0) at t=3", am.describeAnimator());
  }


}
