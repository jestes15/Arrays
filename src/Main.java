import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayFunctions aFunc = new ArrayFunctions();
        Random rand = new Random();
        int returnVal;
        boolean finishingVal = true;

        while (finishingVal) {
            System.out.println("How big do you want the first dimension of the array to be?");
            int z = sc.nextInt();
            System.out.println("How big do you want the second dimension of the array to be?");
            int b = sc.nextInt();
            boolean userInput;
            int[][] arr = new int[z][b];
            //Generates an array to be transposed
            System.out.println("Do you want to input the array yourself or see an example? (Myself/Default)");
            userInput = sc.next().equals("Myself");
            if (userInput) {
                for (int x = 0; x < z; x++) {
                    for (int y = 0; y < b; y++) {
                        System.out.printf("Please input your value for [%d][%d]", x, y);
                        arr[x][y] = sc.nextInt();
                    }
                }
            } else {
                int val;
                int randVal;
                for (int x = 0; x < z; x++) {
                    for (int y = 0; y < b; y++) {
                        val = rand.nextInt(40);
                        randVal = rand.nextInt(40);
                        arr[x][y] = val * randVal + randVal - val;
                    }
                }
            }
            boolean loop = true;
            while (loop) {
                System.out.print("What kind of matrix calculation do you want to do?\n:transpose: transposes the entire inputted array\n" +
                        ":determinate: Determinate of the input matrix\n" +
                        ":sort: Sort each partition of the array, but no the array in its entirety\n:::> ");
                String userInputOfMatrixCalculations = sc.next();

                switch (userInputOfMatrixCalculations) {
                    case "transpose":
                        aFunc.setArr(arr);
                        aFunc.transpose();
                        System.out.println();
                        for (int[] ints : arr) System.out.println(Arrays.toString(ints));
                        System.out.println();
                        for (int[] ints : aFunc.getTempArr()) System.out.println(Arrays.toString(ints));
                        System.out.println();
                        for (int[] ints : arr) System.out.println(Arrays.toString(ints));
                        break;

                    case "determinate":
                        if (aFunc.N == aFunc.W) {
                            System.out.println("\n====================================================");
                            System.out.println("\nCalculating the determinate of your matrix");
                            aFunc.setA(arr);
                            returnVal = aFunc.determinantOfMatrix(arr, aFunc.N);
                            System.out.printf("The determinate of the matrix is: %d", returnVal);
                        } else {
                            System.out.println("I'm sorry, but the matrix you used is not a square matrix, therefore " +
                                    "the determinate cannot be determined.");
                        }
                        break;

                    case "sort":
                        int length = arr.length;
                        int width = arr[0].length;
                        int[][] tempArr = new int[length][width];
                        for (int x = 0; x < length; x++) {
                            System.arraycopy(arr[x], 0, tempArr[x], 0, width);
                        }


                        System.out.println("The original array is:");
                        for (int[] ints : tempArr) System.out.println(Arrays.toString(ints));
                        System.out.println();
                        for (int[] ints : tempArr) {
                            ArrayFunctions.sort(ints, 0, ints.length - 1, true);
                        }
                        System.out.println("The new sorted array is: ");
                        for (int[] ints : tempArr) System.out.println(Arrays.toString(ints));
                        System.out.println();
                        for (int[] ints : arr) System.out.println(Arrays.toString(ints));
                        break;
                }
                System.out.print("Would you like to do another calculation on the same matrix?\n:::> ");
                String userInputInLoop = sc.next();
                switch (userInputInLoop) {
                    case "yes":
                        break;
                    case "no":
                        loop = false;
                        break;
                }
            }
            System.out.print("Would you like to do any calculations on another matrix?\n:::> ");
            String finalUserInput = sc.next();
            switch (finalUserInput) {
                case "no":
                    finishingVal = false;
                    break;
                case "yes":
                    break;
            }
        }
    }
}