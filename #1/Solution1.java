class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        quickSort(copy, 0, copy.length - 1);
        int i = 0;
        int j = copy.length - 1;
        while (i < j) {
            if (copy[i] + copy[j] == target) break;
            else if (copy[i] + copy[j] < target) i++;
            else j--;
        }
        i = findIndex(nums, copy[i], -1);
        j = findIndex(nums, copy[j], i);
        return new int[] {i, j};
    }
    
    private int findIndex(int[] nums, int v, int exp) {
        int index = 0;
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] == v && k != exp) {
                index = k;
                break;
            }
        }
        return index;
    }
    
    private void quickSort(int[] nums, int a, int b) {
        if (a >= b) return;
        boolean m = true;
        int l = a;
        int r = b;
        while (l < r) {
            if (m) {
                while (nums[r] > nums[l] && l < r) {
                    r--;
                }
                swap(nums, l, r);
                if (l < r) l++;
                m = false;
            } else{
                while (nums[r] > nums[l] && l < r) {
                    l++;
                }
                swap(nums, l, r);
                if (l < r) r--;
                m = true;
            }
        }
        if (a < l - 1) quickSort(nums, a, l - 1);
        if (b > l + 1) quickSort(nums, l + 1, b);
    }
    
    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}