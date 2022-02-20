package Leetcode.Simple;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * https://leetcode.com/problems/combination-sum/submissions/
 *
 * Accepted
 * */
class CombinationSumSolution {
    private static int n, len, sum;
    private static List<Integer> nextCombination, prevIndexes;
    private static List<List<Integer>> allCombinations;

    public static void main(String[] args) {
        int[] nums = {2};
        int target = 1;
        List<List<Integer>> res = combinationSum(nums, target);
    }

    public static List<List<Integer>> combinationSum(int[] nums, int target) {
        n = target;
        len = nums.length;
        nextCombination = new ArrayList<>();
        prevIndexes = new ArrayList<>();
        allCombinations = new ArrayList<>();
        backTrackSumm(nums);
        return allCombinations;
    }

    private static void backTrackSumm(int[] nums) {
        for (int i = 0; i < len; i += 1) {
            if (check(nums, i)) {
                store(nums, i);
                if (sum == n)
                    allCombinations.add(List.copyOf(nextCombination));
                else if (sum < n)
                    backTrackSumm(nums);
                remove();
            }
        }
    }

    private static boolean check(int[] nums, int curIndex) {
        if (prevIndexes.size() == 0) return true;
        return curIndex >= prevIndexes.get(prevIndexes.size() - 1) && sum + nums[curIndex] <= n;
    }

    private static void store(int[] nums, int curIndex) {
        nextCombination.add(nums[curIndex]);
        sum += nums[curIndex];
        prevIndexes.add(curIndex);
    }

    private static void remove() {
        int prevVal = nextCombination.remove(nextCombination.size() - 1);
        sum -= prevVal;
        prevIndexes.remove(prevIndexes.size() - 1);
    }
}

/*
 * https://leetcode.com/problems/combination-sum/submissions/
 *
 * это решение не подходит по причине лексикографической сортировки массивов (идите нахер с такими требованиями :D))
 * */
class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> listOfFragments = new ArrayList<>();
        List<Integer> fragment = IntStream.range(0, target).mapToObj(i -> 1).collect(Collectors.toList());

        boolean include = checkFragment(candidates, fragment);
        if (include) {
            listOfFragments.add(fragment);
            System.out.println(fragment);
        }
        while (fragment.size() > 1) {
            fragment = nextFragment(fragment, target);
            include = checkFragment(candidates, fragment);
            if (include) {
                listOfFragments.add(fragment);
                System.out.println(fragment);
            }
        }
        return listOfFragments;
    }

    private static boolean checkFragment(int[] candidates, List<Integer> fragment) {
        Map<Integer, Boolean> include = new HashMap<>();
        for (int elt : fragment) include.putIfAbsent(elt, false);
        for (int elt : candidates) {
            if (include.containsKey(elt)) {
                include.put(elt, true);
            }
        }
        boolean res = true;
        for (boolean isPresent : include.values())
            res &= isPresent;
        return res;
    }

    private static List<List<Integer>> minCombinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int inf = Integer.MAX_VALUE / 10 * 8;
        List<Integer> dp = new ArrayList<>();
        List<Integer> seq = new ArrayList<>();
        dp.add(0);
        seq.add(0);
        for (int i = 1; i <= target; i += 1) {
            dp.add(inf);
            seq.add(0);
            for (int elt : candidates) {
                if (elt <= i && dp.get(i) > dp.get(i - elt) + 1) {
                    dp.set(i, dp.get(i - elt) + 1);
                    seq.set(i, elt);
                }
            }
        }
        List<Integer> fragment = new ArrayList<>();
        while (target != 0) {
            fragment.add(seq.get(target));
            target -= seq.get(target);
        }
        res.add(fragment);
        return res;
    }

    private static List<Integer> nextFragment(List<Integer> fragment, int n) {
        int len = fragment.size(), i;
        for (i = 0; i < len - 1; i += 1)
            if (fragment.get(i) == 1) break;
        if (i == len - 1) {
            for (i = 0; i < len - 2; i += 1)
                if (Objects.equals(fragment.get(i), fragment.get(i + 1))) break;
        }
        int tmpInc = fragment.get(i) + 1, sum = tmpInc;
        for (int j = 0; j < i; j += 1)
            sum += fragment.get(j);
        int newLen = n - sum + i + 1;
        List<Integer> newFragment = new ArrayList<>();
        for (int j = 0; j < newLen; j += 1)
            if (j < i) newFragment.add(fragment.get(j));
            else if (j == i) newFragment.add(tmpInc);
            else newFragment.add(1);
        return newFragment;
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
//        List<List<Integer>> res = minCombinationSum(candidates, target);
        List<List<Integer>> listOfFragments = combinationSum(candidates, target);
    }
}
