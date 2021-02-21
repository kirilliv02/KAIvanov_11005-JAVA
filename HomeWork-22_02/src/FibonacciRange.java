import java.util.Iterator;

public class FibonacciRange extends Range{

    private int pre;
    private int prePre;
    private int now;

    public FibonacciRange(int length) {
        super(length);
        prePre = 0;
        pre = 1;
        now = 0;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return counter < length;
            }

            @Override
            public Integer next() {
                if (counter == 0) {
                    counter++;
                    return 1;
                } else {
                    counter++;
                    now = prePre + pre;
                    prePre = pre;
                    pre = now;
                    return now;
                }

            }
        };
    }
}
