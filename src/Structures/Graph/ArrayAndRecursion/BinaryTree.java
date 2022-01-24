package Structures.Graph.ArrayAndRecursion;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.*;

/*
*
*          1
*         / \
*        /   \
*       /     \
*      2       3
*     / \     / \
*   21   22 31   32
*
* */
public class BinaryTree {
    private static final int[] g = {1, 2, 3, 21, 22, 31, 32};
    private static int[] used = new int[g.length];

    // DFS
    private static void symmetric(int v) {
        int l = (v << 1) | 1;
        int r = l + 1;
        if (l < g.length) symmetric(l);
        System.out.printf("%d; ", g[v]);
        if (r < g.length) symmetric(r);
    }

    private static void preorder(int v) {
        int l = (v << 1) | 1;
        int r = l + 1;
        System.out.printf("%d; ", g[v]);
        if (l < g.length) preorder(l);
        if (r < g.length) preorder(r);
    }

    private static void postorder(int v) {
        int l = (v << 1) | 1;
        int r = l + 1;
        if (l < g.length) postorder(l);
        if (r < g.length) postorder(r);
        System.out.printf("%d; ", g[v]);
    }

    // BFS
    private static void bfs(int v) {
        Integer cur, next;
        Queue<Integer> q = new Queue<>();
        used[v] = 1;
        q.offer(v);
        System.out.printf("%d; ", g[v]);
        while (!q.isEmpty()) {
            cur = q.poll();
            if (cur == null) continue;
            while ((next = getNext(cur)) != -1) {
                used[next] = 1;
                q.offer(next);
                System.out.printf("%d; ", g[next]);
            }
        }
        used = new int[g.length];
    }

    private static int getNext(int cur) {
        int l = (cur << 1) | 1;
        int r = l + 1;
        for (int v : new int[]{l, r}) {
            if (v < g.length && used[v] == 0) return v;
        }
        return -1;
    }

    public static void main(String[] args) {
        symmetric(0);
        System.out.println();
        preorder(0);
        System.out.println();
        postorder(0);
        System.out.println();
        bfs(0);
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
            Iterator<V> iterator = iterator();
            V v = iterator.next();
            if (v != null) {
                iterator.remove();
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
