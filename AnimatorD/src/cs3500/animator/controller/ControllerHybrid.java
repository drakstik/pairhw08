package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.JFileChooser;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

import cs3500.animator.model.AbsAnimation;
import cs3500.animator.model.AbsMyShape;
import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.model.Utils;
import cs3500.animator.provider.Controller_Files.IHybridController;
import cs3500.animator.view.HybridView;

/**
 * A controller that interfaces between a model and a HybridView to allow a user
 * to interactively play an animation.
 */
public class ControllerHybrid implements IController, IHybridController {
  private IAnimatorModel model;
  private HybridView view;
  private cs3500.animator.provider.View_Files.HybridView pview;

  boolean loop = false;
  boolean paused = true;

  private Timer timer;
  private int t = 0;
  private double tickRate = 1.0;
  private final Utils utils = new Utils();

  /**
   * Constructor provider.
   * @param model the model this controller should be contructed with.
   */
  public ControllerHybrid(IAnimatorModel model, cs3500.animator.provider.View_Files.HybridView pview) {
    this.model = model;
    this.view = new HybridView();
    int tickRateMS = ((Double) (1000 / tickRate))
            .intValue();
    timer = new Timer(tickRateMS, this);
    timer.setActionCommand("timer");
    this.pview = pview;

    //model.setController(this);
    view.setController(this);
    this.viewDisplay();

  }

  /**
   * Constructor.
   * @param model the model this controller should be contructed with.
   * @param view  the view this controller should be contructed with.
   */
  public ControllerHybrid(IAnimatorModel model, HybridView view) {
    this.model = model;
    this.view = view;

    int tickRateMS = ((Double) (1000 / tickRate))
            .intValue();
    timer = new Timer(tickRateMS, this);
    timer.setActionCommand("timer");
    this.pview = new cs3500.animator.provider.View_Files.HybridView((int) Math.round(tickRate), "out");

    //model.setController(this);
    view.setController(this);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    switch (actionEvent.getActionCommand()) {
      case "play":
        this.paused = false;
        System.out.println(paused);
        timer.start();
        break;
      case "pause":
        this.paused = true;
        timer.stop();
        System.out.println("Animation has been paused");
        break;
      case "restart":
        t = 0;
        timer.restart();
        System.out.println("Animations were restarted");
        break;
      case "loop":
        this.loop = !loop;
        model.setLoop(!model.getLoop());
        break;
      case "tickInput":
        try {
          int ti = Integer.parseInt(view.getTickInput().getText());
          if (ti > 0) {
            tickRate = ti;
            int tickRateMS = ((Double) (1000 / tickRate))
                    .intValue();
            timer.setDelay(tickRateMS);
            view.setTickRateLabel("Current Speed = " + tickRate);
          } else {
            //TODO create window for error handling
            JOptionPane.showMessageDialog(view,  "TickRate must be a positive integer!",
                    "Error!", JOptionPane.ERROR_MESSAGE);
          }
        } catch (NumberFormatException nfe) {
          //TODO create window for error handling
          JOptionPane.showMessageDialog(view, "TickRate must be a positive integer!",
                  "Error!", JOptionPane.ERROR_MESSAGE);
        }
        //error check

        break;
      case "print":
        view.svgDisplay();
        System.out.println(view.getOutput().toString());
        break;
      case "save":
        view.svgDisplay();
        final JFileChooser FCHOOSER = new JFileChooser(".");
        int retvalue = FCHOOSER.showSaveDialog(view);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = FCHOOSER.getSelectedFile();
          try {
            if (f.createNewFile()) {
              PrintWriter pw = new PrintWriter(f);
              pw.write(view.getOutput().toString());
              pw.close();
              System.out.println("File: " + f.getAbsolutePath() + " is created!");
            } else {
              System.out.println("File already exists.");
            }
          } catch (IOException e) {
            System.out.println(e);
          }
        }
        break;
      case "timer":
        model.snapshot(t);
        t++;
        break;
      default:
        throw new IllegalArgumentException("Unknown actionEvent");
    }
  }

  @Override
  public void setTickRate(double tr) {
    this.tickRate = tr;
    int tickRateMS = ((Double) (1000 / tickRate))
            .intValue();
    timer.setDelay(tickRateMS);
  }

  @Override
  public void run() {
    view.setShapeButtons(this.setUpButtons());
    view.display();
  }

  @Override
  public JComponent setUpButtons() {
    JPanel radioPanel = new JPanel();
    radioPanel.setBorder(BorderFactory.createTitledBorder("Shapes"));

    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));

    JCheckBox[] checkBoxes = new JCheckBox[model.getShapes().size()];

    for (int i = 0; i < checkBoxes.length; i++) {
      JCheckBox jc = checkBoxes[i];
      AbsMyShape s = model.getShapes().get(i);
      jc = new JCheckBox(s.getName());
      jc.setSelected(true);

      jc.setActionCommand("Shape: " + s.getName());
      JCheckBox finalJc = jc;
      jc.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
          if (finalJc.isSelected()) {
            for (AbsAnimation anim : model.getInitAnims()) {
              if (s.getName().equals(anim.getName())) {
                model.addAnim(anim);
              }
            }
          } else {
            ArrayList<AbsAnimation> toRemove = new ArrayList<>();
            for (AbsAnimation anim : model.getAnims()) {
              if (anim.getName().equals(s.getName())) {
                toRemove.add(anim);
              }
            }
            model.getAnims().removeAll(toRemove);
          }
          model.snapshot(t - 1);
        }
      });
      radioPanel.add(jc);
    }

    JScrollPane scrollPane2 = new JScrollPane(radioPanel);
    return scrollPane2;
  }

  @Override
  public double getTickRate() {
    return tickRate;
  }

  @Override
  public boolean getLoop() throws UnsupportedOperationException {
    return loop;
  }

  @Override
  public int getT() throws UnsupportedOperationException {
    return t;
  }

  @Override
  public void setT(int tick) {
    this.t = tick;
  }

  @Override
  public void restartTimer() {
    timer.restart();
    this.timeCheck(0);
  }

  @Override
  public void timeCheck(int time) {
    if(time >= model.getEndTime() && loop) {
      this.restart();
    }
    model.snapshot(time);
    pview.setUpPanel(utils.MyShapesToShapes(model.getShapes()),
              utils.MyShapesToColors(model.getShapes()));

  }

  @Override
  public void start() {
    this.paused = false;
    this.timer.start();
    this.pview.playTimer();
  }

  @Override
  public void pausePlay() {
    if (this.isPaused()) {
      this.paused = false;
      pview.playTimer();
      this.timer.start();
    } else {
      this.paused = true;
      pview.pauseTimer();
      this.timer.stop();
    }
  }

  @Override
  public boolean isPaused() {
    return this.paused;
  }

  @Override
  public void removeShapeName(String shapeString) {
    ArrayList<AbsAnimation> toRemove = new ArrayList<>();
    for (AbsAnimation anim : model.getAnims()) {
      if (anim.getName().equals(shapeString)) {
        toRemove.add(anim);
      }
    }
    model.getAnims().removeAll(toRemove);
  }

  @Override
  public void restart() {
    this.restartTimer();
    this.paused = false;
  }

  @Override
  public void enableLoop() {
    this.loop = true;
  }

  @Override
  public void changeSpeed(int ticksPerSec) {
    this.setTickRate(ticksPerSec);
  }

  @Override
  public void viewDisplay() {
    pview.setTimerListener(this);
    pview.setStartListener(this);
    pview.setRestartListener(this);
    pview.setPausePlayListener(this);
    pview.setLoopListener(this);
    pview.setNewSpeedListener(this);
    pview.setRemoveShapeListener(this);
    pview.display();

  }
}
