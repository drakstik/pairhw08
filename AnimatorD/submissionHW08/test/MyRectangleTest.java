import org.junit.Test;

import cs3500.animator.model.MyRectangle;
import cs3500.animator.model.ShapeColor;

import static junit.framework.TestCase.assertEquals;

public class MyRectangleTest {

  @Test
  public void myRectangleTest1() {
    MyRectangle r = new MyRectangle("R", 200.0, 100.0, 50.0, 100.0,
            new ShapeColor(0, 0, 1), true, true);
    assertEquals("Name: R\n" + "Type: rectangle\n"
                    + "Lower-Left Corner: (200.0,200.0), Width: 50.0, Height:"
                    + " 100.0, Color: (0.0,0.0,1.0)", r.toString());
  }

  @Test
  public void toSVGTest() {
    MyRectangle r = new MyRectangle("R", 500.0, 100.0, 60.0, 30.0,
            new ShapeColor(0.0, 0.0, 1.0), true, true);

    assertEquals("<rect id=\"R\" x=\"500\" y=\"100\" width=\"60\" height=\"30\" "
                    + "fill=\"rgb(0,0,255)\" visibility=\"visible\" >",
            r.toSVG());
  }

}
