import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import OsInfo.OsUtils;
import Functions.*;

public class MAIN {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Functions aFunc = new Functions();
        SBuildArray sBuild = new SBuildArray();
        classBufferedWriter BuffWrite = new classBufferedWriter();
        CreateFile newFile = new CreateFile();
        Random rand = new Random();
        boolean finishingVal = true;

        System.out.println(OsUtils.startOsInfo());
        System.out.println(System.getProperty("os.name") + " " + System.getProperty("os.arch"));

        newFile.setGetDir("\\ArrayListDir\\");
        newFile.setGetName("ArrayList");
        String returnVal = newFile.createFile();
        switch (returnVal) {
            case "File Created" -> System.out.println("The file has been created and is ready for use");
            case "File Exists" -> System.out.println("File ready");
            case "An error has occurred" -> System.out.println("An error has occurred");
            default -> System.out.println("An unhandled choice has been passed");
        }

        sBuild.initialize();

        while (finishingVal) {
            System.out.print("""
                    What kind of function do you want to do?
                    :Matrix: Any form of matrix calculation this program currently supports
                    :Primes: Can find a prime number from 0 to a user inputted number
                    :::> \s""");
            String userChoice = sc.next();
            switch (userChoice) {
                case "Matrix" -> {
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

                    sBuild.append("The original array:\n", arr);

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
                        :removePunctuation:  Removes any punctuation from the string\s
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
                                NullPointerException e =  aFunc.transpose();
                                if (e == null) {
                                    System.out.printf("AN error has occurred: %s", (Object) null);
                                    break;
                                }
                                int[][] tempForSort = aFunc.getTempArr();

                                if (!transposed) {
                                    sBuild.append("\nThe Transposed array:\n", aFunc.getTempArr());
                                    sBuild.append("\n");
                                    transposed = true;
                                }

                                for (int[] ints : tempForSort) {
                                    Functions.sort(ints, 0, ints.length - 1, true);
                                }
                                System.out.println("The sorted array of the transposed version is:\n");
                                for (int[] ints : tempForSort) System.out.println(Arrays.toString(ints));

                                sBuild.append("The sorted version of the transposed array is:\n", tempForSort);
                                sBuild.append("\n");
                            }

                            case "transpose" -> {
                                aFunc.setArr(arr);
                                NullPointerException e = aFunc.transpose();
                                if (e == null) {
                                    System.out.printf("An error has occurred: %s\n", (Object) null);
                                    break;
                                }
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
                                    Functions.sort(ints, 0, ints.length - 1, true);
                                }
                                System.out.println("The new sorted array is: ");
                                for (int[] ints : tempArr) System.out.println(Arrays.toString(ints));

                                sBuild.append("The sorted version of the array is:\n", tempArr);
                                sBuild.append("\n");
                            }
                            case "removePunctuation" -> {
                                sc.nextLine();
                                System.out.print("What string do you want put into an array?");
                                String userIn = sc.nextLine();
                                sBuild.setMsgWithPun(userIn);
                                userIn = sBuild.replacePunctuation(userIn);
                                sBuild.setMsg(userIn);

                                String[] charArr = sBuild.splitMsg();
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
                                System.out.printf("With Punctuation: %s\nWithout: %s", sBuild.getMsgWithPun(), sBuild.getMsg());
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
                }
                case "Primes" -> {
                    System.out.print("How far do you want me to find prime numbers?\n:::> ");
                    int n = sc.nextInt();
                    boolean[] array = new boolean[n];
                    for (int x = 0; x < n; x++)
                        array[x] = true;
                    aFunc.setTempBoolArr(array);
                    aFunc.SieveOfEratosthenes(n);

                    int numOfPrimes = 0;
                    sBuild.append("The prime numbers that was printed in the operation:\n");

                    boolean[] boolArray = aFunc.getTempBoolArr();


                    for (int place = 0; place < n; place++) {
                        if (boolArray[place]) {
                            System.out.printf("%d, ", place);
                            sBuild.append(place + ", ");
                            numOfPrimes++;

                            if (numOfPrimes % 10 == 0) {
                                System.out.print("\n");
                                sBuild.append("\n");
                            }
                        }
                    }
                    System.out.println();
                    sBuild.append("\n");
                }
            }
            sBuild.append("End of this operation\n");
            BuffWrite.writeBufferedWriter(sBuild.getMsg(), "\\ArrayListDir\\", "ArrayList");

            System.out.print("Would you like to do any other calculations?\n:::> ");
            String finalUserInput = sc.next();
            switch (finalUserInput) {
                case "no" -> finishingVal = false;
                case "yes" -> { }
            }
        }
    }
}