package Task1;

public class RandomPerson {
    static Person random(){
        String[] names = {"Kirill", "Dima", "Gleb"};

        String name = names[(int) (Math.random()* names.length)];
        int age = (int) (Math.random()*100);
        Countries country = Countries.values()[(int) (Math.random()*Countries.values().length)];
        return new Person(name, age, country);
    }
}
