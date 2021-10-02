package chapter3;

import chapter3.section1.ArrayST;
import chapter3.section1.ArraySTWithMtF;
import chapter3.section1.BinarySearchST;
import chapter3.section1.BinarySearchSTWithOneArray;
import common.Item;

public class Section1 {

    private static double ranks[] = { 4.0, 3.0, 2.0, 1.0, 0.0, 0.0 };

    public static double exercise1(final String scores) {
        double score = 0.0;
        int count = 0;

        for (int i = 0; i < scores.length(); ++i) {
            final char ch = scores.charAt(i);
            if ('+' == ch || '-' == ch) {
                score += 0.33 * ('+' == ch ? 1.0 : -1.0);
                continue;
            }
            score += ranks[ch - 65];
            ++count;
        }
        return score / count;
    }

    public static void exercise2() {
        ArrayST<Integer, String> ast = new ArrayST<>(4);
        ast.put(1, "abc");
        ast.put(5, "world");
        ast.put(3, "hello");
        System.out.println(ast.get(5));
        System.out.println(ast.get(6));
        ast.delete(3);
        System.out.println(ast.contains(3));
        System.out.println(ast.size());
        ast.keys().forEach(k -> System.out.println("key " + k));
    }

    public static void exercise12() {
        Item<Integer, String>[] array = Item.makeArray(10);
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Item<>(i, "str " + i);
        }

        BinarySearchSTWithOneArray<Integer, String> st = new BinarySearchSTWithOneArray<>(array);
        System.out.println(st.get(5));
        st.put(3, "new string for 3");
        System.out.println(st.get(3));

        System.out.println(st.size());
        System.out.println(st.size(3, 9));

        System.out.println(st.min());
        System.out.println(st.max());

        System.out.println(st.contains(10));
        st.put(10, "hello world");
        System.out.println(st.get(10));

        st.delete(6);
        System.out.println(st.floor(6));
        System.out.println(st.ceiling(6));
    }

    public static void exercise16() {
        int capacity = 10;
        BinarySearchST<Integer, String> st = new BinarySearchST<>(capacity);
        for (int i = 0; i < capacity; ++i) {
            st.put(i, "str " + i);
        }
        System.out.println(st.floor(2));
        st.delete(2);
        System.out.println(st.floor(2));
    }

    public static void exercise17() {
        Section1.exercise16();
    }

    public static void exercise22() {
        ArraySTWithMtF<Integer, String> ast = new ArraySTWithMtF<>(4);
        ast.put(1, "abc");
        ast.put(5, "world");
        ast.put(3, "hello");
        System.out.println(ast.get(5));
        System.out.println(ast.get(6));
        ast.delete(3);
        System.out.println(ast.contains(3));
        System.out.println(ast.size());
        ast.keys().forEach(k -> System.out.println("key " + k));
    }

    public static void exercise28() {
        Section1.exercise16();
    }

    public static void main(final String[] args) {
    }

}
