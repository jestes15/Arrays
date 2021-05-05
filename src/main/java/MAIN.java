import java.io.IOException;
import java.util.Scanner;

import BuffWriter.BuffWriter;
import OsInfo.OsUtils;
import Functions.*;
import MatrixCalc.MatrixSecondary;

public class MAIN {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Functions aFunc = new Functions();
        SBuildArray sBuild = new SBuildArray();
        boolean finishingVal = true;

        OsUtils.writeToFile();

        String returnVal = BuffWriter.createFile("\\ArrayListDir\\", "ArrayList");
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
            String[] userChoice = {sc.next(), ""};
            switch (userChoice[0]) {
                case "Matrix" -> {
                    MatrixSecondary matrixSecondary = new MatrixSecondary();
                    System.out.println("How big do you want the first dimension of the array to be?");
                    int z = sc.nextInt();
                    System.out.println("How big do you want the second dimension of the array to be?");
                    int b = sc.nextInt();
                    matrixSecondary.setArr(new int[z][b]);

                    System.out.println("Do you want to input the array yourself or see an example? (myself/default)");
                    String userInput = sc.next();
                    matrixSecondary.initializeArr(userInput);

                    sBuild.append("The original array:\n", matrixSecondary.getArr());

                    boolean loop = true;
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
                        :exit:              Exits the program after writing anything in cache to the files\s
                        :::> \s""");

                        String userInputOfMatrixCalculations = sc.next();

                        switch (userInputOfMatrixCalculations) {
                            case "SBuild" -> System.out.println(sBuild.getMsg());
                            case "exit" -> loop = false;
                            default -> matrixSecondary.executeOperation(userInputOfMatrixCalculations, aFunc, sBuild);
                        }
                        userChoice[1] = userInputOfMatrixCalculations;
                        if (!userInputOfMatrixCalculations.equals("exit")) {
                            System.out.print("Would you like to do another calculation on the same matrix?\n:::> ");
                            String userInputInLoop = sc.next();
                            switch (userInputInLoop) {
                                case "yes" -> { }
                                case "no" -> loop = false;
                            }
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
            sBuild.set_dir("\\ArrayListDir\\");
            sBuild.set_fileName("ArrayList");
            sBuild.writeToFile();

            String finalUserInput;
            if (userChoice[1].equals("exit")) {
                finalUserInput = "no";
            }
            else {
                System.out.print("Would you like to do any other calculations?\n:::> ");
                finalUserInput = sc.next();
            }
            switch (finalUserInput) {
                case "no" -> finishingVal = false;
                case "yes" -> System.out.println("Continuing!");
                default -> System.out.println("That is not a valid option, please try again!");
            }
        }
    }
}