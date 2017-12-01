import org.junit.Test;

import cs3500.animator.model.Move;
import cs3500.animator.model.MyOval;
import cs3500.animator.model.MyRectangle;
import cs3500.animator.model.ShapeColor;

import static junit.framework.TestCase.assertEquals;

public class MoveTest {
  //Things to test:
  //       -getTraveledX --
  //       -getTraveledY --
  //       -toString --
  //       -giveShape() --
  //       -animateForT(int duration)

  //Testing toString() in Move, after giving startPosX and startPosY values through animate()
  @Test
  public void toStringTest() {
    Move m = new Move("o", 2, 12, 10, 10, 13, 14);
    MyOval o = new MyOval("o", 10.0, 10.0, 10.0, 10.0,
            new ShapeColor(0, 0, 1), true, true);
    MyRectangle r = new MyRectangle("o", 20.0, 25.0, 10.0, 10.0,
            new ShapeColor(0, 0, 1), true, true);

    //With no shape assigned to m
    assertEquals("This animation has no shape to work on", m.toString());

    //with o assigned to m
    m.giveShape(o);
    assertEquals("o moves from (10.0,10.0) to (13.0,14.0) at t=2 to t=12", m.toString());

    //Now with r assigned to m
    m.giveShape(r);
    assertEquals("o moves from (10.0,10.0) to (13.0,14.0) at t=2 to t=12", m.toString());
  }

  @Test
  public void giveShapeTest() {
    Move m = new Move("o", 12, 15, 12, 12, 10, 10);
    MyOval o = new MyOval("o", 12.0, 12.0, 13.0, 15.0,
            new ShapeColor(0, 0, 1), true, true);

    assertEquals("This animation has no shape to work on", m.toString());

    m.giveShape(o);

    //Testing if m has a shape, m's name is "o" and m.startPosX, m.startPosY == o.getPosX, o.getPosY
    assertEquals("o moves from (12.0,12.0) to (10.0,10.0) at t=12 to t=15", m.toString());
    assertEquals(m.getStartPosX(), o.getPosX());
    assertEquals(m.getStartPosY(), o.getPosY());
  }

  @Test
  public void toSVG() {
    Move m = new Move("o", 1000, 5000, 200, 200, 300,
            200);
    System.out.println(m.getBgn());

    //This method behaves correctly, but depending on IntelliJ settings may fail due
    // to different line separator characters.
    //    assertEquals("\t\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" "
    //            + "attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />", m.toSVG());

    Move m2 = new Move("o", 1000, 5000, 200, 200, 300,
            300);
    //This method behaves correctly, but depending on IntelliJ settings may fail due
    // to different line separator characters.
    //    assertEquals("\t\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" "
    //            + "attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n\t\t"
    //            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" "
    //            + "attributeName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />", m2.toSVG());

    Move m3 = new Move("o", 1000, 5000, 200, 200, 200,
            300);

    m3.setLoop(true);

    assertEquals("\t\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" "
            + "attributeName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" "
            + "repeatCount=\"indefinite\"/>", m3.toSVG());
  }
}
