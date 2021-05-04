//Declares Functions.java to be in the Functions package
package Functions;

//
public class Functions {
    private static final int INSERTION_SORT_THRESHOLD = 47;

    boolean[] tempBoolArr;

    private int[][] arr;
    private int[][] tempArr;
    private int lengthOfArray;
    private int widthOfArray;

    public void setArr(int[][] array) {
        this.arr = array;
        this.widthOfArray = array[0].length;
        this.lengthOfArray = array.length;
    }

    public void setTempArr(int[][] tempArr) {
        this.tempArr = tempArr;
    }
    public int[][] getTempArr() {
        return tempArr;
    }

    public void setTempBoolArr(boolean[] tempBoolArr) {
        this.tempBoolArr = tempBoolArr;
    }
    public boolean[] getTempBoolArr() {
        return tempBoolArr;
    }

    /**
     * Transposes the array from a private int array
     * @return**/
    public NullPointerException transpose() throws NullPointerException {
        int[][] tempArr = new int[widthOfArray][lengthOfArray];

        for (int x = 0; x < lengthOfArray; x++) {
            for (int y = 0; y < widthOfArray; y++) {
                try {
                    tempArr[y][x] = arr[x][y];
                } catch (NullPointerException e) {
                    return e;
                }
            }
        }
        setTempArr(tempArr);
        return null;
    }

    public void SieveOfEratosthenes(int n) {
        int intOne, intTwo;

        tempBoolArr[0] = false;
        tempBoolArr[1] = false;

        for (intOne = 2; intOne < n; intOne++) {
            tempBoolArr[intOne] = true;
        }

        for (intOne = 2; intOne < Math.sqrt(n); intOne++) {
            for (intTwo = intOne * intOne; intTwo < n; intTwo = intTwo + intOne) {
                tempBoolArr[intTwo] = false;
            }
        }
    }

    public static void sort(int[] a, int left, int right, boolean leftmost) {
        int length = right - left + 1;
        if (length < INSERTION_SORT_THRESHOLD) {
            if (leftmost) {
                for (int i = left, j = i; i < right; j = ++i) {
                    int ai = a[i + 1];
                    while (ai < a[j]) {
                        a[j + 1] = a[j];
                        if (j-- == left) {
                            break;
                        }
                    }
                    a[j + 1] = ai;
                }
            } else {
                do {
                    if (left >= right) {
                        return;
                    }
                } while (a[++left] >= a[left - 1]);

                for (int k = left; ++left <= right; k = ++left) {
                    int a1 = a[k], a2 = a[left];

                    if (a1 < a2) {
                        a2 = a1; a1 = a[left];
                    }
                    while (a1 < a[--k]) {
                        a[k + 2] = a[k];
                    }
                    a[++k + 1] = a1;

                    while (a2 < a[--k]) {
                        a[k + 1] = a[k];
                    }
                    a[k + 1] = a2;
                }
                int last = a[right];

                while (last < a[--right]) {
                    a[right + 1] = a[right];
                }
                a[right + 1] = last;
            }
            return;
        }
        int seventh = (length >> 3) + (length >> 6) + 1;
        int e3 = (left + right) >>> 1;
        int e2 = e3 - seventh;
        int e1 = e2 - seventh;
        int e4 = e3 + seventh;
        int e5 = e4 + seventh;
        if (a[e2] < a[e1]) { int t = a[e2]; a[e2] = a[e1]; a[e1] = t; }

        if (a[e3] < a[e2]) { int t = a[e3]; a[e3] = a[e2]; a[e2] = t;
            if (t < a[e1]) { a[e2] = a[e1]; a[e1] = t; }
        }
        if (a[e4] < a[e3]) { int t = a[e4]; a[e4] = a[e3]; a[e3] = t;
            if (t < a[e2]) { a[e3] = a[e2]; a[e2] = t;
                if (t < a[e1]) { a[e2] = a[e1]; a[e1] = t; }
            }
        }
        if (a[e5] < a[e4]) { int t = a[e5]; a[e5] = a[e4]; a[e4] = t;
            if (t < a[e3]) { a[e4] = a[e3]; a[e3] = t;
                if (t < a[e2]) { a[e3] = a[e2]; a[e2] = t;
                    if (t < a[e1]) { a[e2] = a[e1]; a[e1] = t; }
                }
            }
        }
        int less  = left;
        int great = right;

        if (a[e1] != a[e2] && a[e2] != a[e3] && a[e3] != a[e4] && a[e4] != a[e5]) {
            int pivot1 = a[e2];
            int pivot2 = a[e4];
            a[e2] = a[left];
            a[e4] = a[right];
            outer:
            for (int k = less - 1; ++k <= great; ) {
                int ak = a[k];
                if (ak < pivot1) {
                    a[k] = a[less];
                    a[less] = ak;
                    ++less;
                } else if (ak > pivot2) {
                    while (a[great] > pivot2) {
                        if (great-- == k) {
                            break outer;
                        }
                    }
                    if (a[great] < pivot1) {
                        a[k] = a[less];
                        a[less] = a[great];
                        ++less;
                    } else {
                        a[k] = a[great];
                    }
                    a[great] = ak;
                    --great;
                }
            }
            a[left]  = a[less  - 1]; a[less  - 1] = pivot1;
            a[right] = a[great + 1]; a[great + 1] = pivot2;
            sort(a, left, less - 2, leftmost);
            sort(a, great + 2, right, false);
            if (less < e1 && e5 < great) {
                while (a[less] == pivot1) {
                    ++less;
                }

                while (a[great] == pivot2) {
                    --great;
                }
                outer:
                for (int k = less - 1; ++k <= great; ) {
                    int ak = a[k];
                    if (ak == pivot1) {
                        a[k] = a[less];
                        a[less] = ak;
                        ++less;
                    } else if (ak == pivot2) {
                        while (a[great] == pivot2) {
                            if (great-- == k) {
                                break outer;
                            }
                        }
                        if (a[great] == pivot1) {
                            a[k] = a[less];
                            a[less] = pivot1;
                            ++less;
                        } else {
                            a[k] = a[great];
                        }
                        a[great] = ak;
                        --great;
                    }
                }
            }
            sort(a, less, great, false);

        } else {
            int pivot = a[e3];
            for (int k = less; k <= great; ++k) {
                if (a[k] == pivot) {
                    continue;
                }
                int ak = a[k];
                if (ak < pivot) {
                    a[k] = a[less];
                    a[less] = ak;
                    ++less;
                } else {
                    while (a[great] > pivot) {
                        --great;
                    }
                    if (a[great] < pivot) {
                        a[k] = a[less];
                        a[less] = a[great];
                        ++less;
                    } else {
                        a[k] = pivot;
                    }
                    a[great] = ak;
                    --great;
                }
            }
            sort(a, left, less - 1, leftmost);
            sort(a, great + 1, right, false);
        }
    }
}