# Task
A console application for simulating movement of multiple rovers on a grid (plateau). Takes user input for:
- Upper right coordinate of the grid as "X Y", e.g. "10 10"
- Each rover's starting position as "X Y Direction", e.g. "1 1 N"
- Each rover's movement plan as a sequence, e.g. "LRLRMM" 

# Implementation
- Plateau: Implemented as a singleton to ensure a single instance of the plateau. It also controls that rovers don't go out of its bounds.
- Rovers: Created strictly with a reference to a plateau. 
- Movement plan: Translated into command interfaces. 
- Direction: represented as an enum. 

## Error handling
Each of the functions with user input in App.java checks the validity of the input and prompts to enter it again until a valid line is obtained. 