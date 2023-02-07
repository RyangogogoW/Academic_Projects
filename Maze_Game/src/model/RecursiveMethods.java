package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class RecursiveMethods {

	public static String task1(String inputString) {

		if (!inputString.startsWith("(")) {
			return task1(inputString.substring(1));
		}
		if (!inputString.endsWith(")"))
			return task1(inputString.substring(0, inputString.length() - 1));
		else
			return inputString;

	}

	public static boolean task2(int[] inputArray, int target) {
		if (target == 0)
			return true;
		if (inputArray.length == 1)
			return (target - inputArray[0] == 0);

		if (task2(Arrays.copyOfRange(inputArray, 1, inputArray.length), target - inputArray[0]))
			return true;
		if (task2(Arrays.copyOfRange(inputArray, 1, inputArray.length), target))
			return true;
		return false;

	}

	public static int task3(int stairs, int maxStep) {
		int sumSteps = 0;
		if (stairs == 0)
			return 1;
		if (stairs >= maxStep) {
			for (int i = 1; i <= maxStep; i++) {
				sumSteps = sumSteps + task3(stairs - i, maxStep);
			}
		} else
			sumSteps = task3(stairs, stairs);
		return sumSteps;
	}

	public static HashSet<ArrayList<Integer>> task4(int stairs, int maxStep) {
		HashSet<ArrayList<Integer>> result = new HashSet<ArrayList<Integer>>();
		ArrayList<Integer> path = new ArrayList<Integer>();

		task4HelperMethod(result, path, stairs, maxStep);

		return result;
	}

	// This is a helper method of task 4;
	public static void task4HelperMethod(HashSet<ArrayList<Integer>> result, ArrayList<Integer> path, int stairs,
			int maxStep) {
		if (stairs == 0) {
			result.add(new ArrayList<>(path));
			return;
		}
		for (int i = 1; i <= maxStep; i++) {
			if (stairs >= i) {
				path.add(i);
				task4HelperMethod(result, path, stairs - i, maxStep);
				path.remove(path.size() - 1);

			}
		}
		return;
	}
}
