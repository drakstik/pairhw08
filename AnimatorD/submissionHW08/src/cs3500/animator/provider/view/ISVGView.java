package cs3500.animator.provider.view;

/**
 * The view interface for the svg view, the output will follow the Format set below:
 *
 * <svg width="700" height="500" version="1.1" xmlns="http://www.w3.org/2000/svg">
 *
 * <rect id="TestRec" x="3.0" y="3.0" width="30.0" height="30.0" fill="rgb(255,0,0)" visibility =
 * "visible" > <animate attributeType="xml" attributeName="fill" begin="3000.0ms" dur="5000.0ms"
 * from="rgb(255,0,0)" to="rgb(0,0,255)" fill = "freeze" /> <animate attributeType="xml"
 * attributeName="width" begin="4000.0ms" dur="1000.0ms" from="30.0" to="50.0" fill="freeze" />
 * <animate attributeType="xml" attributeName="height" begin="4000.0ms" dur="1000.0ms" from="30.0"
 * to="50.0" fill="freeze" /> <animate attributeType="xml" begin="5000.0ms" dur="1000.0ms"
 * attributeName="x" from="3.0" to="70.0" fill="freeze" /> <animate attributeType="xml"
 * begin="5000ms" dur="1000.0ms" attributeName="y" from="3.0" to="70.0" fill="freeze" /> </rect>
 * <ellipse id="TestEllip" cx="30.0" cy="30.0" rx="25.0" ry="25.0" fill="rgb(255,0,0)" visibility =
 * "visible" > <animate attributeType="xml" attributeName="rx" begin="1000.0ms" dur="2000.0ms"
 * from="25.0" to="10.0" fill="freeze" /> <animate attributeType="xml" attributeName="ry"
 * begin="1000.0ms" dur="2000.0ms" from="25.0" to="10.0" fill="freeze" /> <animate
 * attributeType="xml" attributeName="fill" begin="2000.0ms" dur="6000.0ms" from="rgb(255,0,0)"
 * to="rgb(0,255,0)" fill = "freeze" /> <animate attributeType="xml" begin="3000.0ms" dur="3000.0ms"
 * attributeName="cx" from="30.0" to="40.0" fill="freeze" /> <animate attributeType="xml"
 * begin="3000ms" dur="3000.0ms" attributeName="cy" from="30.0" to="80.0" fill="freeze" />
 * </ellipse> <polygon id="TestTri" points="5.0,5.0 50.0,5.0 5.0,50.0" fill="rgb(255,0,0)"
 * visibility = "visible" > <animate attributeType="xml" attributeName="fill" begin="1000.0ms"
 * dur="7000.0ms" from="rgb(255,0,0)" to="rgb(0,255,255)" fill = "freeze" /> <animate
 * attributeType="xml" begin="2000ms" dur="1000.0ms" attributeName="points" from="5.0,5.0 50.0,5.0
 * 5.0,50.0" to="50.0,10.0 95.0,10.0 50.0,55.0" fill="freeze" /> <animate attributeType="xml"
 * begin="4000ms" dur="1000.0ms" attributeName="points" to="5.0,5.0 5.0,50.0 100.0,12.0"
 * fill="freeze" /> </polygon> <polygon id="TestPoly" points=" 85.0,100.0 115.0,100.0 130.0,74.01924
 * 115.0,48.03848 85.0,48.03848 70.0,74.01924" fill="rgb(255,0,0)" visibility = "visible" > <animate
 * attributeType="xml" attributeName="points" to=" 75.0,100.0 125.0,100.0 150.0,56.69873
 * 125.0,13.397461 75.0,13.397461 50.0,56.69873" begin="1000ms" dur="6000.0ms" fill="freeze" />
 * <animate attributeType="xml" begin="2000ms" dur="1000.0ms" attributeName="points" to="
 * 135.0,150.0 165.0,150.0 180.0,124.01924 165.0,98.03848 135.0,98.03848 120.0,124.01924"
 * fill="freeze" /> <animate attributeType="xml" attributeName="fill" begin="2000.0ms"
 * dur="2000.0ms" from="rgb(255,0,0)" to="rgb(255,200,0)" fill = "freeze" /> </polygon> </svg>.
 */
public interface ISVGView extends View {

  /**
   * Sets up an SVG animation canvas, with the input height and width parameters.
   */
  void setupSVG(int height, int width);

  /**
   * Takes in the XML data output by the SVG controller.
   */
  void setXMLData(String controllerData);

  /**
   * Displays the SVG animation.
   */
  void displaySVG();
}
