public class Person implements Comparable<Person>, Printable {

    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        return name.compareTo(o.name);
    }

    @Override
    public void print() {
        System.out.println(name + " " + age);
    }

}
