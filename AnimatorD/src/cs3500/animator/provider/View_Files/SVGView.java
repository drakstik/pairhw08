package cs3500.animator.provider.View_Files;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


/**
 * The SVG View relies on the SVG Controller to feed it the appropriately packaged information
 * for it to display and print out, in SVG format. If the view is provided an output file, it
 * will output to that file. If not, it will output to the console.
 */
public class SVGView implements ISVGView {
  private int ticksPerSecond;
  private String xmlData;
  private StringBuilder finalOutput = new StringBuilder();
  private String outputFile = "";

  public SVGView(int ticksPerSecond, String outputFile) {
    this.ticksPerSecond = ticksPerSecond;
    this.outputFile = outputFile;
  }

  public SVGView(int ticksPerSecond) {
    this.ticksPerSecond = ticksPerSecond;
  }

  @Override
  public void setupSVG(int height, int width) {
    finalOutput.append("\n<svg width=\"" + width + "\" height=\"" + height + "\" " +
            "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">");
  }

  @Override
  public void setXMLData(String controllerData) {
    xmlData = controllerData;
  }

  @Override
  public void displaySVG() {
    finalOutput.append(xmlData);
    finalOutput.append("\n</svg>");
    try {
      if (outputFile.equals("")) {
        System.out.println(finalOutput);
      } else {
        try {
          PrintWriter writer = new PrintWriter(outputFile, "utf-8");
          writer.print(finalOutput);
          writer.close();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (UnsupportedEncodingException f) {
          f.printStackTrace();
        } catch (NullPointerException n) {
          System.out.println(finalOutput);
        }
      }
    } catch (NullPointerException e) {
      System.out.println(finalOutput);
    }
  }

  @Override
  public void display() {
    displaySVG();
  }
}
