package cs3500.animator;

import cs3500.animator.controller.ControllerHybrid;
import cs3500.animator.controller.ControllerSVG;
import cs3500.animator.controller.ControllerText;
import cs3500.animator.controller.ControllerVisual;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.ViewFactory;
import cs3500.animator.view.HybridView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.VisualView;
import util.AnimationFileReader;
import util.TweenModelBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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

    String animFileName = "";
    String viewType = "";
    String output = "";
    double tickRate = 1.0;

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
        default:
          throw new IllegalArgumentException("Invalid command line input.");
      }
    }

    if (animFileName.equals("") || viewType.equals("")) {
      throw new IllegalArgumentException("Input must include a view and a file path.");
    }

    AnimationFileReader afr = new AnimationFileReader();
    TweenModelBuilder<AnimatorModel> builder = new AnimatorModel.Builder();

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
  }
}
