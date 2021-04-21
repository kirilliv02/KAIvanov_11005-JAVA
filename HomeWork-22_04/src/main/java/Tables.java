public class Tables {
    int count;
    String nameOfBooker;

    public Tables(int count, String nameOfBooker) {
        this.count = count;
        this.nameOfBooker = nameOfBooker;
    }

    @Override
    public String toString() {
        return "count of person to table=" + count +
                ", nameOfBooker='" + nameOfBooker + '\'' +
                '}';
    }
}
