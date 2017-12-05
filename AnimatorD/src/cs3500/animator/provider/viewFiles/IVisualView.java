package cs3500.animator.provider.viewFiles;

import java.awt.Color;
import java.awt.Shape;
import java.util.LinkedHashMap;

import cs3500.animator.provider.controllerFiles.IVisualController;

/**
 * Interface for the VisualView.
 */
public interface IVisualView extends View {

  /**
   * Sets the ActionListener to trigger the VisualController function that must be called when a
   * time tick occurs.
   */
  void setTimerListener(IVisualController listener);

  /**
   * Passes the VisualViewPanel the maps of shapes and colors to display and refreshes the panel.
   */
  void setUpPanel(LinkedHashMap<String, Shape> shapes,
                  LinkedHashMap<String, Color> shapeColors);
}
