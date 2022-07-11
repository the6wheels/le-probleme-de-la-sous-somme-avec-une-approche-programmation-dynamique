package algoTP1;

//TP5 Algorithmique et Complexite 2021-2022

//Nom:HADJAZI
//Prenom: Mohammed Hisham
//Specialite:   RSSI      Groupe: 01

//Nom:Ameur
//Prenom: Wassim Malik
//Specialite:   RSSI      Groupe: 01

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Somme_DP {
	static long cpt = 0;

	private static Boolean[][] mapSubs;

	// Returns true if there is a subset
	// of set[] with sum equal to given sum
	private static boolean findSubsets(int[] values, Vector<Integer> subarray, int target, int n, int target2) {
		cpt++;
		// Base Case 1
		if (target == 0) {
			System.out.println("The Sum of subset " + Arrays.toString(subarray.toArray()) + " = " + target2);
			return true;
		}
		// Base Case 2
		if (n == 0 && target > 0) {
			return false;
		}
		// If already calculated just
		// return the result
		if (mapSubs[target][n] != null) {
			return mapSubs[target][n];
		}
		// If last element is greater than
		// sum, then ignore it
		if (values[n - 1] > target) {
			mapSubs[target][n] = findSubsets(values, subarray, target, n - 1, target2);
			/*
			 * else, check if sum can be obtained by any of the following (a) including the
			 * last element (b) excluding the last element
			 */
		} else {
			mapSubs[target][n] = findSubsets(values, subarray, target, n - 1, target2);
			Vector<Integer> subarray2 = new Vector<Integer>(subarray);
			subarray2.add(values[n - 1]);
			mapSubs[target][n] = findSubsets(values, subarray2, target - values[n - 1], n - 1, target2);
		}
		return mapSubs[target][n];
	}

	// Starter function that fills our matrix with null
	private static boolean fillFunc(int[] values, Vector<Integer> subarray, int target, int n, int target2) {
		mapSubs = new Boolean[target + 1][n + 1];
		for (int s = 0; s <= target; s++) {
			for (int i = 0; i <= n; i++) {
				mapSubs[s][i] = null;
			}
		}
		return findSubsets(values, subarray, target, n, target2);
	}

	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);

		System.out.println("Enter the size of the Array n =\n");
		int n = keyboard.nextInt();
		int[] values = new int[n];
		Vector<Integer> v = new Vector<Integer>();

		System.out.println("Select Filling Method:\n(1) : For manual filling\n(2) : For automatic filling\n");
		int a = keyboard.nextInt();

		switch (a) {
		case 1:
			System.out.println("\nManual Filling Activated");
			for (int i = 0; i < n; i++) {
				System.out.println("Value " + (i + 1) + " =");
				values[i] = keyboard.nextInt();
			}
			break;
		case 2:
			System.out.println("\nAutomatic filling Activated");
			Random random = new Random();
			for (int i = 0; i < n; i++) {
				values[i] = random.nextInt(30 + 10);
			}
			break;
		}

		System.out.println("The target you are looking for =");
		int target = keyboard.nextInt();
		int target2 = target;
		keyboard.close();

		System.out.println("\n\n .................................... \n\n");
		System.out.println("The Array is : " + Arrays.toString(values) + "\n");
		System.out.println("The Target Sum is : " + target + "\n");
		fillFunc(values, v, target, n, target2);
		System.out.println("\nThe Matrix is as follows :\n\n" + Arrays.deepToString(mapSubs).replace("], ", "]\n"));
		System.out.println("\nThe number of recursive calls are : " + cpt + " Times\n");
	}
}
