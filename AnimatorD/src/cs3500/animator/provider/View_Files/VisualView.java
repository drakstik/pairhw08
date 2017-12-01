package cs3500.animator.provider.View_Files;

import java.awt.Color;
import java.awt.Shape;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs3500.animator.controller.VisualController;

/**
 * The Visual View relies on the Visual Controller to feed it the appropriately packaged information
 * for it to display.
 */
public class VisualView extends JFrame implements IVisualView {

  protected Timer t;
  protected int timeTick;
  protected int ticksPerSec;
  protected VisualViewPanel panel;

  /**
   * Constructor for VisualView object.
   */
  public VisualView(int ticksPerSec) {
    this.ticksPerSec = ticksPerSec;
    this.panel = new VisualViewPanel();
    panel.setBounds(0, 0, 600, 400);
    JScrollPane scrollPane = new JScrollPane(panel);
    this.add(scrollPane, BorderLayout.CENTER);
    this.setVisible(true);
    this.setSize(600, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Visual View");
  }

  @Override
  public void setTimerListener(VisualController listener) {
    ActionListener listen = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        listener.timerAction(timeTick);
      }
    };
    t = new Timer((1000 / ticksPerSec), listen);
  }

  @Override
  public void display() {
    timeTick = 0;
    t.start();
  }

  @Override
  public void setUpPanel(LinkedHashMap<String, Shape> shapes,
                         LinkedHashMap<String, Color> shapeColors) {
    panel.setUp(shapes, shapeColors);
    panel.repaint();
    timeTick++;
    t.start();
  }
}
