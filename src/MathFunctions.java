public class MathFunctions {
    private int sum = 1;

    public int SumFact(int x) {

        for (int i = 1; i < x; i++) {
            sum = sum*i;
        }
        return sum;
    }
}