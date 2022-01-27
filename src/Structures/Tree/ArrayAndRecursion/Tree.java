package Structures.Tree.ArrayAndRecursion;

import java.util.*;

/*
 *                        1
 *            /           |           \
 *           11           12          13
 *        /  |  \      /  |  \      /  |  \
 *      111 112 113  221 222 223  331 332 333
 *      / \
 *   1111 1112
 *
 * */
public class Tree {
    private static final int[] g = {1, 11, 12, 13, 111, 112, 113, 221, 222, 223, 331, 332, 333, 1111, 1112};
    private static final int n = g.length;
    private static int[] used = new int[n];
    private static int[] p = new int[n];
    private static int isCycle = 0;

    private static void preorder(int v) {
        int fst = (v * 3) + 1;
        int snd = fst + 1;
        int thd = snd + 1;
        System.out.printf("%d; ", g[v]);
        if (fst < n) preorder(fst);
        if (snd < n) preorder(snd);
        if (thd < n) preorder(thd);
    }

    private static void postorder(int v) {
        int fst = (v * 3) + 1;
        int snd = fst + 1;
        int thd = snd + 1;
        if (fst < n) postorder(fst);
        if (snd < n) postorder(snd);
        if (thd < n) postorder(thd);
        System.out.printf("%d; ", g[v]);
    }

    private static int getNext(int v) {
        int fst = (v * 3) + 1;
        int snd = fst + 1;
        int thd = snd + 1;
        for (int next : new int[]{fst, snd, thd})
            if (next < n && used[next] == 0) return next;
        return -1;
    }

    private static void dfsI(int v) {
        used = new int[n];
        used[v] = 1;
        Stack<Integer> s = new Stack<>();
        s.push(v);
        System.out.printf("%d; ", g[v]);
        while(!s.isEmpty()) {
            int cur = s.peek(), next;
            if ((next = getNext(cur)) == -1) s.pop();
            else {
                used[next] = 1;
                s.push(next);
                System.out.printf("%d; ", g[next]);
            }
        }
        used = new int[n];
    }

    // вершины выводятся в обратном порядке
    private static void dfs(int v) {
        used[v] = 1;
        int next;
        while((next = getNext(v)) != -1) {
            dfs(next);
        }
        System.out.printf("%d; ",g[v]);
    }

    // вершины выводятся в обратном порядке
    private static void dfsIsCycle(int v) {
        used[v] = 1;
        int fst = (v * 3) + 1;
        int snd = fst + 1;
        int thd = snd + 1;
        for (int next : new int[]{fst, snd, thd}) {
            if (next < n && used[next] == 0) {
                p[next] = v;
                dfs(next);
            } else if (next < n && used[next] == 1 && p[v] == next) {
                isCycle = 1;
            }
        }
        used[v] = 2;
        if (isCycle == 0) System.out.printf("%d; ", g[v]);
    }

    private static void bfs(int v) {
        used = new int[n];
        Integer cur, next;
        Queue<Integer> q = new Queue<>();
        q.offer(v);
        used[v] = 1;
        System.out.printf("%d; ", g[v]);
        while (!q.isEmpty()) {
            cur = q.poll();
            if (cur == null) continue;
            while ((next = getNext(cur)) != -1) {
                q.offer(next);
                used[next] = 1;
                System.out.printf("%d; ", g[next]);
            }
        }
        used = new int[n];
    }

    public static void main(String[] args) {
        System.out.print("Preorder:\n\t"); preorder(0);
        System.out.println();
        System.out.print("Postorder:\n\t"); postorder(0);
        System.out.println();
        System.out.print("Bfs:\n\t"); bfs(0);
        System.out.println();
        System.out.print("Dfs:\n\t"); dfs(0);
        used = new int[n];
        System.out.println();
        System.out.print("Dfs with cycle check:\n\t"); dfsIsCycle(0);
        System.out.println();
        System.out.print("Dfs iterable:\n\t"); dfsI(0);
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
