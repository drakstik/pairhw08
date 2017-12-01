README for the Animator.
By Rick Falleto and David Mberingabo

****IMPORTANT****:
  When running the programme's main method, one can give arguments to specify what kind of 
  animation should be viewed. To input args into the main method, you must end the args with a " ."
  For example:
      - Instead of writing "-iv interactive -if c:\users\buildings.txt", you would write
        "-iv interactive -if c:\users\buildings.txt ."

CHANGES MADE SINCE PREVIOUS SUBMISSION:
 - Created and implemented a HybridView for a user interactive view. This view has multiple buttons
   that allow it to:
      -pause
      -loop
      -play
      -restart
      -shape selection
      -SVG file export
 - Added an interface IController, abstract class AbsController and its children classes
   ControllerHybrid, ControllerVisual, ControllerSVG and ControllerText. 
 - We implemented a go() method for the controller that calls view.display() and handles
   the representation of the model.
 - We moved timer, t and tickrate implementations from the VisualView, the HybridView and the TextView
   to the controllers.
 - We moved the button-handling from the view to the controller,

DESIGN:
 - This programme follows the Model, Controller and View pattern. The model as the keeper of
   the information (mainly composed of the animations and shapes). The view as the system of classes
   that shows the model to the user. The controller as the regulator/messenger between the view and the 
   model.

