package MatrixCalc;

import java.util.Random;
import java.util.Scanner;

public class MatrixMain {
    private int[][] arr;
    private int[][] sortArr;
    private int[][] transArr;
    private int[][] transSortArr;
    private int[][] tempArr;
    private final Scanner sc = new Scanner(System.in);
    private final Random rand = new Random();
    private boolean transposed = false;

    public Scanner getSc() {
        return sc;
    }

    public Random getRand() {
        return rand;
    }

    public int[][] getArr() {
        return arr;
    }

    public int[][] getSortArr() {
        return sortArr;
    }

    public int[][] getTransArr() {
        return transArr;
    }

    public int[][] getTransSortArr() {
        return transSortArr;
    }

    public int[][] getTempArr() {
        return tempArr;
    }

    public boolean isTransposed() {
        return !transposed;
    }

    public void setArr(int[][] arr) {
        this.arr = arr;
    }

    public void setSortArr(int[][] sortArr) {
        this.sortArr = sortArr;
    }

    public void setTransArr(int[][] transArr) {
        this.transArr = transArr;
    }

    public void setTransSortArr(int[][] transSortArr) {
        this.transSortArr = transSortArr;
    }

    public void setTempArr(int[][] tempArr) {
        this.tempArr = tempArr;
    }

    public void setTransposed(boolean transposed) {
        this.transposed = transposed;
    }
}