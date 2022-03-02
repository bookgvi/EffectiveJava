package Algos.BackTracking;

import java.util.*;

/*
 * https://leetcode.com/problems/path-sum-ii/submissions/
 * */
public class PathSumII {
    private static List<List<Integer>> resultPaths;
    private static List<Integer> path;
    private static List<TreeNode> leafNodesInSum;
    private static List<TreeNode> parent;
    private static int sum;

    public static void main(String[] args) {
        Integer[] tree = {-2, null, -3};
        int targetSum = -5;
//        Integer[] tree = {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, null, 5, 1};
//        int targetSum = 22;
        TreeNode root = buildTree(tree, 0, new TreeNode(tree[0]));
        pathSum(root, targetSum);
    }

    private static TreeNode buildTree(Integer[] arr, int i, TreeNode node) {
        int left = (i << 1) + 1;
        int right = left + 1;
        if (node != null && left < arr.length && right < arr.length) {
            node.left = arr[left] != null ? new TreeNode(arr[left]) : null;
            buildTree(arr, left, node.left);
            node.right = arr[right] != null ? new TreeNode(arr[right]) : null;
            buildTree(arr, right, node.right);
        }
        return node;
    }

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        resultPaths = new ArrayList<>();
        if (root == null) return resultPaths;
        parent = new ArrayList<>();
        path = new ArrayList<>();
        leafNodesInSum = new ArrayList<>();
        sum = 0;
        backTrack(root, targetSum);
        return resultPaths;
    }

    private static void backTrack(TreeNode node, int targetSum) {
        if (finish(node, targetSum)) {
            resultPaths.add(List.copyOf(path));
        } else if (check(node, targetSum)) {
            if (canPlace(node)) {
                place(node);
                backTrack(node.left, targetSum);
                node = remove(node);
                if (canPlace(node)) {
                    place(node);
                    backTrack(node.right, targetSum);
                    node = remove(node);
                }
            }
        }
    }

    private static boolean check(TreeNode node, int targetSum) {
        return node != null;
    }

    private static boolean canPlace(TreeNode node) {
        if (leafNodesInSum.isEmpty()) return true;
        return leafNodesInSum.get(leafNodesInSum.size() - 1) != node;
    }

    private static void place(TreeNode node) {
        parent.add(node);
        path.add(node.val);
        sum += node.val;
        if (node.left == null && node.right == null) leafNodesInSum.add(node);
    }

    private static boolean finish(TreeNode node, int targetSum) {
        boolean leafNode = !parent.isEmpty()
                && parent.get(parent.size() - 1).left == null
                && parent.get(parent.size() - 1).right == null;
        return leafNode && sum == targetSum;
    }

    private static TreeNode remove(TreeNode node) {
        TreeNode parentNode = !parent.isEmpty() ? parent.remove(parent.size() - 1) : node;
        int lastVal = path.remove(path.size() - 1);
        sum -= lastVal;
        return parentNode;
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
