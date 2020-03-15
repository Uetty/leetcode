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
    
    int[] arr = new int[10000];
    int len = 0;
    
    public TreeNode balanceBST(TreeNode root) {
        
        getData(root);
        arr = Arrays.copyOf(arr, len);
        Arrays.sort(arr);
        return createTree(0, len - 1);
    }
    
    private void getData(TreeNode node) {
        if (node == null) {
            return;
        }
        addNum(node.val);
        getData(node.left);
        getData(node.right);
    }
    private void addNum(int num) {
        arr[len++] = num;
    }
    
    private TreeNode createTree(int left, int right) {
        if (left > right) {
            return null;
        }
        int m = (left + right) / 2;
        TreeNode node = new TreeNode(arr[m]);
        node.left = createTree(left, m - 1);
        node.right = createTree(m + 1, right);
        return node;
    }
}