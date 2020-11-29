import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        ArrayFunctions aFunc = new ArrayFunctions();
        classBufferedWriter BuffWrite = new classBufferedWriter();
        Random rand = new Random();
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
            StringBuilder SBuild = new StringBuilder();
            LocalDateTime time = LocalDateTime.now();
            int currentTimeMIN = time.getMinute();
            int currentTimeHOUR = time.getHour();
            int currentTimeDAY = time.getDayOfMonth();
            int currentTimeMONTH = time.getMonthValue();
            int currentTimeYEAR = time.getYear();
            SBuild.append("Array for attempt at ").append(currentTimeHOUR).append(":").append(currentTimeMIN).
                    append(" on ").append(currentTimeMONTH).append("/").append(currentTimeDAY).append("/").
                    append(currentTimeYEAR).append("\n").append("======================================================\n");
            SBuild.append("The original array:\n");
            for (int[] ints : arr) SBuild.append(Arrays.toString(ints)).append("\n");

            boolean loop = true;
            boolean transposed = false;
            while (loop) {
                System.out.print("What kind of matrix calculation do you want to do?\n:transpose: transposes the entire inputted array\n" +
                        ":sort: Sort each partition of the array, but no the array in its entirety\n" +
                        ":print: Prints the array\n" +
                        ":copy: Copies the array to a temp array to be used to be printed.\n" +
                        ":sortTran: Sort the transposed version of this array\n" +
                        ":SBuild: Show current information in the StringBuilder to be written to the file at the end of the operation\n:::> ");

                String userInputOfMatrixCalculations = sc.next();

                switch (userInputOfMatrixCalculations) {

                    case "SBuild":
                        System.out.println(SBuild.toString());
                        break;

                    case "print":
                        for (int[] ints : arr) System.out.println(Arrays.toString(ints));
                        break;

                    case "copy":
                        int lengthOfArr = arr.length;
                        int widthOfArr = arr[0].length;
                        int[][] tempArray = new int[lengthOfArr][widthOfArr];
                        for (int x = 0; x < lengthOfArr; x++) {
                            System.arraycopy(arr[x], 0, tempArray[x], 0, widthOfArr);
                        }
                        System.out.println("Operation was successful");
                        break;

                    case "sortTran":
                        aFunc.setArr(arr);
                        aFunc.transpose();
                        int[][] tempForSort = aFunc.getTempArr();
                        if (!transposed) {
                            SBuild.append("\nThe Transposed array:\n");
                            for (int[] ints : aFunc.getTempArr()) SBuild.append(Arrays.toString(ints)).append("\n");
                            SBuild.append("\n");
                            transposed = true;

                        }
                        for (int[] ints : tempForSort) {
                            ArrayFunctions.sort(ints, 0, ints.length - 1, true);
                        }
                        System.out.println("The sorted array of the transposed version is:\n");
                        for (int[] ints : tempForSort) System.out.println(Arrays.toString(ints));

                        SBuild.append("The sorted version of the transposed array is:\n");
                        for (int[] ints : tempForSort) SBuild.append(Arrays.toString(ints)).append("\n");
                        SBuild.append("\n");
                        break;

                    case "transpose":
                        aFunc.setArr(arr);
                        aFunc.transpose();
                        System.out.println();
                        for (int[] ints : arr) System.out.println(Arrays.toString(ints));
                        System.out.println();
                        for (int[] ints : aFunc.getTempArr()) System.out.println(Arrays.toString(ints));
                        if (!transposed) {
                            transposed = true;
                            SBuild.append("\nThe Transposed array:\n");
                            for (int[] ints : aFunc.getTempArr()) SBuild.append(Arrays.toString(ints)).append("\n");
                            SBuild.append("\n");
                        }

                        break;

                    case "sort":
                        int length = arr.length;
                        int width = arr[0].length;
                        int[][] tempArr = new int[length][width];
                        for (int x = 0; x < length; x++) {
                            System.arraycopy(arr[x], 0, tempArr[x], 0, width);
                        }
                        SBuild.append("The sorted version of the array is:\n");

                        System.out.println("The original array is:");
                        for (int[] ints : tempArr) System.out.println(Arrays.toString(ints));
                        System.out.println();
                        for (int[] ints : tempArr) {
                            ArrayFunctions.sort(ints, 0, ints.length - 1, true);
                        }
                        System.out.println("The new sorted array is: ");
                        for (int[] ints : tempArr) System.out.println(Arrays.toString(ints));
                        for (int[] ints : tempArr) SBuild.append(Arrays.toString(ints)).append("\n");
                        SBuild.append("\n");
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
            SBuild.append("End of this array operation\n");
            BuffWrite.writeBufferedWriter(SBuild.toString(), "\\ArrayListDir\\", "ArrayList");
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