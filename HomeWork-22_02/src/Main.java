public class Main {
    public static void main(String[] args) {
        Range range = new Range(5);
        RangeInterval rangeInterval = new RangeInterval(5 , 10);
        FibonacciRange fibonacciRange = new FibonacciRange(17);


        while (rangeInterval.iterator().hasNext()) {
            System.out.print(rangeInterval.iterator().next() + " ");
        }

        System.out.println();

        while (range.iterator().hasNext()) {
            System.out.print(range.iterator().next() + " ");
        }

        System.out.println();

        while (fibonacciRange.iterator().hasNext()) {
            System.out.print(fibonacciRange.iterator().next() + " ");
        }
    }
}
