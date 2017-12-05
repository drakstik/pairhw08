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
import cs3500.animator.view.SVGView;

import static junit.framework.TestCase.assertEquals;

public class SVGViewTest {
  @Test
  public void displayTest() {
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
    Appendable app = new StringBuilder();
    SVGView view = new SVGView(model, app);
    view.display();
    assertEquals("<?xml version=\"1.0\"?>\n" + "<svg width=\"800\" height=\"600\" "
            + "viewPort=\"0 0 800 600\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<ellipse id=\"o\" cx=\"10\" cy=\"10\" rx=\"5\" ry=\"5\" fill=\"rgb(0,0,255)\" "
            + "visibility=\"visible\" >\n\t\t<animate attributeType=\"XML\" "
            + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" begin=\"3\" "
            + "dur=\"0.001s\" fill=\"freeze\"/>\n\t\t<animate attributeType=\"XML\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(255,0,0)\" begin=\"3\" "
            + "dur=\"6\" fill=\"freeze\"/>\n</ellipse>\n<ellipse id=\"o1\" cx=\"10\" cy=\"10\" "
            + "rx=\"5\" ry=\"5\" fill=\"rgb(0,0,255)\" visibility=\"visible\" >\n\t\t<animate "
            + "attributeType=\"xml\" begin=\"2ms\" dur=\"10ms\" attributeName=\"x\" from=\"10\" "
            + "to=\"13\" fill=\"freeze\" />\n\t\t<animate attributeType=\"xml\" begin=\"2ms\" "
            + "dur=\"10ms\" attributeName=\"y\" from=\"10\" to=\"14\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"XML\" attributeName=\"visibility\" from=\"visible\" "
            + "to=\"hidden\" begin=\"3\" dur=\"0.001s\" fill=\"freeze\"/>\n</ellipse>\n"
            + "<ellipse id=\"o2\" cx=\"10\" cy=\"10\" rx=\"5\" ry=\"5\" fill=\"rgb(0,0,255)\" "
            + "visibility=\"visible\" >\n</ellipse>\n" + "<ellipse id=\"o3\" cx=\"10\" cy=\"10\" "
            + "rx=\"5\" ry=\"5\" fill=\"rgb(0,0,255)\" visibility=\"visible\" >\n\t\t<animate "
            + "attributeType=\"xml\" begin=\"2ms\" dur=\"10ms\" attributeName=\"width\" from=\"10\""
            + " to=\"13\" fill=\"freeze\" />\n\t\t<animate attributeType=\"xml\" begin=\"2ms\" "
            + "dur=\"10ms\" attributeName=\"height\" from=\"10\" to=\"14\" fill=\"freeze\" />\n"
            + "</ellipse>\n" + "</svg>", view.getOutput().toString());
  }
}
