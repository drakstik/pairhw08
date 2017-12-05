package cs3500.animator.provider.viewFiles;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This is an implementation of the ITextView interface that uses Java Swing to provide a textual
 * description of the animation taking place it shows any error messages using a pop-up dialog box.
 */
public class TextView extends JFrame implements ITextView {
  private int ticksPerSec;
  private JButton commandButton;
  private JButton quitButton;
  private JPanel buttonPanel;
  private JPanel animationPanel;
  private JScrollPane scrollPane;
  private JTextField input;
  private JTextArea output;
  private String outputFile = "";
  private StringBuilder textOutput = new StringBuilder();
  Consumer<String> commandCallback;

  /**
   * This constructor takes in an output file, and outputs the text description to it.
   */
  public TextView(int ticksPerSec, String outputFile) {
    super();
    this.outputFile = outputFile;
    this.ticksPerSec = ticksPerSec;
    this.setTitle("Animation Text View");
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //use a border layout with drawing panel in center and button panel in south
    this.setLayout(new BorderLayout());
    animationPanel = new JPanel();
    animationPanel.setPreferredSize(new Dimension(500, 500));
    scrollPane = new JScrollPane(animationPanel);
    //this.add(scrollPane,BorderLayout.CENTER);

    //Output Text Area
    output = new JTextArea(100, 100);
    animationPanel.add(output);
    JScrollPane scrollPane2 = new JScrollPane(output);
    this.add(scrollPane2, BorderLayout.CENTER);

    //button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    //quit button
    quitButton = new JButton("Quit");
    quitButton.addActionListener((ActionEvent e) -> {
      System.exit(0);
    });
    buttonPanel.add(quitButton);

    commandCallback = null;

    this.pack();
  }

  /**
   * This constructor works when no output file is given, it prints the animation description in
   * a JFrame.
  */
  public TextView(int ticksPerSec) {
    super();
    this.ticksPerSec = ticksPerSec;
    this.setTitle("Animation Text View");
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //use a border layout with drawing panel in center and button panel in south
    this.setLayout(new BorderLayout());
    animationPanel = new JPanel();
    animationPanel.setPreferredSize(new Dimension(500, 500));
    scrollPane = new JScrollPane(animationPanel);
    //this.add(scrollPane,BorderLayout.CENTER);

    //Output Text Area
    output = new JTextArea(100, 100);
    animationPanel.add(output);
    JScrollPane scrollPane2 = new JScrollPane(output);
    this.add(scrollPane2, BorderLayout.CENTER);

    //button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    //quit button
    quitButton = new JButton("Quit");
    quitButton.addActionListener((ActionEvent e) -> {
      System.exit(0);
    });
    buttonPanel.add(quitButton);

    commandCallback = null;

    this.pack();
    this.setVisible(true);
  }


  @Override
  public void display() {
    try {
      if (outputFile.equals("")) {
        System.out.println(textOutput);
      } else {
        try {
          PrintWriter writer = new PrintWriter(outputFile, "utf-8");
          writer.print(textOutput);
          writer.close();
        } catch (UnsupportedEncodingException u) {
          u.printStackTrace();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (NullPointerException n) {
          System.out.println(textOutput);
        }
      }
    } catch (NullPointerException n) {
      System.out.println(textOutput);
    }
    output.append(textOutput.toString());
    this.refresh();
    this.setVisible(true);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void describeAnimation(String controllerStringOut) {
    textOutput.append(controllerStringOut);
  }
}
