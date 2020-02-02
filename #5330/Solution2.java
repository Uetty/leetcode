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
    
    /*
	 * 重新考虑了一下，其实这就是一个二叉树裁剪问题，
	 * 无论如何裁剪，必然有其中一边是其中一个节点构成的一棵子树，只需要找到值最接近1/2总和的节点
	 * https://leetcode-cn.com/problems/maximum-product-of-splitted-binary-tree/solution/ding-yi-zuo-you-liang-ge-dui-zuo-bian-dui-chu-shi-/
	 */
    public int maxProduct(TreeNode root) {
        initSum(root);
        
        int rightInMax = getRightInMax(root, (float) root.val / 2);
        int leftInMax = root.val - rightInMax;
        int m = 1000_000_000 + 7;
        
        long r = (((long) rightInMax) * leftInMax) % m;
        return (int) r;
    }
    
    private int getRightInMax(TreeNode root, float target) {
        float divide = Math.abs(target - root.val);
        int rightInMax = root.val;


        if (root.val < target) {
            return rightInMax;
        }
        if (root.right != null) {
            int r1 = getRightInMax(root.right, target);
            float d1 = Math.abs(target - r1);
            if (d1 < divide) {
                divide = d1;
                rightInMax = r1;
            }
        }
        if (root.left != null) {
            int r2 = getRightInMax(root.left, target);
            float d2 = Math.abs(target - r2);
            if (d2 < divide) {
                divide = d2;
                rightInMax = r2;
            }
        }
        return rightInMax;
    }

    private void initSum(TreeNode root) {
        if (root == null) {
            return;
        }
        initSum(root.left);
        initSum(root.right);
        int sum = root.val;
        if (root.left != null) {
            sum = root.left.val + sum;
        }
        if (root.right != null) {
            sum = root.right.val + sum;
        }

        root.val = sum;
    }

}