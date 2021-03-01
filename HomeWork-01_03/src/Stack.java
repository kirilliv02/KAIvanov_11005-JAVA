import java.util.Iterator;

public class Stack<T> implements Iterable<T> {


    private static class Node<T> {
        private final T value;
        private final Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<T> head;

    public void push(T value) {
        head = new Node<>(value, head);
    }

    public T pop() {
        T temp = head.value;
        head = head.next;
        return temp;
    }

    public T peek() {
        return head.value;
    }

    private class StackIterator implements Iterator<T> {
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
            T temp = head.value;
            head = head.next;
            return temp;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

}
