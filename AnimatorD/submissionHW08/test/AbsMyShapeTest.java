import org.junit.Test;

import cs3500.animator.model.MyOval;
import cs3500.animator.model.MyRectangle;
import cs3500.animator.model.ShapeColor;

import static junit.framework.TestCase.assertEquals;

public class AbsMyShapeTest {
  //Things to test:
  //    -Constructors
  //    -changeColor() -
  //    -getColAsString()      -
  //    -getCol()
  //    -changePos()   -
  //    -scale()       -
  //    -makeVisible() -
  //    -makeInvisible() -
  //    -getName()     -
  //    -equals()      -

  //Testing changeColor() and getColAsString().
  @Test
  public void changeColorTest() {
    MyOval o = new MyOval("o", 100.0, 90.0, 10.0, 10.0,
            new ShapeColor(0, 0, 1), true, true);

    assertEquals("(0.0,0.0,1.0)", o.getColAsString());

    o.changeColor(new ShapeColor(1, 0, 0));

    assertEquals("(1.0,0.0,0.0)", o.getColAsString());
  }

  //Testing changePos() & getPosX() & getPosY()
  @Test
  public void changePosTest() {
    MyOval o = new MyOval("o", 100.0, 90.0, 10.0, 10.0,
            new ShapeColor(0, 0, 1), true, true);

    assertEquals(100, (int) o.getPosX());
    assertEquals(90, (int) o.getPosY());

    o.changePos(100, 100);

    assertEquals(100, (int) o.getPosY());
  }

  //Testing scale() & getWidth() & getHeight()
  @Test
  public void scaleTest() {
    MyOval o = new MyOval("o", 100.0, 90.0, 10.0, 10.0,
            new ShapeColor(1, 0, 0), true, true);
    o.scale(15, 15);
    assertEquals(15, (int) o.getWidth());
    assertEquals(15, (int) o.getHeight());
  }

  //Testing makeVisible() & getVisibility()
  @Test
  public void makeVisible() {
    MyOval o = new MyOval("o", 100.0, 90.0, 10.0, 10.0,
            new ShapeColor(1, 0, 0), false, true);
    o.makeVisible();
    assertEquals(true, o.getVisibility());
  }

  //Testing makeInvisible() & getVisibility()
  @Test
  public void makeInvisible() {
    MyOval o = new MyOval("o", 100.0, 90.0, 10.0, 10.0,
            new ShapeColor(1, 0, 0), true, true);
    o.makeInvisible();
    assertEquals(false, o.getVisibility());
  }

  //Testing getName()
  @Test
  public void getNameTest() {
    MyOval o = new MyOval("o", 100.0, 90.0, 10.0, 10.0,
            new ShapeColor(1, 0, 0), true, true);

    assertEquals("o", o.getName());
  }

  @Test
  public void getColTest() {
    MyOval o = new MyOval("o", 10.0, 10.0, 10.0, 10.0,
            new ShapeColor(1, 0, 0), true, true);
    MyRectangle r = new MyRectangle("r", 10.0, 10.0, 10.0, 10.0,
            new ShapeColor(1, 0, 0), true, true);

    assertEquals("(1.0,0.0,0.0)", r.getCol().toString());
    assertEquals("(1.0,0.0,0.0)", o.getCol().toString());
  }
}
