package chapter3;

import chapter3.section2.BinarySearchTree;
import chapter3.section3.RedBlackTree;
import edu.princeton.cs.algs4.StdRandom;

public class Section3 {

    public static void exercise10() {
        Character[] input = { 'E', 'A', 'S', 'Y', 'Q', 'U', 'T', 'I', 'O', 'N' };
        RedBlackTree<Character, Character> tree = Section3.makeRBT(input);
        tree.printLevel();
    }

    private static <K extends Comparable<K>> RedBlackTree<K, K> makeRBT(K[] array) {
        RedBlackTree<K, K> tree = new RedBlackTree<>();
        for (K k : array) {
            tree.put(k, k);
        }
        return tree;
    }

    public static void exercise11() {
        Character[] input = { 'Y', 'L', 'P', 'M', 'X', 'H', 'C', 'R', 'A', 'E', 'S' };
        RedBlackTree<Character, Character> tree = Section3.makeRBT(input);
        tree.printLevel();
    }

    public static void exercise17() {
        Character[] alpha = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P' };

        StdRandom.shuffle(alpha);
        RedBlackTree<Character, Character> tree1 = Section3.makeRBT(alpha);
        System.out.println("Red black tree 1:");
        tree1.printLevel();
        BinarySearchTree<Character, Character> tree1_ = Section3.makeBST(alpha);
        System.out.println("Binary search tree 1:");
        BinarySearchTree.printLevel(tree1_.getRoot());

        StdRandom.shuffle(alpha);
        RedBlackTree<Character, Character> tree2 = Section3.makeRBT(alpha);
        System.out.println("Red black tree 2:");
        tree2.printLevel();
        BinarySearchTree<Character, Character> tree2_ = Section3.makeBST(alpha);
        System.out.println("Binary search tree 1:");
        BinarySearchTree.printLevel(tree2_.getRoot());
    }

    private static <K extends Comparable<K>> BinarySearchTree<K, K> makeBST(K[] array) {
        BinarySearchTree<K, K> tree = new BinarySearchTree<>();
        for (K k : array) {
            tree.put(k, k);
        }
        return tree;
    }

    public static void exercise30() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();
        tree.put(1, 2);
        System.out.println(tree.get(1));
        tree.put(2, 4);
        tree.put(2, 5);
        tree.printLevel();
    }

    public static void exercise37() {
        Integer[] array = { 14, 12, 5, 13, 2, 7, 10, 15, 8, 11, 3, 4, 6, 9 };
        RedBlackTree<Integer, Integer> tree = Section3.makeRBT(array);
        System.out.println("Before:");
        tree.printLevel();
        tree.put(1, 1);
        tree.deleteMin();
        System.out.println("After:");
        tree.printLevel();
    }

    public static void exercise39() {
        Integer[] array = { 1, 2, 3, 4, 5 };
        RedBlackTree<Integer, Integer> tree = Section3.makeRBT(array);
        tree.deleteMin();
        tree.printLevel();
    }

    public static void exercise40() {
        Integer[] array = { 1, 2, 3, 4, 5 };
        RedBlackTree<Integer, Integer> tree = Section3.makeRBT(array);
        tree.deleteMax();
        tree.printLevel();
    }

    public static void exercise41() {
        Integer[] array = { 1, 2, 3, 4, 5 };
        RedBlackTree<Integer, Integer> tree = Section3.makeRBT(array);
        tree.delete(4);
        tree.printLevel();
    }

    public static void main(String[] args) {
    }

}
