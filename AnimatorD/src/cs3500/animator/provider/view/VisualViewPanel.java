package cs3500.animator.provider.view;

import java.awt.Color;
import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.swing.JPanel;

public class VisualViewPanel extends JPanel {
  private LinkedHashMap<String, Shape> shapes;
  private LinkedHashMap<String, Color> shapeColors;

  /**
   * Constructor for VisualViewPanel object.
   */
  public VisualViewPanel() {
    this.shapes = new LinkedHashMap<String, Shape>();
    this.shapeColors = new LinkedHashMap<String, Color>();
  }

  /**
   * Sets the member variables to the map of shapes and shape colors passed as parameters.
   */
  public void setUp(LinkedHashMap<String, Shape> shapes, LinkedHashMap<String, Color> shapeColors) {
    this.shapes = shapes;
    this.shapeColors = shapeColors;
  }

  /**
   * Uses the shapes and shapeColors members to create the appropriate graphics.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    for (HashMap.Entry<String, Shape> entry : shapes.entrySet()) { // take array and make shapes
      g2.setColor(shapeColors.get(entry.getKey()));
      g2.fill(entry.getValue());
    }
  }
}
