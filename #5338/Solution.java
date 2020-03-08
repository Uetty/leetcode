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
    
    int res = 0;
	public int longestZigZag(TreeNode root) {
		res = 0;
		solve(root);
		return res - 1;
	}

	private int[] solve(TreeNode cur) {
		if (cur == null) return new int[] {0, 0};
		int[] left = solve(cur.left);
		int[] right = solve(cur.right);
		int[] ans = new int[] {left[1] + 1, right[0] + 1};
		this.res = Math.max(this.res, ans[0]);
		this.res = Math.max(this.res, ans[1]);
		return ans;
	}
}