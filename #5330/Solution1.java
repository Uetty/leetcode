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
    
    
    public int maxProduct(TreeNode root) {
        initSum(root);
        
        TreeNode rightRoot = root;
        
        int total = root.val;
        int lc = 0;
        int rc = total;
        
        int rightInMax = getRightInMax(root, lc, rc);
        
        int leftInMax = total - rightInMax;
        
        int m = 1000_000_000 + 7;
        
        long r = (((long) rightInMax) * leftInMax) % m;
        return (int) r;
    }
        
    private int getRightInMax(TreeNode rightRoot, int lc, int rc) {
        if (rightRoot == null) {
            return 0;
        }
        
        int divide = Math.abs(lc - rc);
        int rightInMax = rc;
        
        int left = rightRoot.left != null ? rightRoot.left.val : 0;
        int right = rightRoot.right != null ? rightRoot.right.val : 0;
        int val = rightRoot.val - left - right;
        
        int lc0 = lc + val;
        
        int d1 = Math.abs(lc0 + left - right);
        if (d1 < divide) {
            divide = d1;
            rightInMax = right;
        }
        
        
        int d2 = Math.abs(lc0 + right - left);
        if (d2 < divide) {
            divide = d2;
            rightInMax = left;
        }
        
        if (lc0 + left < right) {
            int r3 = getRightInMax(rightRoot.right, lc0 + left, right);
            int d3 = Math.abs(lc + rc - r3 - r3);
            if (d3 < divide) {
                divide = d3;
                rightInMax = r3;
            }
        }
        
        if (lc0 + right < left) {
            int r4 = getRightInMax(rightRoot.left, lc0 + right, left);
            int d4 = Math.abs(lc + rc - r4 - r4);
            if (d4 < divide) {
                divide = d4;
                rightInMax = r4;
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