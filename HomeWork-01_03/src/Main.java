public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        for (Integer item : stack) {
            System.out.print(item + ", ");
        }
        System.out.println();
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        for (Integer item : list) {
            System.out.print(item + ", ");
        }
        System.out.println();
        System.out.println(list.get(1));
        System.out.println(list.count());

    }

}
