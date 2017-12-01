import org.junit.Test;

import cs3500.animator.model.MyOval;
import cs3500.animator.model.MyRectangle;
import cs3500.animator.model.Scale;
import cs3500.animator.model.ShapeColor;

import static junit.framework.TestCase.assertEquals;

public class ScaleTest {
  //Things to test:
  //       -toString() --
  //       -giveShape() --
  //       -animateForT(int duration)

  //Testing toString() in Scale, before giving startWidth and startHeight values
  @Test
  public void scaleToString() {
    Scale sc = new Scale("R", 2, 12, 1, 1, 10,
            10);
    MyOval o = new MyOval("R", 10.0, 10.0, 10.0, 10.0,
            new ShapeColor(0, 0, 1), true, true);
    sc.giveShape(o);
    assertEquals("R changes (width, height) from (1.0,1.0) to (10.0,10.0) at t=2 to t=12",
            sc.toString());
  }

  //Testing toString() in Move, after giving startWidth and startHeight values through animate()
  //This is a test for animateForT() as well
  @Test
  public void scaleToStringAnimateForT() {
    Scale sc = new Scale("o", 2, 12, 10, 10, 13,
            14);
    MyOval o = new MyOval("o", 10.0, 10.0, 10.0, 10.0,
            new ShapeColor(0, 0, 1), true, true);
    MyRectangle r = new MyRectangle("o", 20.0, 25.0, 10.0, 10.0,
            new ShapeColor(0, 0, 1), true, true);

    //With no shape assigned to sc
    assertEquals("This animation has no shape to work on", sc.toString());

    //with o assigned to sc
    sc.giveShape(o);
    assertEquals("o changes (width, height) from (10.0,10.0) to (13.0,14.0) at t=2 to t=12",
            sc.toString());

    //Now with r assigned to sc
    sc.giveShape(r);
    assertEquals("o changes (width, height) from (10.0,10.0) to (13.0,14.0) at t=2 to t=12",
            sc.toString());
  }

  @Test
  public void giveShapeTest() {
    Scale sc = new Scale("o", 12, 15, 13, 15, 10,
            10);
    MyOval o = new MyOval("o", 12.0, 12.0, 13.0, 15.0,
            new ShapeColor(0, 0, 1), true, true);

    assertEquals("This animation has no shape to work on", sc.toString());

    sc.giveShape(o);

    //Testing if sc has a shape, sc's name field == "o" and sc.startWidth(),
    // sc.startHeight == o.getWidth(), o.getHeight()
    assertEquals("o changes (width, height) from (13.0,15.0) to (10.0,10.0)"
            + " at t=12 to t=15", sc.toString());
    assertEquals(sc.getStartWidth(), o.getWidth());
    assertEquals(sc.getStartHeight(), o.getHeight());
  }

  @Test
  public void toSVG() {
    Scale s = new Scale("o", 1000, 5000, 200, 200, 300,
            200);

    assertEquals("\t\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" "
            + "attributeName=\"width\" from=\"200\" to=\"300\" fill=\"freeze\" />", s.toSVG());

    Scale s2 = new Scale("o", 1000, 5000, 200, 200, 300,
            300);
    assertEquals("\t\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" "
            + "attributeName=\"width\" from=\"200\" to=\"300\" fill=\"freeze\" />\n\t\t"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" "
            + "attributeName=\"height\" from=\"200\" to=\"300\" fill=\"freeze\" />", s2.toSVG());

    Scale s3 = new Scale("o", 1000, 5000, 200, 200, 300,
            300);

    s3.setLoop(true);
    assertEquals("\t\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" "
            + "attributeName=\"width\" from=\"200\" to=\"300\" fill=\"freeze\" "
            + "repeatCount=\\\"indefinite\\\"/>", s3.toSVG());
    //This method behaves correctly, but depending on IntelliJ settings may fail due
    // to different line separator characters.
    //    assertEquals("\t\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
    //            + "attributeName=\"width\" from=\"200\" to=\"300\" fill=\"freeze\" />%n"
    //            + "\t\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
    //            + "attributeName=\"height\" from=\"%d\" to=\"%d\" fill=\"freeze\" "
    //            + "repeatCount=\"indefinite\"/>", s3.toSVG());
  }
}
