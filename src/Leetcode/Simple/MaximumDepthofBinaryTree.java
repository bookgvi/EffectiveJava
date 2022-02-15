package Leetcode.Simple;

/*
* https://leetcode.com/problems/maximum-depth-of-binary-tree/submissions/
* */
public class MaximumDepthofBinaryTree {
    private static int depthL = 0, depthR = 0;
    private static TreeNode root = new TreeNode(), next = root;

    private static void build(Integer[] arr, int v) {
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int depthL = maxDepth(root.left);
        int depthR = maxDepth(root.right);
        return Math.max(depthL, depthR) + 1;
    }

    private static int maxDepth(Integer[] heap, int v) {
        if (v >= heap.length || heap[v] == null) return 0;
        int depthL = maxDepth(heap, (v << 1) + 1);
        int depthR = maxDepth(heap, (v << 1) + 2);
        return Math.max(depthL, depthR) + 1;
    }

    private static void dfs(Integer[] tree, int v) {
        if (v < tree.length && tree[v] != null) {
            dfs(tree, (v << 1) + 1); // left subtree
            System.out.println(tree[v]);
            dfs(tree, (v << 1) + 2); // right subtree
        }
    }

    public static void main(String[] args) {
//        Integer[] heap = {1,null,2};
        Integer[] heap = {3, 9, 20, 91, 92, 15, 7, null,null,null,null,1,2};
        int res = maxDepth(heap, 0);
        dfs(heap, 0);
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
}
