import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import cs3500.animator.model.AbsAnimation;
import cs3500.animator.model.AbsMyShape;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.Appear;
import cs3500.animator.model.ChangeColor;
import cs3500.animator.model.Disappear;
import cs3500.animator.model.IAnimatorModelView;
import cs3500.animator.model.Move;
import cs3500.animator.model.MyOval;
import cs3500.animator.model.Scale;
import cs3500.animator.model.ShapeColor;
import cs3500.animator.view.TextView;

import static junit.framework.TestCase.assertEquals;

public class TextViewTest {

  MyOval o = new MyOval("o", 10.0f, 10.0f, 10.0f, 10.0f,
          new ShapeColor(0, 0, 1), true, true);
  MyOval o1 = new MyOval("o1", 10, 10, 10, 10,
          new ShapeColor(0, 0, 1), true, true);
  MyOval o2 = new MyOval("o2", 10, 10, 10, 10,
          new ShapeColor(0, 0, 1), true, true);
  MyOval o3 = new MyOval("o3", 10, 10, 10, 10,
          new ShapeColor(0, 0, 1), true, true);

  //ap and dp do not conflict
  Appear ap = new Appear("o", 3, 6);
  ChangeColor cc = new ChangeColor("o", 3, 9, new ShapeColor(0, 0, 1),
          new ShapeColor(1, 0, 0));
  Disappear dp = new Disappear("o1", 3, 6);
  Move m = new Move("o1", 2, 12, 10, 10, 13, 14);
  Scale sc = new Scale("o3", 2, 12, 10, 10, 13,
          14);

  ArrayList<AbsMyShape> lshapes = new ArrayList<>(Arrays.asList(o, o1, o2, o3));
  ArrayList<AbsAnimation> lanims = new ArrayList<>(Arrays.asList(ap, cc, dp, m, sc));


  IAnimatorModelView model = new AnimatorModel(lshapes, lanims);
  TextView view = new TextView(model, 1);

  @Test
  public void AnimToStringTest() {
    assertEquals("Appears at t=3.0s", view.animToString(ap));
    assertEquals("Shape o changes color from (0.0,0.0,1.0) to (1.0,0.0,0.0) "
            + "from t=3.0s to t=9.0s", view.animToString(cc));
    assertEquals("Disappears at t=3.0s", view.animToString(dp));
    assertEquals("Shape o1 moves from (10.0,10.0) to (13.0,14.0) from t=2.0s to t=12.0s",
            view.animToString(m));
    assertEquals("Shape o3 scales from Width:10.0, Height: 10.0 to Width: 13.0, "
            + "Height: 14.0 from t=2.0s to t=12.0s", view.animToString(sc));

  }

  @Test
  public void textDisplayTest() {
    assertEquals("Shapes:\n" + "Name: o\n" + "Type: Oval \n" + "Center:"
            + " (10.0,10.0), X Radius: 5.0, Y Radius: 5.0, Color: (0.0,0.0,1.0)\n"
            + "Appears at t=3.0s\n" + "\n" + "Name: o1\n" + "Type: Oval \n"
            + "Center:" + " (10.0,10.0), X Radius: 5.0, Y Radius: 5.0, Color: (0.0,0.0,1.0)\n"
            + "Disappears at t=3.0s\n" + "\n" + "Name: o2\n" + "Type: Oval \n"
            + "Center:" + " (10.0,10.0), X Radius: 5.0, Y Radius: 5.0, Color: (0.0,0.0,1.0)\n"
            + "\n" + "Name: o3\n" + "Type: Oval \n" + "Center:"
            + " (10.0,10.0), X Radius: 5.0, Y Radius: 5.0, Color: (0.0,0.0,1.0)\n" + "\n"
            + "Shape o1 moves from (10.0,10.0) to (13.0,14.0) from t=2.0s to t=12.0s\n"
            + "Shape o3 scales from Width:10.0, Height: 10.0 to Width: 13.0,"
            + " Height: 14.0 from t=2.0s to t=12.0s\n" + "Shape o changes color from "
            + "(0.0,0.0,1.0) to (1.0,0.0,0.0) from t=3.0s to t=9.0s", view.textDisplay());
  }
}
