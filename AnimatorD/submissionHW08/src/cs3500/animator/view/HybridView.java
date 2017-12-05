package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.JComponent;

import cs3500.animator.controller.IController;
import cs3500.animator.model.AbsAnimation;
import cs3500.animator.model.AbsMyShape;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorModelView;
import cs3500.animator.model.IMyShape;

/**
 * An interactive version of the VisualView.
 */
public class HybridView extends JFrame implements IView {
  private static final int DEFAULT_WINDOW_WIDTH = 1000;
  private static final int DEFAULT_WINDOW_HEIGHT = 1000;

  //printing button
  JButton printButton = new JButton("Print");
  private final JButton playButton = new JButton("play");
  private final JButton pauseButton = new JButton("pause");
  private final JButton restartButton = new JButton("restart");
  private final JButton saveButton = new JButton("save a file");
  private final JCheckBox loopButton = new JCheckBox("loop");
  private final JTextField tickInput = new JTextField(1);
  private final JLabel tickRateLabel = new JLabel();

  private IAnimatorModelView model;
  private IController controller;
  AnimatorPanel panel;
  private Appendable output;
  private JComponent shapeButtons;

  /**
   * Convinience Constructor.
   */
  public HybridView() {
    this.model = new AnimatorModel();
    output = new StringBuilder();
    this.svgDisplay();
  }

  /**
   * Constructs a HybridView.
   *
   * @param model    the model.
   * @param tickRate tick per second.
   */
  public HybridView(IAnimatorModelView model, int tickRate) {
    this.model = model;
    output = new StringBuilder();
    this.svgDisplay();
  }

  @Override
  public void svgDisplay() {
    output = new StringBuilder();
    StringBuilder bgnLines = new StringBuilder();

    bgnLines.append(String.format("<?xml version=\"1.0\"?>%n<svg width=\"800\" height=\"600"));
    bgnLines.append("\" viewPort=\"0 0 800 600\" version=\"1.1\" ");
    bgnLines.append(String.format("xmlns=\"http://www.w3.org/2000/svg\">%n"));

    try {
      output.append(bgnLines);
    } catch (IOException e) {
      throw new IllegalStateException("This SVGView was not able to append to output");
    }

    for (AbsMyShape shape : model.getShapes()) {
      StringBuilder shapeString = new StringBuilder();
      shapeString.append(shape.toSVG() + "\n");

      for (AbsAnimation anim : model.getAnims()) {
        if (anim.getName().equals(shape.getName())
                && (anim.getType() == AbsAnimation.AnimType.APPEAR)) {
          String appear = String.format("\t\t<animate attributeType=\"XML\" attributeName="
                  + "\"visibility\" from=\"hidden\" to=\"visible\" begin=\"%d\" dur=\"0.001s\" "
                  + "fill=\"freeze\"/>%n", anim.getBgn());
          shapeString.append(appear);
        } else if (anim.getName().equals(shape.getName())
                && (anim.getType() == AbsAnimation.AnimType.DISAPPEAR)) {
          String disappear = String.format("\t\t<animate attributeType=\"XML\" attributeName="
                  + "\"visibility\" from=\"visible\" to=\"hidden\" begin=\"%d\" dur=\"0.001s\" "
                  + "fill=\"freeze\"/>%n", anim.getBgn());
          shapeString.append(disappear);
        } else if (anim.getName().equals(shape.getName())) {
          shapeString.append(anim.toSVG() + "\n");
        }
      }

      if (shape.getShapeType() == IMyShape.ShapeType.RECTANGLE) {
        shapeString.append("</rect>\n"); //closing a shape
      } else if (shape.getShapeType() == IMyShape.ShapeType.OVAL) {
        shapeString.append("</ellipse>\n");
      }

      try {
        output.append(shapeString);
      } catch (IOException e) {
        throw new IllegalStateException("A shape was not able to get appended to the output"
                + " of this SVGView");
      }
    }

    try {
      output.append("</svg>");
    } catch (IOException e) {
      throw new IllegalStateException("Closing line was not able to get appended to the output"
              + " of this SVGView");
    }
  }

  @Override
  public void display() {
    this.setTitle("Animator");
    this.setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());


    this.panel = new AnimatorPanel(model);
    panel.setPreferredSize(new Dimension(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT));
    JScrollPane scrollPane = new JScrollPane(panel);
    this.add(scrollPane, BorderLayout.CENTER);

    //button panel
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.NORTH);

    buttonPanel.add(printButton);
    buttonPanel.add(playButton);
    buttonPanel.add(pauseButton);
    buttonPanel.add(restartButton);
    buttonPanel.add(loopButton);
    buttonPanel.add(tickInput);
    buttonPanel.add(tickRateLabel);
    buttonPanel.add(saveButton);


    playButton.setActionCommand("play");
    playButton.addActionListener(controller);
    pauseButton.setActionCommand("pause");
    pauseButton.addActionListener(controller);
    restartButton.setActionCommand("restart");
    restartButton.addActionListener(controller);
    loopButton.setActionCommand("loop");
    loopButton.addActionListener(controller);
    saveButton.setActionCommand("save");
    saveButton.addActionListener(controller);
    printButton.setActionCommand("print");
    printButton.addActionListener(controller);
    tickRateLabel.setText("Current Speed = " + controller.getTickRate());


    tickInput.setActionCommand("tickInput");
    tickInput.addActionListener(controller);

    this.add(shapeButtons, BorderLayout.EAST);

    this.setVisible(true);

    int endTime = model.getEndTime();

    while (true) {
      if (controller.getLoop() && (controller.getT() == endTime)) {
        System.out.println("is looping and animation is over");
        controller.setT(0);
        controller.restartTimer();
      }
      this.repaint();
    }
  }

  @Override
  public void setModel(IAnimatorModelView model) {
    this.model = model;
  }


  @Override
  public void setOutput(Appendable output) throws RuntimeException {
    this.output = output;
  }

  @Override
  public JTextField getTickInput() throws UnsupportedOperationException {
    return this.tickInput;
  }


  @Override
  public void setTickRateLabel(String s) throws UnsupportedOperationException {
    tickRateLabel.setText(s);
  }

  @Override
  public Appendable getOutput() {
    return output;
  }

  @Override
  public void setController(IController controller) {
    this.controller = controller;
  }

  public void setShapeButtons(JComponent shapeButtons) {
    this.shapeButtons = shapeButtons;
  }

  @Override
  public String getXMLData() {
    StringBuilder shapeString = new StringBuilder();
    shapeString.append("\n");
    for (AbsMyShape shape : model.getShapes()) {

      shapeString.append(shape.toSVG() + "\n");

      for (AbsAnimation anim : model.getAnims()) {
        if (anim.getName().equals(shape.getName())
                && (anim.getType() == AbsAnimation.AnimType.APPEAR)) {
          String appear = String.format("\t\t<animate attributeType=\"XML\" attributeName="
                  + "\"visibility\" from=\"hidden\" to=\"visible\" begin=\"%d\" dur=\"0.001s\" "
                  + "fill=\"freeze\"/>%n", anim.getBgn());
          shapeString.append(appear);
        } else if (anim.getName().equals(shape.getName())
                && (anim.getType() == AbsAnimation.AnimType.DISAPPEAR)) {
          String disappear = String.format("\t\t<animate attributeType=\"XML\" attributeName="
                  + "\"visibility\" from=\"visible\" to=\"hidden\" begin=\"%d\" dur=\"0.001s\" "
                  + "fill=\"freeze\"/>%n", anim.getBgn());
          shapeString.append(disappear);
        } else if (anim.getName().equals(shape.getName())) {
          shapeString.append(anim.toSVG() + "\n");
        }
      }

      if (shape.getShapeType() == IMyShape.ShapeType.RECTANGLE) {
        shapeString.append("</rect>\n"); //closing a shape
      } else if (shape.getShapeType() == IMyShape.ShapeType.OVAL) {
        shapeString.append("</ellipse>\n");
      }

      try {
        output.append(shapeString);
      } catch (IOException e) {
        throw new IllegalStateException("A shape was not able to get appended to the output"
                + " of this SVGView");
      }
    }
    return output.toString();
  }

}
