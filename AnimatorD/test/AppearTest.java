import org.junit.Test;

import cs3500.animator.model.Appear;
import cs3500.animator.model.MyOval;
import cs3500.animator.model.MyRectangle;
import cs3500.animator.model.ShapeColor;

import static junit.framework.TestCase.assertEquals;

public class AppearTest {
  //Things to test:
  //    -toString() --
  //    -giveShape() --
  //    -animateForT(int duration)

  @Test
  public void appearToStringTest() {
    Appear ap = new Appear("o", 3, 6);
    MyOval o = new MyOval("o", 10.0, 10.0, 100.0, 100.0,
            new ShapeColor(0, 0, 1.0), true, true);
    MyRectangle r = new MyRectangle("o", 20.0, 25.0, 10.0, 10.0,
            new ShapeColor(0, 0, 1.0), true, true);

    //With no shape assigned to ap
    assertEquals("This animation has no shape to work on", ap.toString());

    //with o assigned to ap
    ap.giveShape(o);
    assertEquals("o appears at t=3", ap.toString());

    //Now with r assigned to ap
    ap.giveShape(r);
    assertEquals("o appears at t=3", ap.toString());
  }

  @Test
  public void giveShapeTest() {
    Appear ap = new Appear("o", 3, 6);
    MyOval o = new MyOval("o", 10.0, 10.0, 100.0, 100.0,
            new ShapeColor(0, 0, 1), true, true);

    assertEquals("This animation has no shape to work on", ap.toString());

    ap.giveShape(o);

    //Testing if ap now has a shape and it's name is "o".
    assertEquals("Name: o\n" + "Type: Oval \n"
            + "Center: (10.0,10.0), X Radius: 50.0, Y Radius: 50.0, Color: (0.0,0.0,1.0)",
            ap.getShape().toString());
  }
}
