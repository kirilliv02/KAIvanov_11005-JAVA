import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

public class CRUDHolderTest {

    CRUDHolder<Person> crudHolder;

    @Before
    public void prepare() {
        crudHolder = new CRUDHolder<>();
        crudHolder.create(new Person("Kirill", 19));
        crudHolder.create(new Person("Dima", 20));
    }

    @Test
    public void createTestSuccess() {
        Person person = new Person("Oleg", 55);
        crudHolder.create(person);
        Assert.assertEquals(person, crudHolder.items.get(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTestFailed() {
        Person person = new Person("Kirill", 19);
        crudHolder.create(person);
        Assert.assertNotEquals(person, crudHolder.items.get(2));
    }

    @Test
    public void readTestSuccessful() {
        Person person = new Person("Kirill", 19);
        Assert.assertEquals(person, crudHolder.read(0));
    }

    @Test(expected = NoSuchElementException.class)
    public void readTestFailed() {
        crudHolder.read(crudHolder.items.size());
        Assert.fail();
    }

    @Test
    public void updateSuccessful() {
        Person before = crudHolder.read(0);
        crudHolder.update(new Person("Kirill",55));
        Person after = crudHolder.read(0);
        Assert.assertNotEquals(before.age, after.age);

    }

    @Test(expected = NoSuchElementException.class)
    public void updateFailed() {
        Person person = new Person("Oleg",55);
        crudHolder.update(person);
        Assert.fail();
    }

    @Test
    public void deleteSuccessful() {
        Person person = new Person("Kirill",19);
        crudHolder.delete(person);
        Assert.assertNotEquals(person, crudHolder.read(0));
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteFailed() {
        Person person = new Person("Oleg",55);
        crudHolder.delete(person);
        Assert.fail();
    }

    @Test
    public void print() {
        crudHolder.print();
    }

}
