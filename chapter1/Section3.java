package chapter1;

import java.io.File;

import chapter1.section2.Date;
import chapter1.section2.Transaction;
import chapter1.section3.Buffer;
import chapter1.section3.Deque;
import chapter1.section3.DoubleList;
import chapter1.section3.DoubleList.DoubleNode;
import chapter1.section3.FixedCapacityStackOfStrings;
import chapter1.section3.GeneralizedQueueUsingArray;
import chapter1.section3.GeneralizedQueueUsingLinkedList;
import chapter1.section3.List;
import chapter1.section3.LoopList;
import chapter1.section3.MoveToFront;
import chapter1.section3.Node;
import chapter1.section3.Queue;
import chapter1.section3.QueueUsingStacks;
import chapter1.section3.RandomBag;
import chapter1.section3.RandomQueue;
import chapter1.section3.ResizingArrayDeque;
import chapter1.section3.ResizingArrayQueueOfStrings;
import chapter1.section3.RingBuffer;
import chapter1.section3.Stack;
import chapter1.section3.Steque;
import chapter1.section3.TwoStacksUsingDeque;
import chapter1.section3.TwoStacksUsingDeque.StackId;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Section3 {

    public static void exercise1() {
        final FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(1);
        StdOut.println(stack.isEmpty());
        stack.push("item");
        StdOut.println(stack.isFull());
    }

    public static void exercise4() {
        final Stack<Character> stack = new Stack<>();
        while (!StdIn.isEmpty()) {
            final Character c = StdIn.readChar();
            if ("([{".contains(c.toString())) {
                stack.push(c);
            } else if ("([{".indexOf(stack.pop()) != ")]}".indexOf(c)) {
                StdOut.println("false");
                return;
            }
        }
        StdOut.println("true");
    }

    public static void exercise7() {
        final Stack<Integer> stack = new Stack<>();
        stack.push(1);
        StdOut.println(stack.peek());
        StdOut.println(stack.size());
    }

    public static void exercise9() {
        // 1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
        final String[] expr = StdIn.readLine().split(" ");
        final Stack<String> stack = new Stack<>();
        for (int i = 0; i < expr.length; ++i) {
            if (!")".equals(expr[i])) {
                stack.push(expr[i]);
                continue;
            }
            // assert 3 <= stack.size();
            String tmp = "";
            for (int j = 0; j < 3; ++j) {
                tmp = stack.pop() + " " + tmp;
            }
            tmp = "( " + tmp + ")";
            stack.push(tmp);
        }
        // assert 1 == stack.size();
        StdOut.println(stack.peek());
    }

    public static void exercise10() { // as `InfixToPostfix`
        // ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
        final String[] expr = StdIn.readLine().replaceAll("\\( ", "").split(" ");
        final Stack<String> nums = new Stack<>(), ops = new Stack<>();
        for (int i = 0; i < expr.length; ++i) {
            if (")".equals(expr[i])) {
                // assert 2 <= nums.size() && 1 <= ops.size();
                final String tmp = nums.pop();
                nums.push(String.format("%s %s %s", nums.pop(), tmp, ops.pop()));
            } else if ("+-*/".contains(expr[i])) {
                ops.push(expr[i]);
            } else {
                nums.push(expr[i]);
            }
        }
        // assert 1 == nums.size() && ops.isEmpty();
        StdOut.println(nums.peek());
    }

    public static void exercise11() { // as `EvaluatePostfix`
        // 1 2 + 3 4 - 5 6 - * *
        final String[] expr = StdIn.readLine().split(" ");
        final Stack<String> stack = new Stack<>();
        for (int i = 0; i < expr.length; ++i) {
            if (!"+-*/".contains(expr[i])) {
                stack.push(expr[i]);
                continue;
            }
            final double y = Double.parseDouble(stack.pop());
            final double x = Double.parseDouble(stack.pop());
            switch (expr[i].charAt(0)) {
                case '+':
                    stack.push(String.valueOf(x + y));
                    break;
                case '-':
                    stack.push(String.valueOf(x - y));
                    break;
                case '*':
                    stack.push(String.valueOf(x * y));
                    break;
                case '/':
                    stack.push(String.valueOf(x / y));
                    break;
            }
        }
        // assert 1 == stack.size();
        StdOut.println(stack.peek());
    }

    public static void exercise12() {
        final Stack<String> st1 = new Stack<>();
        st1.push("1");
        st1.push("2");
        st1.push("3");
        final Stack<String> st2 = Stack.copy(st1);
        while (!st2.isEmpty()) {
            StdOut.println(st2.pop());
        }
    }

    public static void exercise14() {
        final ResizingArrayQueueOfStrings<Integer> q = new ResizingArrayQueueOfStrings<>(2);
        q.offer(1);
        q.offer(2);
        q.resize(1);
        while (!q.isEmpty()) {
            StdOut.println(q.poll());
        }
        StdOut.println("***************");
        q.offer(1);
        q.offer(2); // will fail
        q.resize(2);
        q.offer(3);
        while (!q.isEmpty()) {
            StdOut.println(q.poll());
        }
    }

    public static void exercise15(final int k) {
        final Stack<String> stack = new Stack<>();
        while (!StdIn.isEmpty()) {
            stack.push(StdIn.readLine());
        }
        // assert k <= stack.size();
        for (int i = 1; i < k; ++i) {
            stack.pop();
        }
        StdOut.println(stack.peek());
    }

    public static void exercise16() {
        final Date[] dates = Date.readDates();
        StdOut.println("***************");
        for (final Date date : dates) {
            StdOut.println(date);
        }
    }

    public static void exercise17() {
        final Transaction[] transactions = Transaction.readTransactions();
        StdOut.println("***************");
        for (final Transaction tx : transactions) {
            StdOut.println(tx);
        }
    }

    public static void exercise19() {
        final List<Integer> list = new List<>();
        list.add(1);
        list.add(2);
        StdOut.println(list.deleteTail());
        StdOut.println(list);
    }

    public static void exercise20() {
        final List<Integer> list = new List<>();
        list.add(1);
        list.add(2);
        list.add(3);
        StdOut.println(list.remove(1));
        StdOut.println(list);
    }

    public static void exercise21() {
        final List<Integer> list = new List<>();
        list.add(1);
        list.add(2);
        StdOut.println(list.find(2));
        StdOut.println(list.find(3));
    }

    public static void exercise24() {
        final List<Integer> list = new List<>();
        list.add(1);
        list.add(2);
        list.add(3);
        final Node<Integer> node = list.find(1);
        list.removeAfter(node);
        StdOut.println(list);
    }

    public static void exercise25() {
        final List<Integer> list = new List<>();
        list.add(1);
        list.add(3);
        final Node<Integer> node = list.find(1);
        list.insertAfter(node, new Node<Integer>(2, null));
        StdOut.println(list);
    }

    public static void exercise26() {
        final List<Integer> list = new List<>();
        list.add(1);
        list.add(2);
        list.add(1);
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(1);
        StdOut.println(list.remove(1));
        StdOut.println(list);
    }

    public static void exercise27() {
        final List<Integer> list = new List<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(3);
        StdOut.println(List.max(list.first()));
    }

    public static void exercise28() {
        final List<Integer> list = new List<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(3);
        StdOut.println(List.maxWithRecursion(list.first()));
    }

    public static void exercise29() {
        final LoopList<Integer> queue = new LoopList<>();
        queue.add(1);
        StdOut.println(queue.remove(queue.size() - 1));
        queue.add(2);
        queue.add(3);
        queue.add(4);
        StdOut.println(queue.remove(queue.size() - 1));
        queue.add(5);
        StdOut.println(queue);
    }

    public static void exercise31() {
        final DoubleNode<Integer> node1 = new DoubleNode<>(1);
        final DoubleNode<Integer> node2 = new DoubleNode<>(2);
        final DoubleNode<Integer> node3 = new DoubleNode<>(3);
        final DoubleNode<Integer> node4 = new DoubleNode<>(4);
        final DoubleNode<Integer> node5 = new DoubleNode<>(5);
        final DoubleNode<Integer> node6 = new DoubleNode<>(6);
        DoubleList.insertAfter(node1, node2); // insert as tail
        DoubleList.insertAfter(node1, node3); // insert after_1
        DoubleList.insertAfter(node1, node6); // insert after_2
        DoubleList.insertBefore(node1, node4); // insert as head
        DoubleList.insertBefore(node1, node5); // insert before
        StdOut.println(node1); // 4 5 1 6 3 2
        DoubleList.delete(node1); // delete certain element
        DoubleList.delete(node2); // delete tail
        DoubleList.delete(node4); // delete head
        StdOut.println(node6); // 5 6 3
    }

    public static void exercise32() {
        final Steque<Integer> steque = new Steque<>();
        steque.push(1);
        steque.push(2);
        steque.offer(3);
        steque.offer(4);
        steque.pop();
        steque.poll();
        StdOut.print(steque); // 2 3
    }

    public static void exercise33_1() { // double linked list
        final Deque<Integer> deque = new Deque<>();
        deque.offerFirst(1);
        deque.offerFirst(2);
        deque.offerLast(3);
        deque.offerLast(4);
        deque.pollLast();
        deque.pollFirst();
        deque.forEach(v -> StdOut.println(v)); // 1 3
    }

    public static void exercise33_2() { // resizing array
        final ResizingArrayDeque<Integer> resizingArrayDeque = new ResizingArrayDeque<>(2);
        resizingArrayDeque.offerFirst(1);
        resizingArrayDeque.offerFirst(2);
        resizingArrayDeque.offerLast(3);
        resizingArrayDeque.offerLast(4);
        resizingArrayDeque.offerLast(5);
        resizingArrayDeque.pollLast();
        resizingArrayDeque.pollFirst();
        resizingArrayDeque.forEach(v -> StdOut.println(v)); // 1 3 4
    }

    public static void exercise34() {
        final RandomBag<Integer> bag = new RandomBag<>(16);
        bag.add(1);
        bag.add(2);
        bag.add(3);
        bag.add(4);
        bag.add(5);
        for (int i = 0; i < 10; ++i) {
            StdOut.print("round " + i + ":");
            bag.forEach(v -> StdOut.print(" " + v));
            StdOut.println();
        }
    }

    public static void exercise35() {
        final RandomQueue<Integer> queue = new RandomQueue<>(52);
        for (int i = 0; i < 4; ++i) {
            for (int j = 1; j <= 13; ++j) {
                queue.offer(j);
            }
        }
        StdOut.println("sample: " + queue.sample());

        final int[][] cards = new int[4][13];
        for (int i = 0; i < 13; ++i) {
            for (int j = 0; j < 4; ++j) {
                cards[j][i] = queue.poll();
            }
        }
        for (int i = 0; i < 4; ++i) {
            StdOut.print("person " + i + ":");
            for (final int card : cards[i]) {
                StdOut.print(" " + card);
            }
            StdOut.println();
        }
    }

    public static void exercise36() {
        final RandomQueue<Integer> queue = new RandomQueue<>(10);
        for (int i = 1; i <= 13; ++i) {
            queue.offer(i);
        }
        queue.forEach(v -> StdOut.print(v + " ")); // return and not dequeue
        StdOut.println();
        queue.forEach(v -> StdOut.print(v + " "));
        StdOut.println();
    }

    public static void exercise37(final int N, final int M) {
        final Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < N; ++i) {
            queue.offer(i);
        }
        int count = 0;
        while (!queue.isEmpty()) {
            final int x = queue.poll();
            if (M == ++count) {
                StdOut.print(x + " ");
                count = 0;
            } else {
                queue.offer(x);
            }
        }
    }

    public static void exercise38_1() {
        GeneralizedQueueUsingArray<Integer> queue = new GeneralizedQueueUsingArray<>(2);
        queue.offer(1);
        queue.offer(3);
        queue.offer(5);
        queue.delete(2);
        StdOut.println(queue);
    }

    public static void exercise38_2() {
        GeneralizedQueueUsingLinkedList<Integer> queue = new GeneralizedQueueUsingLinkedList<>();
        queue.offer(1);
        queue.offer(3);
        queue.offer(5);
        queue.delete(2);
        StdOut.println(queue);
    }

    public static void exercise39() {
        final RingBuffer<Integer> buffer = new RingBuffer<>(2);
        final Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (buffer.isEmpty()) {
                        StdOut.println("buffer is EMPTY, waiting...");

                    } else {
                        StdOut.println("consumed: " + buffer.consume());
                    }
                    try {
                        Thread.sleep(StdRandom.uniform(1000));
                    } catch (final InterruptedException e) {
                    }
                }
            }
        });
        final Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (buffer.isFull()) {
                        StdOut.println("buffer is FULL, waiting...");

                    } else {
                        StdOut.println("produced: " + buffer.produce(StdRandom.uniform(10)));
                    }
                    try {
                        Thread.sleep(StdRandom.uniform(1000));
                    } catch (final InterruptedException e) {
                    }
                }
            }
        });
        consumer.start();
        producer.start();
    }

    public static void exercise40() {
        final MoveToFront mtf = new MoveToFront("aeiowagnaw");
        StdOut.println(mtf);// wangoie
        mtf.readChar('x');
        mtf.readChar('g');
        mtf.readChar('q');
        StdOut.println(mtf); // qgxwanoie
    }

    public static void exercise41() {
        final Queue<Integer> q1 = new Queue<>();
        q1.offer(1);
        q1.offer(2);
        final Queue<Integer> q2 = new Queue<>(q1);
        q2.offer(3);
        q2.poll();
        StdOut.println(q1);
        StdOut.println(q2);
    }

    public static void exercise42() {
        final Stack<Integer> s1 = new Stack<>();
        s1.push(1);
        s1.push(2);
        final Stack<Integer> s2 = new Stack<>(s1);
        s2.pop();
        s2.push(3);
        while (!s1.isEmpty()) {
            StdOut.print(s1.pop() + " ");
        }
        StdOut.println();
        while (!s2.isEmpty()) {
            StdOut.print(s2.pop() + " ");
        }
    }

    public static void exercise43() {
        String path = "C:/Program Files (x86)/gnupg";
        __exercise43(path, 0);
    }

    private static void __exercise43(String path, int level) {
        File dir = new File(path);
        File[] files = dir.listFiles();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; ++i) {
            sb.append("    ");
        }
        String prefix = sb.toString();

        Queue<String> q = new Queue<>(); // print subdirs first, then files
        for (File file : files) {
            if (file.isDirectory()) {
                StdOut.println(prefix + file.getName());
                __exercise43(file.getAbsolutePath(), level + 1);
            } else {
                q.offer(file.getName());
            }
        }
        while (!q.isEmpty()) {
            StdOut.println(prefix + q.poll());
        }
    }

    public static void exercise44() {
        Buffer buffer = new Buffer();
        buffer.insert('a'); // a_
        buffer.insert('b'); // ab_
        buffer.left(2); // _ab
        buffer.insert('c'); // c_ab
        buffer.right(1); // ca_b
        buffer.insert('d'); // cad_b
        buffer.right(1); // cadb_
        buffer.delete(); // cad_
        StdOut.println(buffer.size()); // 3
        while (buffer.size() > 0) {
            StdOut.println(buffer.delete()); // d a c
        }
    }

    public static void exercise45_1(String[] ops) {
        int count = 0;
        for (String op : ops) {
            if (!"-".equals(op)) {
                ++count;
                continue;
            }
            if (--count < 0) {
                StdOut.println("underflow");
                return;
            }
        }
        StdOut.println("success");
    }

    public static void exercise45_2(String[] ops, String[] target) {
        if (ops.length != 2 * target.length) {
            StdOut.println("no");
            return;
        }
        Stack<String> stack = new Stack<>();
        for (int i = 0, j = 0; i < ops.length; ++i) {
            if (!"-".equals(ops[i])) {
                stack.push(ops[i]);
                continue;
            }
            if (!target[j++].equals(stack.pop())) {
                StdOut.println("no");
                return;
            }
        }
        StdOut.println("yes");
    }

    public static void exercise47() {
        Queue<Integer> q1 = new Queue<>(); // or Queue, Stack
        q1.offer(1);
        q1.offer(2);
        q1.offer(3);
        Queue<Integer> q2 = new Queue<>(q1);
        q2.poll();
        q2.offer(4);
        q2.catenation(q1);
        StdOut.println(q1); // 1 2 3
        StdOut.println(q2); // 2 3 4 1 2 3
    }

    public static void exercise48() {
        TwoStacksUsingDeque<Integer> twoStacks = new TwoStacksUsingDeque<>();
        twoStacks.push(StackId.Stack1, 1);
        twoStacks.push(StackId.Stack1, 2);
        twoStacks.push(StackId.Stack2, 3);
        twoStacks.push(StackId.Stack2, 4);
        twoStacks.pop(StackId.Stack1);
        StdOut.println(twoStacks);
        StdOut.println(twoStacks); // ensure stacks unchanged after iteration
    }

    public static void exercise49() { // amortized O(1)
        QueueUsingStacks<Integer> queue = new QueueUsingStacks<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.poll();
        queue.offer(4);
        queue.offer(5);
        StdOut.println(queue); // [5, 4], [2, 3]
    }

    public static void exercise50() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        for (Integer i : stack) {
            StdOut.println(i);
            if (2 == i) {
                stack.push(4); // cause fail-fast in next iteration
            }
        }
    }

    public static void main(final String[] args) {
    }

}
