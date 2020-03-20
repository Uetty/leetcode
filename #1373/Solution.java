/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[] {0, 0, 1};
        }

        int val = node.val;
        int valid = 1;

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int max = Math.max(left[1], right[1]);

        if (left[2] != 1 || right[2] != 1) {
            valid = 0;
        } else if (node.left != null && node.left.val >= val) {
            valid = 0;
        } else if (node.right != null && node.right.val <= val) {
            valid = 0;
        }

        int sum = 0;
        if (valid == 1) {
            sum = val + left[0] + right[0];
            max = Math.max(max, sum);
        } 

        return new int[] {sum, max, valid};
    }

    public int maxSumBST(TreeNode root) {
        return dfs(root)[1];
    }
}