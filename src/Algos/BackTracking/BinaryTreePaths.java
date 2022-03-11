package Algos.BackTracking;

import java.util.*;

/*
 * https://leetcode.com/problems/binary-tree-paths/submissions/
 * */
public class BinaryTreePaths {
    private static List<String> subTrees;
    private static List<Integer> subTree;


    public static List<String> binaryTreePaths(TreeNode root) {
        subTrees = new ArrayList<>();
        subTree = new ArrayList<>();
        backTracking(root);
        return subTrees;
    }

    private static void backTracking(TreeNode node) {
        if (node != null) {
            place(node);
            if (isLeaf(node)) {
                StringBuilder subTreeSB = new StringBuilder();
                for (int v : subTree)
                    subTreeSB.append(v).append("->");
                subTreeSB.delete(subTreeSB.length() - 2, subTreeSB.length());
                subTrees.add(subTreeSB.toString());
            } else {
                backTracking(node.left);
                backTracking(node.right);
            }
            removeLast(node);
        }
    }

    private static boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    private static void place(TreeNode node) {
        subTree.add(node.val);
    }

    private static void removeLast(TreeNode node) {
        if (!subTree.isEmpty()) subTree.remove(subTree.size() - 1);
    }

    private static void build(int v, Integer[] heap, TreeNode node) {
        int l = (v << 1) + 1;
        int r = l + 1;
        if (l < heap.length && r < heap.length && node != null) {
            node.left = heap[l] != null ? new TreeNode(heap[l]) : null;
            build(l, heap, node.left);
            node.right = heap[r] != null ? new TreeNode(heap[r]) : null;
            build(r, heap, node.right);
        }
    }

    public static void main(String[] args) {
        Integer[] heap = {1, 2, 3, null, 5};
        TreeNode tree = new TreeNode(heap[0]);
        build(0, heap, tree);
        List<String> res = binaryTreePaths(tree);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static class Queue<V> extends AbstractQueue<V> {
        LinkedList<V> l = new LinkedList<>();

        @Override
        public Iterator<V> iterator() {
            return l.iterator();
        }

        @Override
        public int size() {
            return l.size();
        }

        @Override
        public boolean offer(V v) {
            boolean res = false;
            if (v != null) {
                l.add(v);
                res = true;
            }
            return res;
        }

        @Override
        public V poll() {
            Iterator<V> iter = iterator();
            V v = iter.next();
            if (v != null) {
                iter.remove();
                return v;
            }
            return null;
        }

        @Override
        public V peek() {
            return l.getFirst();
        }
    }
}
