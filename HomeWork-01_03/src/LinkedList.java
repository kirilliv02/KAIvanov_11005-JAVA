import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {


    private static class Node<T> {
        private T item;
        private Node<T> next;
    }

    private Node<T> head;

    void add(T item) {
        Node<T> node = new Node<>();
        node.item = item;
        if (head == null) {
            head = node;
            return;
        }
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    T get(int i) {
        int count = 0;
        Node<T> temp = head;
        while (count != i) {
            count++;
            temp = temp.next;
        }
        return temp.item;
    }

    int count() {
        Node<T> temp = head;
        int count = 1;
        while (temp.next != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }

    //void remove(int i) {}

    private class ListIterator implements Iterator<T> {
        Node<T> temp = head;

        @Override
        public boolean hasNext() {
            if (head == null) {
                head = temp;
                return false;
            } else
                return true;
        }

        @Override
        public T next() {
            T value = head.item;
            head = head.next;
            return value;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

}
