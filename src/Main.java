import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        ArrayFunctions aFunc = new ArrayFunctions();
        SBuildArray sBuild = new SBuildArray();
        classBufferedWriter BuffWrite = new classBufferedWriter();
        CreateFile newFile = new CreateFile();
        Random rand = new Random();
        SplitString SS = new SplitString();
        boolean finishingVal = true;

        newFile.setGetDir("\\ArrayListDir\\");
        newFile.setGetName("ArrayList");
        String returnVal = newFile.createFile();
        switch (returnVal) {
            case "File Created" -> System.out.println("The file has been created and is ready for use");
            case "File Exists" -> System.out.println("File ready");
            case "An error has occurred" -> System.out.println("An error has occurred");
            default -> System.out.println("An unhandled choice has been passed");
        }

        while (finishingVal) {
            System.out.println("How big do you want the first dimension of the array to be?");
            int z = sc.nextInt();
            System.out.println("How big do you want the second dimension of the array to be?");
            int b = sc.nextInt();

            boolean userInput;
            int[][] arr = new int[z][b];

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

            sBuild.initialize(arr);

            boolean loop = true;
            boolean transposed = false;
            while (loop) {
                System.out.print("""
                        What kind of matrix calculation do you want to do?\s
                        :transpose:     transposes the entire inputted array\s
                        :sort:          Sort each partition of the array, but no the array in its entirety\s
                        :print:         Prints the array\s
                        :copy:          Copies the array to a temp array to be used to be printed\s
                        :sortTran:      Sort the transposed version of this arrays\s
                        :SBuild:        Show current information in the StringBuilder to be written to the file at the end of the operation\s
                        :::> \s""");

                String userInputOfMatrixCalculations = sc.next();

                switch (userInputOfMatrixCalculations) {

                    case "SBuild" -> System.out.println(sBuild.getMsg());

                    case "print" -> { for (int[] ints : arr) System.out.println(Arrays.toString(ints)); }

                    case "copy" -> {
                        int lengthOfArr = arr.length;
                        int widthOfArr = arr[0].length;
                        int[][] tempArray = new int[lengthOfArr][widthOfArr];
                        for (int x = 0; x < lengthOfArr; x++) {
                            System.arraycopy(arr[x], 0, tempArray[x], 0, widthOfArr);
                        }
                        System.out.println("Operation was successful");
                }

                    case "sortTran" -> {
                        aFunc.setArr(arr);
                        aFunc.transpose();
                        int[][] tempForSort = aFunc.getTempArr();

                        if (!transposed) {
                            sBuild.append("\nThe Transposed array:\n", aFunc.getTempArr());
                            sBuild.append("\n");
                            transposed = true;
                        }

                        for (int[] ints : tempForSort) {
                            ArrayFunctions.sort(ints, 0, ints.length - 1, true);
                        }
                        System.out.println("The sorted array of the transposed version is:\n");
                        for (int[] ints : tempForSort) System.out.println(Arrays.toString(ints));

                        sBuild.append("The sorted version of the transposed array is:\n", tempForSort);
                        sBuild.append("\n");
                    }

                    case "transpose" -> {
                        aFunc.setArr(arr);
                        aFunc.transpose();
                        System.out.println();
                        for (int[] ints : arr) System.out.println(Arrays.toString(ints));
                        System.out.println();
                        for (int[] ints : aFunc.getTempArr()) System.out.println(Arrays.toString(ints));

                        if (!transposed) {
                            transposed = true;
                            sBuild.append("\nThe transposed array:\n", aFunc.getTempArr());
                            sBuild.append("\n");
                        }
                    }

                    case "sort" -> {
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

                        sBuild.append("The sorted version of the array is:\n", tempArr);
                        sBuild.append("\n");
                    }
                    case "testing" -> {
                        sc.nextLine();
                        System.out.print("""
                                What string do you want put into an array?\s
                                msr> \r
                                """);
                        String userIn = sc.nextLine();
                        SS.setMsgWithPun(userIn);
                        userIn = SS.replacePunctuation(userIn);
                        SS.setMsg(userIn);

                        String[] charArr = SS.splitMsg();
                        String errorThrown = null;

                        for (String error : charArr) {
                            switch (error) {
                                case "test" -> errorThrown = "no";
                                case "testing" -> errorThrown = "yes";
                                default -> errorThrown = "unknown";
                            }
                            if (errorThrown.equals("no") || errorThrown.equals("yes")) {
                                break;
                            }
                        }
                        System.out.println(Arrays.toString(charArr));
                        System.out.println(errorThrown);
                        System.out.printf("With Punctuation: %s\nWithout: %s", SS.getMsgWithPun(), SS.getMsg());
                        System.out.println();
                    }
                }
                System.out.print("Would you like to do another calculation on the same matrix?\n:::> ");
                String userInputInLoop = sc.next();
                switch (userInputInLoop) {
                    case "yes" -> { }
                    case "no" -> loop = false;
                }
            }
            sBuild.append("End of this array operation\n");
            BuffWrite.writeBufferedWriter(sBuild.getMsg(), "\\ArrayListDir\\", "ArrayList");

            System.out.print("Would you like to do any calculations on another matrix?\n:::> ");
            String finalUserInput = sc.next();
            switch (finalUserInput) {
                case "no" -> finishingVal = false;
                case "yes" -> { }
            }
        }
    }
}