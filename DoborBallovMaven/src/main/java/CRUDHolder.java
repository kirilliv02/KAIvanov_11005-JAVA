import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CRUDHolder<T extends Comparable<T> & Printable> {

    ArrayList<T> items = new ArrayList<>();

    public void create(T item) throws IllegalArgumentException {
        for (T i : items) {
            if (i.compareTo(item) == 0) {
                throw new IllegalArgumentException("Данный элемент уже существует!");
            }
        }
        items.add(item);
    }

    public T read(int id) throws NoSuchElementException {
        if (id >= items.size()) throw new NoSuchElementException("Элемент не найден!");

        return items.get(id);
    }

    public void update(T item) throws NoSuchElementException {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).compareTo(item) == 0) {
                items.set(i, item);
                return;
            }
        }
        throw new NoSuchElementException("Элемент не найден!");
    }

    public void delete(T item) throws NoSuchElementException {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).compareTo(item) == 0) {
                items.remove(i);
                return;
            }
        }
        throw new NoSuchElementException("Элемент не найден!");
    }

    public void print() {
        for (T item : items) {
            item.print();
        }
    }

}