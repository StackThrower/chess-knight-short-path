# chess-knight-short-path
The application calculates a list of all possible paths that one knight piece in the starting position could take to reach the ending position in 3 moves.

### Task
Create a Java application that should represent an empty chessboard where the user will be able to enter a starting position and an ending position. The application should then calculate a list of all possible paths that one knight piece in the starting position could take to reach the ending position in 3 moves. Some inputs might not have a solution, in this case the program should display a message that no solution has been found. Otherwise, the shortest path (if that exists) should be returned.

Please note:
- Although a graphical display would be welcome, this is not required.
- Input can be given by the command line, a text file or whatever you might prefer.
- Output could also be kept simple: just print out the path in a textual format (i.e. A2 -> B5 etc)
- Emphasis will be given on the algorithm, general code structure and how the program could be extended.
- Unit tests would be highly appreciated

### How to start the program
- java -cp chess-khnight-short-path-1.0.1.jar com.chess.Main

### Test input
Kd4 Tc5 BH7

### Tests
All transit steps are market with numbers.
![Alt text](screen1.png "Test screen")

### Pieces
* K - (♞)Knight
* B - (♗)Bishop
* T - (@)Target

