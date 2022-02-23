package Leetcode.Simple;


import java.util.*;

/*
 * https://leetcode.com/problems/clone-graph/submissions/
 * */
public class CloneGraph {

    public static void main(String[] args) {
        int[][] adjList = {{2, 4}, {1, 3}, {2, 4}, {1, 3}};
        Map<Integer, Node> graph = Utils.build(adjList);
//        Node root = Utils.buildNodes(adjList);
        Node root = Utils.createCyclicGraphTmp();
        Node newRoot = cloneGraph(root);
    }

    public static Node cloneGraph(Node node) {
        if (node == null) return null;
        Map<Integer, Node> used = new HashMap<>();
        Node newRoot = new Node(node.val), newCur, newNext, cur;
        Queue<Node> q = new Queue<>();
        Queue<Node> newQ = new Queue<>();
        q.offer(node);
        newQ.offer(newRoot);
        while (!q.isEmpty()) {
            cur = q.poll();
            newCur = newQ.poll();
            if (cur == null || newCur == null) continue;
            List<Node> neighbours = new ArrayList<>();
            newCur.neighbors = neighbours;
            used.putIfAbsent(cur.val, newCur);
            int i = 0;
            for (Node nextNeighbour : cur.neighbors) {
                if (!used.containsKey(nextNeighbour.val)) {
                    newNext = new Node(nextNeighbour.val);
                    neighbours.add(newNext);
                    q.offer(nextNeighbour);
                    newQ.offer(newNext);
                    used.putIfAbsent(nextNeighbour.val, newNext);
                } else {
                    newNext = used.get(nextNeighbour.val);
                    neighbours.add(newNext);
                }
            }
        }
        return newRoot;
    }

    private static Node getUnvisited(Node cur, Map<Integer, Integer> used) {
        for (Node next : cur.neighbors)
            if (!used.containsKey(next.val)) return next;
        return null;
    }

    private static class Utils {
        private static Node createCyclicGraphTmp() {
            Node one, two, three, four;
            one = new Node(1, new ArrayList<>());
            two = new Node(2, new ArrayList<>());
            three = new Node(3, new ArrayList<>());
            four = new Node(4, new ArrayList<>());
            one.neighbors.add(two);
            one.neighbors.add(four);
            two.neighbors.add(one);
            two.neighbors.add(three);
            three.neighbors.add(two);
            three.neighbors.add(four);
            four.neighbors.add(three);
            four.neighbors.add(one);
            return one;
        }

        private static Map<Integer, Node> build(int[][] adjList) {
            Map<Integer, Node> graph = new HashMap<>();
            for (int vertex = 0; vertex < adjList.length; vertex += 1) {
                ArrayList<Node> neighbours = new ArrayList<>();
                for (int neighbour : adjList[vertex])
                    neighbours.add(new Node(neighbour));
                graph.putIfAbsent(vertex + 1, new Node(vertex, neighbours));
            }
            return graph;
        }

        private static Node buildNodes(int[][] adjList) {
            int vertex = 1;
            Node node = new Node(vertex);
            ArrayList<Node> neighbours = new ArrayList<>();
            for (int neighbour : adjList[vertex - 1])
                neighbours.add(new Node(neighbour));
            node.neighbors = neighbours;
            for (Node next : node.neighbors) {
                neighbours = new ArrayList<>();
                for (int neighbour : adjList[next.val - 1])
                    neighbours.add(new Node(neighbour));
                next.neighbors = neighbours;
            }
            return node;
        }
    }


    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
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
            V v = it.next();
            if (v != null) {
                it.remove();
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
