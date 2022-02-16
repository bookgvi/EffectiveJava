package Structures.Tree.ArrayAndRecursion;

import java.util.*;

public class BuildBinaryTreeFromHeap {

    public static void main(String[] args) {
        Integer[] heap = {3, 9, 20, null, 92, 15, 7, null, null, null, null, 1, 2};
        TreeNode root = build(heap, 0);
    }

    // построение дерева из хипа с помощью BFS
    private static TreeNode build(Integer[] heap, int rootV) {
        int[] used = new int[heap.length];
        Integer curV, nextV;
        Queue<TreeNode> q = new Queue<>();
        Queue<Integer> qv = new Queue<>();
        used[rootV] = 1;
        TreeNode root = new TreeNode(heap[rootV]), nextNode = root;
        q.offer(nextNode);
        qv.offer(rootV);
        while (!q.isEmpty()) {
            nextNode = q.poll();
            curV = qv.poll();
            if (nextNode == null || curV == null) continue;
            Pair p;
            while ((p = getNext(heap, used, curV)) != null) {
                nextV = p.val;
                used[nextV] = 1;
                qv.offer(nextV);
                if (heap[nextV] != null) {
                    if (Objects.equals(p.side, "left")) {
                        nextNode.left = new TreeNode(heap[nextV]);
                        q.offer(nextNode.left);
                    } else {
                        nextNode.right = new TreeNode(heap[nextV]);
                        q.offer(nextNode.right);
                    }
                }
            }
        }
        return root;
    }

    private static Pair getNext(Integer[] heap, int[] used, Integer curV) {
        Pair p = new Pair();
        for (int i : new int[]{1, 2}) {
            int v = (curV << 1) + i;
            p.val = v;
            p.side = i == 1 ? "left" : "right";
            if (v < heap.length && used[v] == 0) return p;
        }
        return null;
    }

    private static class Pair {
        private String side;
        private Integer val;
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
            Iterator<V> it = iterator();
            V v = iterator().next();
            if (v != null) {
                l.remove();
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
