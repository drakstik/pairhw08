import org.junit.Test;

import cs3500.animator.model.Disappear;
import cs3500.animator.model.MyOval;
import cs3500.animator.model.MyRectangle;
import cs3500.animator.model.ShapeColor;

import static junit.framework.TestCase.assertEquals;

public class DissapearTest {
  //Things to test:
  //    -toString() --
  //    -giveShape() --
  //    -animateForT(int duration)

  //Testing the toString() method in Appear
  @Test
  public void appearToStringTest() {
    Disappear dp = new Disappear("A", 3, 6);
    MyOval o = new MyOval("o", 10.0, 10.0, 100.0, 100.0,
            new ShapeColor(0, 0, 1), true, true);
    MyRectangle r = new MyRectangle("o", 20.0, 25.0, 10.0, 10.0,
            new ShapeColor(0, 0, 1), true, true);

    //With no shape assigned to dp
    assertEquals("This animation has no shape to work on", dp.toString());

    //with o assigned to dp
    dp.giveShape(o);
    assertEquals("o disappears at t=3", dp.toString());

    //Now with r assigned to dp
    dp.giveShape(r);
    assertEquals("o disappears at t=3", dp.toString());
  }

  @Test
  public void giveShapeTest() {
    Disappear dp = new Disappear("o", 8, 10);
    MyOval o = new MyOval("o", 10.0, 10.0, 100.0, 100.0,
            new ShapeColor(0, 0, 1), true, true);

    assertEquals("This animation has no shape to work on", dp.toString());

    dp.giveShape(o);

    //Testing if dp now has a shape and it's name is "o".
    assertEquals("o disappears at t=8", dp.toString());
  }
}
