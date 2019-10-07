class Solution {
    public int maxArea(int[] height) {
		// 双指针法
        int i = 0;
        int j = height.length - 1;
        int max = (j - i) * (height[i] < height[j] ? height[i] : height[j]);
        
        while (i < j - 1) {
            
            if (height[i] < height[j]) {
                while (height[i++] >= height[i] && i < j);
            } else {
                while (height[j--] >= height[j] && i < j);
            }
            
            int temp;
            if ((temp = (j - i) * (height[i] < height[j] ? height[i] : height[j])) > max) {
                max = temp;
            }
        }
        return max;
    }
    
}