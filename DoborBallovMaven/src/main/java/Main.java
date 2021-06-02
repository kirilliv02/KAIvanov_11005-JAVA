public class Main {
    public static void main(String[] args) {
        CRUDHolder<Person> crudHolder = new CRUDHolder<>();
        crudHolder.create(new Person("Kirill", 19));
        crudHolder.create(new Person("Gleb", 19));
        crudHolder.update(new Person("Kirill",18));
        crudHolder.print();
    }
}
