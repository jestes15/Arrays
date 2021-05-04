package MatrixCalc;

import Functions.Functions;
import Functions.SBuildArray;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Locale;

public class MatrixSecondary extends MatrixMain {
    public void initializeArr(@NotNull String userInput) {
        if (userInput.toLowerCase(Locale.ROOT).equals("myself")) {
            for (int x = 0; x < getArr().length; x++) {
                for (int y = 0; y < getArr()[0].length; y++) {
                    System.out.printf("Please input your value for [%d][%d]", x, y);
                    getArr()[x][y] = getSc().nextInt();
                }
            }
        } else {
            int val;
            int randVal;
            for (int x = 0; x < getArr().length; x++) {
                for (int y = 0; y < getArr()[0].length; y++) {
                    val = getRand().nextInt(40);
                    randVal = getRand().nextInt(40);
                    getArr()[x][y] = val * randVal + randVal - val;
                }
            }
        }
    }

    private void copyArray() {
        int lengthOfArr = getArr().length;
        int widthOfArr = getArr()[0].length;
        int[][] tempArray = new int[lengthOfArr][widthOfArr];
        for (int x = 0; x < lengthOfArr; x++) {
            System.arraycopy(getArr()[x], 0, tempArray[x], 0, widthOfArr);
        }
        setTempArr(tempArray);
    }

    private int transpose(Functions aFunc, SBuildArray sBuild) {
        aFunc.setArr(getArr());
        NullPointerException e = aFunc.transpose();
        if (e != null) {
            System.out.printf("An error has occurred: %s\n", e.getMessage());
            return -1;
        }
        System.out.println();
        for (int[] ints : getArr()) System.out.println(Arrays.toString(ints));
        System.out.println();
        for (int[] ints : aFunc.getTempArr()) System.out.println(Arrays.toString(ints));

        if (isTransposed()) {
            setTransposed(true);
            setTransArr(aFunc.getTempArr());
            sBuild.append("\nThe transposed array:\n", getTransArr());
            sBuild.append("\n");
        }
        return 1;
    }

    private void sort(SBuildArray sBuild) {
        int length = getArr().length;
        int width = getArr()[0].length;
        int[][] tempArr = new int[length][width];
        for (int x = 0; x < length; x++) {
            System.arraycopy(getArr()[x], 0, tempArr[x], 0, width);
        }

        System.out.println("The original array is:");
        for (int[] ints : tempArr) System.out.println(Arrays.toString(ints));
        System.out.println();
        for (int[] ints : tempArr) {
            Functions.sort(ints, 0, ints.length - 1, true);
        }
        System.out.println("The new sorted array is: ");
        setSortArr(tempArr);
        for (int[] ints : getSortArr()) System.out.println(Arrays.toString(ints));

        sBuild.append("The sorted version of the array is:\n", getSortArr());
        sBuild.append("\n");
    }

    private int sortTranArray(Functions aFunc, SBuildArray sBuild) {
        aFunc.setArr(getArr());
        NullPointerException e =  aFunc.transpose();
        if (e != null) {
            System.out.printf("An error has occurred: %s", e.getMessage());
            return -1;
        }
        int[][] tempForSort = aFunc.getTempArr();

        if (isTransposed()) {
            sBuild.append("\nThe Transposed array:\n", aFunc.getTempArr());
            sBuild.append("\n");
            setTransposed(true);
        }
        else {
            transpose(aFunc, sBuild);
        }

        for (int[] ints : tempForSort) {
            Functions.sort(ints, 0, ints.length - 1, true);
        }
        setTransSortArr(tempForSort);
        System.out.println("The sorted array of the transposed version is:\n");
        for (int[] ints : getTransSortArr()) System.out.println(Arrays.toString(ints));

        sBuild.append("The sorted version of the transposed array is:\n", getTransSortArr());
        sBuild.append("\n");
        return 1;
    }

    public void executeOperation(String userInput, Functions aFunc, SBuildArray sBuild) {
        switch (userInput){
            case "print" -> { for (int[] ints : getArr()) System.out.println(Arrays.toString(ints)); }
            case "copy" -> copyArray();
            case "showCopy" -> { for (int[] ints : getTempArr()) System.out.println(Arrays.toString(ints)); }
            case "sortTran" -> {
                if (sortTranArray(aFunc, sBuild) == -1) System.out.println("The chosen operation could not be performed");
            }
            case "transpose" -> {
                if(transpose(aFunc, sBuild) == -1) System.out.println("The chosen operation could not be performed");
            }
            case "sort" -> sort(sBuild);
            case "removePunctuation" -> {
                getSc().nextLine();
                System.out.print("What string do you want put into an array?");
                String userIn = getSc().nextLine();
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
            default -> System.out.println("Not a valid operation");
        }
    }
}

