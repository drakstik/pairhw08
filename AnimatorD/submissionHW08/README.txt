README for the AnimatorPlus.
By Mira Maalouf and David Mberingabo

**IMPORTANT USER INFORMATION**
To play provider version of views, use code line "-provider true" alongside the normal command lines.

CHANGES MADE SINCE PREVIOUS SUBMISSION:
 - Created a HybridView for a user interactive view. This view has multiple buttons that allow it to:
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

PROVIDER CODE CRITIQUE:
- Looping cannot be enabled while the animation has begun and is playing.
- Removing shapes cannot be done while the animation has begun and is playing.
- Use of uncoupled animations, instead of having an interface or abstract top level class.
	All methods had to be written for each operation class in the main code.
	

OUR EXPERIENCE WITH ASSIGNMENT 8:
- While doing this assignment we realized the importance of general design patterns like MVC and
  how this helps colaboration between programmers' different code. We were surprised to see that 
  even though our providers and us wrote our code seperately, it was still nicely adaptable and
  the same general design; We did not expect our code to be so similar. Having to go through other's
  code without having any idea of the implementation was strange at first, but once we understood
  their code in relation to ours it became very simple. Assignment 8 helped us understand the design
  concepts better, not only by analyzing the provider’s code, but by modifying our own to send to our
  customers. It forced me to take a  whole new point of view to look at my own code with other 
  people's eyes. 


