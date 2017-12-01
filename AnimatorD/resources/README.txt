DESIGN CHANGES SINCE LAST ASSIGNMENT:
  -Added a name field to AbsMyShape
  -Added width, height fields to AbsMyShape (represents the rectangular base dimensions)
  -Created a shapeType enum
  -Created a animType enum
  -Removed most contructors and created a single constructor
  -Introduced AnimComp for determining the order of animations in a list
  -Introduced an IAnimatorView and a IAnimatorModelView
  -Introduced ShapeColor instead of java.awt.Color
  -Introduced ViewFactory to create views
  -Introduced an AnimatorPanel
  -Introduced IView and its childre: TextView, SVGView, VisualView
  -Introduced EasyAnimator

DESIGN:
  This programme follows the Model, Controller and View pattern. The controller is replaced by the class
  EasyAnimator's main method.

****IMPORTANT****:
  When running the programme's main method, one can give arguments to specify what kind of 
  animation should be viewed. To input args into the main method, you must end the args with a " ."