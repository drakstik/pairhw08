import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import cs3500.animator.model.AbsAnimation;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.Appear;
import cs3500.animator.model.ChangeColor;
import cs3500.animator.model.Disappear;
import cs3500.animator.model.AbsMyShape;
import cs3500.animator.model.Move;
import cs3500.animator.model.MyOval;
import cs3500.animator.model.Scale;
import cs3500.animator.model.ShapeColor;

import static junit.framework.TestCase.assertEquals;

public class ModelOutputTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void cleanUpStrams() {
    System.setOut(null);
    System.setErr(null);
  }

  //Testing non-working AnimatorModel construction
  @Test
  public void AnimatorModelTest3() {
    MyOval o = new MyOval("o", 10.0, 10.0, 10.0, 10.0,
            new ShapeColor(0, 0, 1), true, true);
    MyOval o1 = new MyOval("o1", 10.0, 10.0, 10.0, 10.0,
            new ShapeColor(0, 0, 1), true, true);
    MyOval o2 = new MyOval("o1", 10.0, 10.0, 10.0, 10.0,
            new ShapeColor(0, 0, 1), true, true);
    MyOval o3 = new MyOval("o3", 10.0, 10.0, 10.0, 10.0,
            new ShapeColor(0, 0, 1), true, true);

    //ap and dp conflict
    Appear ap = new Appear("o", 3, 6);
    ChangeColor cc = new ChangeColor("o", 3, 9, new ShapeColor(0, 0, 1),
            new ShapeColor(1, 0, 0));
    Disappear dp = new Disappear("o", 3, 6);
    Move m = new Move("o1", 2, 12, 10.0, 10.0, 13,
            14);
    Scale sc = new Scale("o3", 2, 12, 10.0, 10.0, 13,
            14);

    ArrayList<AbsMyShape> lshapes = new ArrayList<>(Arrays.asList(o, o1, o2, o3));
    ArrayList<AbsAnimation> lanims = new ArrayList<>(Arrays.asList(ap, cc, dp, m, sc));

    new AnimatorModel(lshapes, lanims);
    assertEquals("Given shape shares a name with a pre-existing shape.\n"
            + "java.lang.IllegalArgumentException: Animation: [o disappears at t=3]"
            + " is clashing with pre-existing animation [o appears at t=3]\n",
            outContent.toString());
  }
}
