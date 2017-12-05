package cs3500.animator.provider.view;

/**
 * Interface for a generic View, this is extended further by all other views, adding in thier
 * specific functionalities.
 */
public interface View {

  /**
   * Function called by the controller that causes the view to begin creating its outputs and
   * displaying the data from the controller.
   */
  void display();
}
