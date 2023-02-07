package PE1;

import java.util.Arrays;

public class PE1Test {
	public static void main(String[] args) {
//		String[][] maze={{"1110","1010","1010","1000","1010","1011"},
//				{"1010","1000","1001","0101","1100","1001"},
//				{"1100","0011","0101","0110","0011","0101"},
//				{"0101","1101","0110","1001","1110","0000"},
//				{"0110","0011","1110","0010","1010","0011"}};
//		Maze  m = new Maze(maze);
//		
//		PE1 a1=new PE1();
//		a1.setup(maze);
//		System.out.println(a1.dogMaze);
//		String[][] newMaze=a1.mazeExtend(a1.dogMaze.getMaze());
//		boolean t=a1.pathToExit(newMaze, 2, 1, 4, 6);
//		for(int i =0;i<newMaze.length;i++) {
//			System.out.println(Arrays.toString(newMaze[i]));
//		}
//		System.out.println("(1,0)"+a1.pathPrint(newMaze, 2, 1, 4, 6));
//		
//		
		String[][] maze2 = { { "1000", "1000", "1001" }, { "0100", "0000", "0001" }, { "0110", "0010", "0001" } };
		;
		Maze m2 = new Maze(maze2);

		PE1 a2 = new PE1();
		a2.setup(maze2);
		System.out.println(a2.dogMaze);
		String[][] newMaze2 = a2.mazeExtend(a2.dogMaze.getMaze());
		boolean t2 = a2.pathToExit(newMaze2, 1, 1, 3, 3);
		for (int i = 0; i < newMaze2.length; i++) {
			System.out.println(Arrays.toString(newMaze2[i]));
		}
		System.out.println(a2.findPath(0, 0));

	}
}
