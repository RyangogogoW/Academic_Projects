package PE1;

import java.util.Arrays;

/* PLEASE DO NOT MODIFY A SINGLE STATEMENT IN THE TEXT BELOW.
READ THE FOLLOWING CAREFULLY AND FILL IN THE GAPS

I hereby declare that all the work that was required to 
solve the following problem including designing the algorithms
and writing the code below, is solely my own and that I received
no help in creating this solution and I have not discussed my solution 
with anybody. I affirm that I have read and understood
the Senate Policy on Academic honesty at 
https://secretariat-policies.info.yorku.ca/policies/academic-honesty-senate-policy-on/
and I am well aware of the seriousness of the matter and the penalties that I will face as a 
result of committing plagiarism in this assignment.

BY FILLING THE GAPS,YOU ARE SIGNING THE ABOVE STATEMENTS.

Full Name: Huan Wu
Student Number:	219208263
Course Section:	EECS 2021 Section A
*/

public class PE1 {
	Maze dogMaze;

	/**
	 * This method sets up the maze using the given input argument
	 * 
	 * @param maze is a maze that is used to construct the dogMaze
	 */
	public void setup(String[][] maze) {
		/*
		 * insert your code here to create the dogMaze using the input argument.
		 */
		dogMaze = new Maze(maze);
	}

	/**
	 * This method returns true if the number of gates in dogMaze >= 2.
	 * 
	 * @param maze which was constructed and find out if it has at least two gates.
	 * @return it returns true, if enough gate exists (at least 2), otherwise false.
	 */
	public boolean enoughGate() {

		// insert your code here. Change the return value to fit your purpose.
		int gateCount = findGate(dogMaze.getMaze(), 0);

		if (gateCount >= 2)
			return true;
		else
			return false;
	}

	/**
	 * This method finds a path from the entrance gate to the exit gate.
	 * 
	 * @param row    is the index of the row, where the entrance is.
	 * @param column is the index of the column, where the entrance is.
	 * @return it returns a string that contains the path from the start to the end.
	 *         The return value should have a pattern like this (i,j)(k,l),... The
	 *         first pair of the output must show the entrance given as the input
	 *         parameter (i.e. (row,column) No whitespace is allowed in the output.
	 */
	public String findPath(int row, int column) {
		// insert your code here. Change the return value to fit your purpose.

		String path = "(" + row + "," + column + ")";
		String exitGate = findExit(dogMaze.getMaze(), row, column);
		int endRow = Character.getNumericValue(exitGate.charAt(0)) + 1; // +1 because we need to extend the maze.
		int endColumn = Character.getNumericValue(exitGate.charAt(2)) + 1; // +1 because we need to extend the maze.

		String[][] newMaze = mazeExtend(dogMaze.getMaze());

		boolean pathFound = pathToExit(newMaze, row + 1, column + 1, endRow, endColumn); // +1 because we need to extend
																							// the maze.
		if (pathFound) {
			path += pathPrint(newMaze, row + 1, column + 1, endRow, endColumn); // +1 because we need to extend the
																				// maze.
		}
		return path;
	}

	/**
	 * This method transfers path to coordinates version.
	 * 
	 * @param maze      is a that is constructed to construct dogMaze.
	 * @param row       is the index of the row, where the entrance is.
	 * @param column    is the index of the column, where the entrance is.
	 * @param endRow    is the index of the row, where the exit is.
	 * @param endColumn is the index of the column, where the exit is.
	 * @return is return a string that contains the path by coordinate in addition
	 *         to the entrance position.
	 */
	public String pathPrint(String[][] maze, int row, int column, int endRow, int endColumn) {
		if (row == endRow && column == endColumn) { // Base case: when the position at the exit stop the recursion
			return "";
		} else {
			maze[row][column] = "0"; // mark the current position as 0.
			if (maze[row + 1][column] == "2")
				return "(" + (row + 1 - 1) + "," + (column - 1) + ")"
						+ pathPrint(maze, row + 1, column, endRow, endColumn);
			// row and column both minus one because extended the maze before.
			else if (maze[row - 1][column] == "2")
				return "(" + (row - 1 - 1) + "," + (column - 1) + ")"
						+ pathPrint(maze, row - 1, column, endRow, endColumn);
			else if (maze[row][column + 1] == "2")
				return "(" + (row - 1) + "," + (column + 1 - 1) + ")"
						+ pathPrint(maze, row, column + 1, endRow, endColumn);
			else
				return "(" + (row - 1) + "," + (column - 1 - 1) + ")"
						+ pathPrint(maze, row, column - 1, endRow, endColumn);
		}

	}

	/**
	 * This method finds out the correct path of heading to exit with marking as 2,
	 * and incorrect path were marked as 3.
	 * 
	 * @param maze      is a that is constructed to construct dogMaze.
	 * @param row       is the index of the row, where the entrance is.
	 * @param column    is the index of the column, where the entrance is.
	 * @param endRow    is the index of the row, where the exit is.
	 * @param endColumn is the index of the column, where the exit is.
	 * @return is return a maze with the path marked with 2 and the inaccessible
	 *         path marked with 3.
	 */
	public boolean pathToExit(String[][] maze, int row, int column, int endRow, int endColumn) {
		if (maze[endRow][endColumn] == "2") { // Base case: when the exit be marked as 2 stop the recursion
			return true;
		} else if (maze[row][column] == "1" | maze[row][column] == "2") // control dog won't go to boundary and won't
																		// turn back.
			return false;
		else {
			String blockedSituation = maze[row][column];
			maze[row][column] = "2";
			// There are four cases are going up, going down, going left and going right.
			if (blockedSituation.charAt(0) == '0' && pathToExit(maze, row - 1, column, endRow, endColumn)) {

				return true;
			} else if (blockedSituation.charAt(1) == '0' && pathToExit(maze, row, column - 1, endRow, endColumn)) {

				return true;
			} else if (blockedSituation.charAt(2) == '0' && pathToExit(maze, row + 1, column, endRow, endColumn)) {

				return true;
			} else if (blockedSituation.charAt(3) == '0' && pathToExit(maze, row, column + 1, endRow, endColumn)) {

				return true;
			} else { // If none of the four conditions are satisfied, mark as 3.
				maze[row][column] = "3";
				return false;
			}
		}
	}

	/**
	 * This method extended the maze with adding boundary.
	 * 
	 * @param maze is a that is constructed to construct dogMaze.
	 * @return is return a maze with expanded boundaries.
	 */
	public String[][] mazeExtend(String[][] maze) {
		String[][] newMaze = new String[maze.length + 2][maze[0].length + 2];
		for (int i = 0; i < newMaze.length; i++) { // initial maze boundary
			for (int k = 0; k < newMaze[i].length; k++) {
				if (i == 0 | i == newMaze.length - 1) {
					newMaze[i][k] = "1"; // extend the maze with "1".
				} else {
					if (k == 0 | k == newMaze[i].length - 1) {
						newMaze[i][k] = "1"; // extend the maze with "1".
					}
				}
			}
		}

		for (int i = 1; i < newMaze.length - 1; i++) {

			for (int k = 1; k < newMaze[i].length - 1; k++) {
				newMaze[i][k] = maze[i - 1][k - 1]; // fill the extended maze by the original maze.
			}
		}

		return newMaze;
	}

	/**
	 * This method finds out the address(coordinate) of the exit.
	 * 
	 * @param maze   is a that is constructed to construct dogMaze.
	 * @param row    is the index of the row, where the entrance is.
	 * @param column is the index of the column, where the entrance is.
	 * @return is return the address(coordinate) of exit with the form of
	 *         (row,column).
	 */
	public String findExit(String[][] maze, int row, int column) {
		String[] gate = new String[2];
		String[] gateAddress = new String[2]; // To store the address(coordinate) of the gates.
		int t = 0; // Counter of gate
		for (int i = 0; i < maze.length; i++) {
			for (int k = 0; k < maze[i].length; k++) {
				boolean isGate = false;
				for (int m = 0; m < 4; m++) {
					if (maze[i][k].charAt(m) == '0') {
						if (m == 0) {
							if (i - 1 < 0)
								isGate = true;
						} else if (m == 1) {
							if (k - 1 < 0)
								isGate = true;
						} else if (m == 2) {
							if (i + 1 > maze.length - 1)
								isGate = true;
						} else if (m == 3) {
							if (k + 1 > maze[i].length - 1)
								isGate = true;
						}
					}
				}
				if (isGate == true) {
					gate[t] = maze[i][k];
					gateAddress[t] = i + "," + k;
					t++;
				}
			}

		}

		for (String s : gateAddress) {
			// When the gate's address is not same as the entrance then that one should be
			// the exit.
			if (Character.getNumericValue(s.charAt(0)) != row || Character.getNumericValue(s.charAt(2)) != column) {
				int index = 0;
				for (int i = 0; i < gateAddress.length; i++) {
					if (gateAddress[i].equals(s))
						index = i;
				}
				return gateAddress[index];
			} else {

			}

		}
		return "";

	}

	/**
	 * This method finds out how many gate does the maze have.
	 * 
	 * @param maze is a that is constructed to construct dogMaze.
	 * @param i    is the index of the row, where the function is check for the
	 *             gate.
	 * @return is return the number of gate of the maze.
	 */

	public int findGate(String[][] maze, int i) {
		if (i == maze.length)
			return 0;

		int count = 0; // count is used as a gate counter.

		// Special situation 1: when the maze is a maze with a single row.
		if (maze.length == 1) {
			for (int k = 0; k < maze[0].length; k++) {
				if (k == 0) { // When the position is checking at the most left column.
					if (maze[i][k].charAt(0) == '0') // check if the direction is blocked. 0 means up, 1 means left
						count++; // 2 means down, 3 means right.
					if (maze[i][k].charAt(1) == '0')
						count++;
					if (maze[i][k].charAt(2) == '0')
						count++;
					continue;
				}
				if (k == maze[0].length - 1) { // When the position is checking at the most right column.
					if (maze[i][k].charAt(0) == '0')
						count++;
					if (maze[i][k].charAt(2) == '0')
						count++;
					if (maze[i][k].charAt(3) == '0')
						count++;
					continue;
				} else {
					if (maze[i][k].charAt(0) == '0')
						count++;
					if (maze[i][k].charAt(2) == '0')
						count++;
				}
			}
			return count;
		}

		// Special situation 2: when the maze is a maze with a single column.
		else if (maze[0].length == 1) {
			if (i == 0) {
				if (maze[i][0].charAt(0) == '0')
					count++;
				if (maze[i][0].charAt(1) == '0')
					count++;
				if (maze[i][0].charAt(3) == '0')
					count++;
				return count + findGate(maze, i + 1);
			} else if (i == maze.length - 1) {
				if (maze[i][0].charAt(1) == '0')
					count++;
				if (maze[i][0].charAt(2) == '0')
					count++;
				if (maze[i][0].charAt(3) == '0')
					count++;
				return count + findGate(maze, i + 1);
			} else {
				if (maze[i][0].charAt(1) == '0')
					count++;
				if (maze[i][0].charAt(3) == '0')
					count++;
				return count + findGate(maze, i + 1);
			}
		}

		// Normal situation: the size of maze is m*n(m,n>=2).
		else {
			if (i == 0) { // When the row is checking at the top.
				for (int k = 0; k < maze[i].length; k++) {
					if (k == 0) {
						if (maze[i][k].charAt(0) == '0')
							count++;
						if (maze[i][k].charAt(1) == '0')
							count++;
						continue;
					} else if (k == maze[i].length - 1) {
						if (maze[i][k].charAt(0) == '0')
							count++;
						if (maze[i][k].charAt(3) == '0')
							count++;
						continue;
					} else { // When the position is checking at the middle column.
						if (maze[i][k].charAt(0) == '0')
							count++;
						continue;
					}

				}
				return count + findGate(maze, i + 1);

			} else if (i == maze.length - 1) { // When the row is checking at the bottom.
				for (int k = 0; k < maze[i].length; k++) {
					if (k == 0) {
						if (maze[i][k].charAt(1) == '0')
							count++;
						if (maze[i][k].charAt(2) == '0')
							count++;
						continue;
					} else if (k == maze[i].length - 1) {
						if (maze[i][k].charAt(2) == '0')
							count++;
						if (maze[i][k].charAt(3) == '0')
							count++;
						continue;
					} else {
						if (maze[i][k].charAt(2) == '0')
							count++;
						continue;
					}
				}
				return count + findGate(maze, i + 1);
			}

			else { // When the row is checking at the middle.
				if (maze[i][0].charAt(1) == '0') // We only need to check the first and last position at a row if
					count++; // they are in the middle of the maze.
				if (maze[i][maze[i][0].length() - 1].charAt(3) == '0')
					count++;
				return count + findGate(maze, i + 1);
			}
		}

	}
}

/**
 * This class defines a <code> maze </code> using a 2D array. To complete the
 * code, you should not change the method signatures (header).
 *
 */

class Maze {
	private String[][] maze;

	/**
	 * This constructor makes the maze.
	 * 
	 * @param maze is a 2D array that contains information on how each cell of the
	 *             array looks like.
	 */
	public Maze(String[][] maze) {
		this.maze = new String[maze.length][maze[0].length]; // Let maze attribute have the same length as the input
																// maze.
		for (int i = 0; i < maze.length; i++) { // Deep copy.
			for (int k = 0; k < maze[i].length; k++) {
				this.maze[i][k] = maze[i][k];
			}
		}
		/*
		 * complete the constructor so that the maze is a deep copy of the input
		 * parameter.
		 */
	}

	/**
	 * This accessor (getter) method returns a 2D array that represents the maze
	 * 
	 * @return it returns a reference to the maze
	 */
	public String[][] getMaze() {
		/*
		 * complete this method providing that a clone of the maze should be returned.
		 * you may want to change the return value to fit your purpose.
		 */
		return maze;
	}

	@Override
	public String toString() {
		// insert your code here. Change the return value to fit your purpose.
		int mazeLength = this.maze.length;
		String returnValue = "";
		for (int i = 0; i < mazeLength; i++) {
			if (i == mazeLength - 1)
				returnValue += Arrays.toString(maze[i]).replace(",", "");
			else
				returnValue += Arrays.toString(maze[i]).replace(",", "") + "\n";
		}
		return returnValue;
	}

}

// end of class Maze
