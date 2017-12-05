import org.junit.Test;

import cs3500.animator.model.MyOval;
import cs3500.animator.model.ShapeColor;

import static junit.framework.TestCase.assertEquals;

public class MyOvalTest {

  @Test
  public void myOvalTest1() {
    MyOval c = new MyOval("C", 500.0, 100.0, 60.0, 30.0, new ShapeColor(0, 0, 1), true, true);

    assertEquals("Name: C\n" + "Type: Oval \n"
                    + "Center: (500.0,100.0), X Radius: 30.0, Y Radius: 15.0, Color: (0.0,0.0,1.0)",
            c.toString());
  }

  @Test
  public void toSVGTest() {
    MyOval c = new MyOval("C", 500.0, 100.0, 60.0, 30.0, new ShapeColor(0.0, 0.0, 1.0), true, true);

    assertEquals("<ellipse id=\"C\" cx=\"500\" cy=\"100\" rx=\"30\" ry=\"15\" "
                    + "fill=\"rgb(0,0,255)\" visibility=\"visible\" >",
            c.toSVG());
  }
}
