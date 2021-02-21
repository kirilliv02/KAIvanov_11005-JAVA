public class FourTuple<A, B, C, D> extends ThreeTuple<A, B, C> {

    public D fours;

    public FourTuple(A first, B second, C third, D fours) {
        super(first, second, third);
        this.fours = fours;
    }
}