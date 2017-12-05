package cs3500.animator.provider.viewFiles;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import cs3500.animator.provider.controllerFiles.IHybridController;


/**
 * The HybridView takes in info from the Hybridview, and displays it in visual format, while also
 * outputting it in SVG format. If an output file is provided, it outputs the SVG to that file,
 * if not, it outputs to the console.
 */
public class HybridView extends VisualView implements IHybridView {

  //  private Timer t;
  //  private int timeTick;
  //  private int ticksPerSec;
  //  private VisualViewPanel panel;
  private JButton startButton;
  private JButton pauseButton;
  private JButton restartButton;
  private JTextField removeShapesText;
  private JTextField newSpeedText;
  private JTextArea textOutput;
  private JButton newSpeedButton;
  private JButton removeButton;
  private JButton loopButton;
  private JPanel textPanel;
  private JPanel removeShapesPanel;
  private String outputFile;
  private String xmlData;
  private StringBuilder finalOutput = new StringBuilder();
  private Boolean loopFlag = false;

  /**
   * Constructor for HybridView object.
   */
  public HybridView(int ticksPerSec, String outputFile) {
    super(ticksPerSec);

    this.outputFile = outputFile;

    //text description panel
    textPanel = new JPanel();
    textPanel.setLayout(new BorderLayout());
    textPanel.setPreferredSize(new Dimension(500,200));
    textOutput = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(textOutput);
    scrollPane.setPreferredSize(new Dimension(500, 150));
    textPanel.add(scrollPane, BorderLayout.PAGE_START);

    //remove button sub-Panel (within text description)
    removeShapesPanel = new JPanel();
    removeShapesPanel.setLayout(new FlowLayout());
    removeButton = new JButton("REMOVE");
    loopButton = new JButton("Enable Looping");
    removeShapesPanel.add(removeButton);
    textPanel.add(removeShapesPanel, BorderLayout.PAGE_END);

    //Pre-play text field
    removeShapesText = new JTextField(20);
    removeShapesText.getDocument().addDocumentListener(new DocumentListener() {

      public void insertUpdate(DocumentEvent e) {
        turnOnRemoveButton();
      }

      public void removeUpdate(DocumentEvent e) {
        turnOnRemoveButton();
      }

      public void changedUpdate(DocumentEvent e) {
        turnOnRemoveButton();
      }

      public void turnOnRemoveButton() {
        removeButton.setEnabled(true);
      }
    });
    removeShapesPanel.add(removeShapesText);
    removeShapesPanel.add(loopButton);
    this.add(textPanel, BorderLayout.PAGE_START);

    //button panel
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.PAGE_END);

    //startButton button
    startButton = new JButton("Start");
    buttonPanel.add(startButton);

    //pauseButton button
    pauseButton = new JButton("Pause/Play");
    pauseButton.setEnabled(false);
    buttonPanel.add(pauseButton);

    //restartButton button
    restartButton = new JButton("Restart");
    restartButton.setEnabled(false);
    buttonPanel.add(restartButton);

    //newSpeedButton button
    newSpeedButton = new JButton("Change Speed");
    newSpeedButton.setEnabled(false);
    buttonPanel.add(newSpeedButton);

    //increase speed text field
    newSpeedText = new JTextField(10);
    newSpeedText.getDocument().addDocumentListener(new DocumentListener() {

      public void changedUpdate(DocumentEvent e) {
        turnOnSpeedButton();
      }

      public void removeUpdate(DocumentEvent e) {
        turnOnSpeedButton();
      }

      public void insertUpdate(DocumentEvent e) {
        turnOnSpeedButton();
      }

      public void turnOnSpeedButton() {
        newSpeedButton.setEnabled(true);
        //        if (Integer.parseInt(newSpeedText.getText())<=0){
        // newSpeedButton.setEnabled(true); }
      }
    });
    buttonPanel.add(newSpeedText);

    //KeyEvent listeners
  }

  /**
   * Set listener for view timer.
   */
  public void setTimerListener(IHybridController listener) {
    ActionListener listen = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        listener.timeCheck(timeTick);
      }
    };
    t = new Timer((1000 / ticksPerSec), listen);
  }

  @Override
  public void setStartListener(IHybridController listener) {
    startButton.addActionListener((ActionEvent e) -> {
      listener.start();
      pauseButton.setEnabled(true);
      restartButton.setEnabled(true);
      startButton.setEnabled(false);
      removeButton.setEnabled(false);
      loopButton.setEnabled(false);
      textOutput.setText("");
      textOutput.append("The Animation has Started");
      if (loopFlag) {
        textOutput.append(" and will loop indefinitely.");
      } else {
        textOutput.append(".");
      }
      textPanel.remove(removeShapesPanel);
      //this.pack();
    });

    System.out.println("setStartListener is finished being called");
  }

  @Override
  public void setPausePlayListener(IHybridController listener) {
    pauseButton.addActionListener((ActionEvent e) -> {
      listener.pausePlay();
      if (listener.isPaused()) {
        textOutput.append("\nThe Animation is Paused.");
      } else {
        textOutput.append("\nThe Animation is Playing.");
      }
    });
    pauseButton.getActionMap().put("spacePressed", pausePlayOnSpace(listener));
    pauseButton.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"spacePressed");

  }

  private Action pausePlayOnSpace(IHybridController listener) {
    Action spaceAction = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        listener.pausePlay();
        if (listener.isPaused()) {
          textOutput.append("\nThe Animation is Paused.");
        } else {
          textOutput.append("\nThe Animation is Playing.");
        }
      }
    };
    return spaceAction;
  }

  @Override
  public void setRestartListener(IHybridController listener) {
    restartButton.addActionListener((ActionEvent e) -> {
      listener.restart();
      textOutput.append("\nThe Animation was Restarted.");
    });
  }

  @Override
  public void setNewSpeedListener(IHybridController listener) {
    newSpeedButton.addActionListener((ActionEvent e) -> {
      int newSpeed = Integer.valueOf(newSpeedText.getText());
      listener.changeSpeed(newSpeed);
      textOutput.append("\nThe Animation's speed has changed to " + newSpeed);
    });
  }

  /**
   * Set listener function for remove shape operation.
   */
  public void setRemoveShapeListener(IHybridController listener) {
    removeButton.addActionListener((ActionEvent e) -> {
      ArrayList<String> shapesToRemove = new ArrayList<String>();
      Scanner scan = new Scanner(removeShapesText.getText()).useDelimiter(",");
      while (scan.hasNext()) {
        String shapeToRemove = scan.next();
        try {
          listener.removeShapeName(shapeToRemove);
          textOutput. append("\nShape " + shapeToRemove + " was removed from the animation.");
        } catch (IllegalArgumentException f) {
          textOutput.append("\nThe Shape named " + shapeToRemove + " does not exist in the " +
                  "animation, are you sure you spelled it correctly,\nor that you did not " +
                  "already remove it?");
        }
      }
    });
  }

  /**
   * Set listener function for loop button.
   */
  public void setLoopListener(IHybridController listener) {
    loopButton.addActionListener((ActionEvent e) -> {
      listener.enableLoop();
      //System.out.println("LOOP ENABLED");
      loopFlag = true;
    });
  }

  @Override
  public void pauseTimer() {
    t.stop();
  }

  @Override
  public void playTimer() {
    //this.remove(textPanel);
    this.repaint();
  }

  @Override
  public void display() {
    timeTick = 0;
  }

  public void displayDescription(String description) {
    textOutput.append(description);
  }

  @Override
  public void changeSpeed(int newTicksPerSec) {
    ticksPerSec = newTicksPerSec;
    t.setDelay(1000 / newTicksPerSec);
  }

  @Override
  public void setupSVG(int height, int width) {
    finalOutput.append("\n<svg width=\"" + width + "\" height=\"" + height + "\" " +
            "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">");
    if (loopFlag) {
      finalOutput.append("\n<rect>");
      finalOutput.append("\n\t<animate id=\"base\" begin=\"0;base.end\" dur=\"10000.0ms\"" +
                      " attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>");
      finalOutput.append("\n</rect>");
    }

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
          System.out.println("Printing to file: " + outputFile);
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
    } catch (NullPointerException n) {
      System.out.println(finalOutput);
    }
  }


}
