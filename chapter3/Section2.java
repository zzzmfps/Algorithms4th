package chapter3;

import chapter3.section2.BinarySearchTree;
import edu.princeton.cs.algs4.StdRandom;

public class Section2 {

    public static void exercise6() {
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
        tree.put(1, "a");
        System.out.println(tree.height()); // 1
        tree.put(2, "b");
        System.out.println(tree.height()); // 2
        tree.put(3, "c");
        System.out.println(tree.height()); // 3
        tree.put(0, "-");
        System.out.println(tree.height()); // 3
    }

    public static void exercise10() {
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();
        int[] input = StdRandom.permutation(21);
        for (int i = 0; i < input.length; ++i) {
            tree.put(input[i], input[i]);
        }
        System.out.println(tree.min()); // 0
        System.out.println(tree.max()); // 20
        System.out.println(tree.floor(30)); // 20
        System.out.println(tree.floor(10)); // 10
        System.out.println(tree.ceiling(-2)); // 0
        System.out.println(tree.ceiling(15)); // 15
        System.out.println(tree.select(12)); // 11
        System.out.println(tree.rank(7)); // 8
        tree.delete(11); // delete 11
        tree.deleteMax(); // delete 20
        tree.deleteMin(); // delete 0
        System.out.println(tree.keys());
    }

    public static void exercise13() {
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
        tree.put(4, "d");
        tree.put(3, "c");
        tree.put(1, "a");
        tree.put(2, "b");
        tree.put(5, "e");
        System.out.println(tree.get(5));
        System.out.println(tree.get(6));
    }

    public static void exercise14() {
        Section2.exercise10();
    }

    public static void exercise21() {
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
        tree.put(4, "d");
        tree.put(3, "c");
        tree.put(1, "a");
        tree.put(2, "b");
        tree.put(5, "e");
        for (int i = 0; i < 5; ++i) {
            System.out.println(tree.randomKey());
        }
    }

    public static void exercise25() {
        Integer[] permutation = { 5, 7, 3, 1, 4, 6, 2 };
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>(permutation);
        System.out.println(tree.height());
        BinarySearchTree<Integer, Integer> tree2 = new BinarySearchTree<>();
        for (int i = 0; i < permutation.length; i++) {
            tree2.put(permutation[i]);
        }
        System.out.println(tree2.height());
    }

    public static void exercise28() {
        Integer[] permutation = { 5, 7, 3, 1, 4, 6, 2 };
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>(permutation);
        System.out.println(tree.get(2));
    }

    public static void exercise29() {
        Integer[] permutation = { 5, 7, 3, 1, 4, 6, 2 };
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>(permutation);
        System.out.println(BinarySearchTree.isBinaryTree(tree.getRoot()));
    }

    public static void exercise30() {
        Integer[] permutation = { 5, 7, 3, 1, 4, 6, 2 };
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>(permutation);
        System.out.println(BinarySearchTree.isOrdered(tree.getRoot(), 1, 7));
        System.out.println(BinarySearchTree.isOrdered(tree.getRoot(), 0, 7));
        System.out.println(BinarySearchTree.isOrdered(tree.getRoot(), 1, 8));
    }

    public static void exercise31() {
        Integer[] permutation = { 5, 7, 3, 1, 4, 6, 2 };
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>(permutation);
        System.out.println(BinarySearchTree.hasNoDuplicates(tree.getRoot()));
    }

    public static void exercise33() {
        Integer[] permutation = { 5, 7, 3, 1, 4, 6, 2 };
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>(permutation);
        for (Integer i : permutation) {
            Integer key = tree.select(i); // i as rank
            int rank = tree.rank(i); // i as key
            if (i != tree.rank(key) || i != tree.select(rank)) {
                System.out.println(false);
                return;
            }
        }
        System.out.println(true);
    }

    public static void exercise36() {
        Integer[] permutation = { 5, 7, 3, 1, 4, 6, 2 };
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>(permutation);
        System.out.println(tree.keys());
        System.out.println(tree.keysNoRecursion());
    }

    public static void exercise37() {
        Integer[] permutation = { 5, 7, 3, 1, 4, 6, 2 };
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>(permutation);
        BinarySearchTree.printLevel(tree.getRoot());
    }

    public static void exercise42(int n) {
        int n2 = n * n;
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
        for (int i = 0; i < n; ++i) {
            tree.put(StdRandom.uniform(n2));
        }
        System.out.print("n = " + n + ", before: " + tree.height()); // use avg instead of max ?
        for (int i = 0; i < n2; ++i) {
            tree.delete(tree.randomKey());
            tree.put(StdRandom.uniform(n2));
        }
        System.out.println(", after: " + tree.height());
    }

    public static void main(String[] args) {
    }

}
