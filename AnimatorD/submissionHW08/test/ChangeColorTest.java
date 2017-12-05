import org.junit.Test;

import cs3500.animator.model.ChangeColor;
import cs3500.animator.model.MyOval;
import cs3500.animator.model.MyRectangle;
import cs3500.animator.model.ShapeColor;

import static junit.framework.TestCase.assertEquals;

public class ChangeColorTest {
  //Things to test:
  //    -toString() --
  //    -giveShape() --
  //    -animateForT(int duration)

  //Testing toString() in ChangeColor, after animate() is called and prevCol is given a value.
  @Test
  public void changeColorToStringTest2() {
    ChangeColor cc = new ChangeColor("o", 3, 9, new ShapeColor(0, 0, 1),
            new ShapeColor(0, 0, 1));
    MyOval o = new MyOval("o", 10.0, 10.0, 100.0, 100.0,
            new ShapeColor(0, 0, 1), true, true);
    MyRectangle r = new MyRectangle("o", 20.0, 25.0, 10.0, 10.0,
            new ShapeColor(0, 0, 1), true, true);

    //With no shape assigned to m
    assertEquals("This animation has no shape to work on", cc.toString());

    //with o assigned to m
    cc.giveShape(o);
    assertEquals("o changes color from (0.0,0.0,1.0) to (0.0,0.0,1.0) at t=3",
            cc.toString());

    //Now with r assigned to m
    cc.giveShape(r);
    assertEquals("o changes color from (0.0,0.0,1.0) to (0.0,0.0,1.0) at t=3",
            cc.toString());
  }

  @Test
  public void giveShapeTest() {
    ChangeColor cc = new ChangeColor("o", 12, 15, new ShapeColor(0, 0, 1),
            new ShapeColor(0, 0, 1));
    MyOval o = new MyOval("o", 10.0, 10.0, 100.0, 100.0,
            new ShapeColor(0, 0, 1), true, true);

    assertEquals("This animation has no shape to work on", cc.toString());

    cc.giveShape(o);

    //Testing if cc now has a shape, it's name is "o" and its prevCol is the same as o's color.
    assertEquals("o changes color from (0.0,0.0,1.0) to (0.0,0.0,1.0) at t=12",
            cc.toString());
    assertEquals("(0.0,0.0,1.0)", o.getCol().toString());
  }

  @Test
  public void toSVGTest() {
    ChangeColor cc = new ChangeColor("o", 12, 15, new ShapeColor(0, 0, 1),
            new ShapeColor(0, 0, 1));

    assertEquals("\t\t<animate attributeType=\"XML\" attributeName=\"fill\" "
            + "from=\"rgb(0,0,255)\" to=\"rgb(0,0,255)\" begin=\"12\" dur=\"3\" "
            + "fill=\"freeze\"/>", cc.toSVG());

    //CHANGE: HW07
    cc.setLoop(true);

    //Line Separator Issue TODO
    assertEquals("\t\t<animate attributeType=\"XML\" attributeName=\"fill\" "
            + "from=\"rgb(0,0,255)\" to=\"rgb(0,0,255)\" begin=\"12\" dur=\"3\" "
            + "fill=\"freeze\" repeatCount=\"indefinite\"/>", cc.toSVG());
  }
}
