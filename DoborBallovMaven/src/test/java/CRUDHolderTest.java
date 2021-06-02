import org.junit.Before;
import org.junit.Test;

public class CRUDHolderTest {

    CRUDHolder<Person> crudHolder;
    Person person, person1;

    @Before
    public void prepare(){
        crudHolder = new CRUDHolder<>();
        person = new Person("Kirill",19);
        person1 = new Person("Dima",20);
    }

    @Test
    public void createTestSuccess(){
        crudHolder.create(person);
        crudHolder.create(person1);
    }

    @Test
    public void createTestFailed(){
        crudHolder.create(person);
        crudHolder.create(person);
    }

    @Test
    public void readTestSuccessful() {
        crudHolder.create(person);
        crudHolder.read(0).print();
    }

    @Test
    public void readTestFailed() {
        crudHolder.create(person);
        crudHolder.read(1).print();
    }

    @Test
    public void updateSuccessful() {
        crudHolder.create(person);
        crudHolder.update(person);
    }

    @Test
    public void updateFailed() {
        crudHolder.create(person);
        crudHolder.update(person1);
    }

    @Test
    public void deleteSuccessful() {
        crudHolder.create(person);
        crudHolder.delete(person);
    }

    @Test
    public void deleteFailed() {
        crudHolder.create(person);
        crudHolder.delete(person1);
    }

    @Test
    public void print() {
        crudHolder.print();
    }

}
