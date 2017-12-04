package cs3500.animator;

import cs3500.animator.controller.ControllerHybrid;
import cs3500.animator.controller.ControllerSVG;
import cs3500.animator.controller.ControllerText;
import cs3500.animator.controller.ControllerVisual;
import cs3500.animator.provider.Controller_Files.IHybridController;
import cs3500.animator.provider.Controller_Files.IVisualController;
import cs3500.animator.model.AbsMyShape;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IMyShape;
import cs3500.animator.model.ProviderViewFactory;
import cs3500.animator.model.ViewFactory;
import cs3500.animator.provider.View_Files.View;
import cs3500.animator.view.HybridView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.VisualView;
import util.AnimationFileReader;
import util.TweenModelBuilder;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * Represents an easy animator with a main method that can run different animations.
 */
public final class EasyAnimator {
  /**
   * Begins the program.
   *
   * @param args these flags determine which animation to view and in what view, the output path of
   *             the program and the speed of the animation. command-line args will look like: <-if
   *             name-of-animation-file> <-iv type-of-view> <-o where-output-show-run> <-speed
   *             integer-ticks-per-second> .
   *
   *             with a space between each pairing.
   */
  // IMPORTANT: parsing breaks from the hasNext loop when it sees a "."
  // we could not figure out a better way of taking input, so please add a " ." to the end
  // of a test input to see our working program (svg and text DO print correctly to System.out)
  public static void main(String[] args) {
    ViewFactory vf = new ViewFactory();
    ProviderViewFactory pvf = new ProviderViewFactory();

    String animFileName = "";
    String viewType = "";
    String output = "";
    double tickRate = 1.0;
    String provider = "";

    Appendable ap = new StringBuilder();
    Scanner sc = new Scanner(System.in);
    File file;

    String input = sc.nextLine();

    String[] commands = input.split(" ");

    for (int i = 0; i < commands.length; i++) {
      String cmd = commands[i];
      switch (cmd) {
        case ("-if"):
          animFileName = commands[i + 1];
          i++;
          //System.out.println("filename = " + animFileName);
          break;
        case ("-iv"):
          //System.out.println("first arg");
          viewType = commands[i + 1];
          i++;
          //System.out.println("viewType = " + viewType);
          break;
        case ("-o"):
          output = commands[i + 1];
          i++;
          //System.out.println("output = " + output);
          break;
        case ("-speed"):
          tickRate = Double.parseDouble(commands[i + 1]);
          i++;
          break;
        case ("-provider"):
          provider = commands[i + 1];
          i++;
          break;
        default:
          throw new IllegalArgumentException("Invalid command line input.");
      }
    }

    if (animFileName.equals("") || viewType.equals("")) {
      throw new IllegalArgumentException("Input must include a view and a file path.");
    }

    AnimationFileReader afr = new AnimationFileReader();
    TweenModelBuilder<AnimatorModel> builder = new AnimatorModel.Builder();

    if (provider.equals("true")) {
      View view = pvf.create(viewType, (int)Math.round(tickRate), output);
      AnimatorModel model;
      try {
        model = afr.readFile(animFileName, builder);
        ArrayList<AbsMyShape> shapes = model.getShapes();

      switch (viewType) {
        case ("interactive"):
          cs3500.animator.provider.View_Files.HybridView phview
                  = (cs3500.animator.provider.View_Files.HybridView) view;
          IHybridController hc = new ControllerHybrid(model, phview);
//          phview.setTimerListener(hc);
//          phview.setStartListener(hc);
//          phview.setRestartListener(hc);
//          phview.setPausePlayListener(hc);
//          phview.setLoopListener(hc);
//          phview.setNewSpeedListener(hc);
//          phview.setRemoveShapeListener(hc);
          //phview.display();
          phview.setUpPanel(MyShapesToShapes(shapes), MyShapesToColors(shapes));
          break;
        case ("svg"):
          cs3500.animator.provider.View_Files.SVGView pSVGview
                  = (cs3500.animator.provider.View_Files.SVGView) view;
          SVGView svgView = new SVGView();
          svgView.setModel(model);
          pSVGview.setupSVG(1200, 1200);
          pSVGview.setXMLData(svgView.getXMLData());
          pSVGview.display();
          break;
        case ("visual"):
          cs3500.animator.provider.View_Files.VisualView pvview
                  = (cs3500.animator.provider.View_Files.VisualView) view;
          IVisualController cv = new ControllerVisual(model, pvview);
          pvview.setTimerListener(cv);

//          pvview.display();
          pvview.setUpPanel(MyShapesToShapes(shapes), MyShapesToColors(shapes));
          break;
        case ("text"):
          cs3500.animator.provider.View_Files.TextView ptview
                  = (cs3500.animator.provider.View_Files.TextView) view;
          ptview.describeAnimation(model.describeAnimator());
          ptview.display();
          break;
        default:
          System.out.println("Given viewType invalid!");
          break;
      }
      } catch (FileNotFoundException e) {
        System.out.println("Given filename invalid.");
      }
    } else if (provider.equals("false")) {
      switch (viewType) {
        case ("interactive"):
          AnimatorModel hModel; //all fields are empty ArrayLists
          try {
            hModel = afr.readFile(animFileName, builder);
          } catch (FileNotFoundException e) {
            System.out.println("Given filename invalid.");
            break;
          }

          IView hv = vf.create("interactive");
          HybridView hView = (HybridView) hv;
          ControllerHybrid ch = new ControllerHybrid(hModel, hView);

          hView.setModel(hModel);
          ch.setTickRate(tickRate);
          ch.run();
          break;
        case ("svg"):
          AnimatorModel svgModel; //all fields are empty ArrayLists

          try {
            svgModel = afr.readFile(animFileName, builder);
          } catch (FileNotFoundException e) {
            System.out.println("Given filename invalid.");
            break;
          }


          IView svgV = vf.create("svg");
          SVGView svgView = (SVGView) svgV;
          svgView.setModel(svgModel);
          ControllerSVG cSVG = new ControllerSVG(svgModel, svgView);
          Appendable out = svgView.getOutput();
          cSVG.run();

          if (!output.equals("") && !output.equals("out")) { //else leave ap as System.out

            file = new File(output);

            //Create the file
            try {
              if (file.createNewFile()) {
                PrintWriter pw = new PrintWriter(file);
                pw.write(out.toString());
                pw.close();
                System.out.println("File: " + output + " is created!");
              } else {
                System.out.println("File already exists.");
              }
            } catch (IOException e) {
              System.out.println(e);
            }
          } else {
            System.out.println(svgView.getOutput());
          }
          break;

        case ("visual"):


          AnimatorModel vModel = new AnimatorModel(); //all fields are empty ArrayLists
          try {
            vModel = afr.readFile(animFileName, builder);
          } catch (FileNotFoundException e) {
            System.out.println("Given filename invalid.");
            break;
          }

          IView vv = vf.create("visual");
          VisualView vView = (VisualView) vv;
          vView.setModel(vModel);
          ControllerVisual cv = new ControllerVisual(vModel, vView);
          cv.setTickRate(tickRate);
          cv.run();
          break;

        case ("text"):
          AnimatorModel tModel = new AnimatorModel(); //all fields are empty ArrayLists

          try {
            tModel = afr.readFile(animFileName, builder);
          } catch (FileNotFoundException e) {
            System.out.println("Given filename invalid.");
            break;
          }

          IView tV = vf.create("text");
          TextView tView = (TextView) tV;
          tView.setModel(tModel);
          ControllerText cTXT = new ControllerText(tModel, tView);
          cTXT.setTickRate(tickRate);
          cTXT.run();

          //TODO Abstract?
          if (!output.equals("") && !output.equals("out")) { //else leave ap as System.out
            file = new File(output);
            //Create the file
            try {
              if (file.createNewFile()) {
                PrintWriter pw = new PrintWriter(file);
                pw.write(tView.getOutput().toString());
                pw.close();
                System.out.println("File: " + output + " is created!");
              } else {
                System.out.println("File already exists.");
              }
            } catch (IOException e) {
              System.out.println(e);
            }
          } else {
            System.out.print(tView.getOutput());
          }
          break;
        default:
          System.out.println("Given viewType invalid!");
          break;
      }
    } else {
      throw new IllegalArgumentException("Invalid input for provider. Has to be true or false");
    }
  }

  /**
   * Turn the given ArrayList of MyShapes into a LinkedHashMap with K: String V: Shape.
   * @param myShapes ArrayList of MyShapes.
   * @result LinkedHashMap with K: String V: Shape.
   */
  private static LinkedHashMap<String, Shape> MyShapesToShapes(ArrayList<AbsMyShape> myShapes) {
    LinkedHashMap<String, Shape> l = new LinkedHashMap<String,Shape>();

    for (int i = 0; i < myShapes.size(); i++) {
      AbsMyShape ms = myShapes.get(i);
      Shape s;

      if (ms.getShapeType() == IMyShape.ShapeType.RECTANGLE) {
        s = new Rectangle2D.Double(ms.getPosX(), ms.getPosY(), ms.getWidth(), ms.getHeight());
      } else {
        s = new Ellipse2D.Double(ms.getPosX(), ms.getPosY(), ms.getWidth(), ms.getHeight());
      }

      l.put(ms.getName(), s);
    }

    return l;
  }

  /**
   * Turn the given ArrayList of MyShapes into a LinkedHashMap with K: String V: Color.
   * @param myShapes ArrayList of MyShapes.
   * @result LinkedhashMap with K: String V: Color.
   */
  private static LinkedHashMap<String, Color> MyShapesToColors(ArrayList<AbsMyShape> myShapes) {
    LinkedHashMap<String, Color> l = new LinkedHashMap<String, Color>();

    for (int i = 0; i < myShapes.size(); i++) {
      AbsMyShape ms = myShapes.get(i);
      Color c = ms.getCol().scToColor();
      l.put(ms.getName(), c);
    }

    return l;
  }
}
