class Solution {
    
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] in = new int[n];
        for (int i = 0; i < leftChild.length; i++) {
            if (leftChild[i] != -1) {
                if (++in[leftChild[i]] > 1) {
                    return false;
                }
            }
            if (rightChild[i] != -1) {
                if (++in[rightChild[i]] > 1) {
                    return false;
                }
            }
        }
        
        int count1 = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == 1) {
                count1++;
            }
        }
        
        
        // 由于入参的结构，无需判断出度
        
        return count1 == n - 1;
    }
    
}