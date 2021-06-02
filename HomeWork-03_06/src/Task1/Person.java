package Task1;

import java.io.Serializable;

public class Person implements Serializable {
    String name;
    int age;
    Countries country;

    public Person(String name, int age, Countries country) {
        this.name = name;
        this.age = age;
        this.country = country;
    }

    @Override
    public String toString() {
        return name + " " + age + " " + country;
    }
}
