package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import cs3500.animator.model.IAnimatorModelView;
import cs3500.animator.model.IMyShape;

public class AnimatorPanel extends JPanel {
  private IAnimatorModelView model;

  /**
   * Constructor.
   * @param model use this model to construct this AnimatorPanel
   */
  public AnimatorPanel(IAnimatorModelView model) {
    this.model = model;

    this.setLayout(new BorderLayout());
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    for (IMyShape shape : model.getShapes()) {
      switch (shape.getShapeType()) {
        case OVAL:
          g2d.setColor(shape.getCol().scToColor());
          if (shape.getFill() && shape.getVisibility()) {
            g.fillOval(Math.round((float)shape.getPosX()),
                    Math.round((float)shape.getPosY()),
                    Math.round((float)shape.getWidth()),
                    Math.round((float)shape.getHeight()));
          }
          else if (shape.getVisibility()) {
            g.drawOval(Math.round((float)shape.getPosX()),
                    Math.round((float)shape.getPosY()),
                    Math.round((float)shape.getWidth()),
                    Math.round((float)shape.getHeight()));
          }
          break;
        case RECTANGLE:
          g2d.setColor(shape.getCol().scToColor());
          if (shape.getFill() && shape.getVisibility()) {
            g.fillRect(Math.round((float)shape.getPosX()),
                    Math.round((float)shape.getPosY()),
                    Math.round((float)shape.getWidth()),
                    Math.round((float)shape.getHeight()));
          }
          else if (shape.getVisibility()) {
            g.drawRect(Math.round((float)shape.getPosX()),
                    Math.round((float)shape.getPosY()),
                    Math.round((float)shape.getWidth()),
                    Math.round((float)shape.getHeight()));
          }
          break;
        default:
          break;
      }
    }
  }


}
