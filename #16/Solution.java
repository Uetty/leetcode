class Solution {
    public int threeSumClosest(int[] nums, int target) {
        quickSort(nums);
        
        int[] tars = new int[nums.length];
        for (int i = 0; i < tars.length; i++) {
            tars[i] = target - nums[i];
        }
        
        int min = target - nums[0] - nums[1] - nums[2];
        boolean gt = min < 0;
        if (gt) {
            min = -min;
        }
        
        // 双指针法，优势是利用有序的数组，减少可能的组合数量
        // 此外有两个优化点
        // 一个优化点在于，从n个里取3个不重复的数，本身组合数是C(n, 3)，j应该从i+1开始
        // 另一个优化点在于提前去掉不存在的可能性的i
        for (int i = 0; i < tars.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            
            // 排除掉不可能的情况
            if (nums[j] + nums[j] - tars[i] > min) {
                continue;
            }
            if (tars[i] - nums[k] - nums[k] > min) {
                continue;
            }
            while (j < k) {
                int div = tars[i] - nums[j] - nums[k];
                if (div == 0) {
                    return target;
                } else if (div < 0) {
                    if (-div < min) {
                        min = -div;
                        gt = true;
                    }
                    k--;
                } else {
                    if (div < min) {
                        min = div;
                        gt = false;
                    }
                    j++;
                }
            }
        }
        return gt ? target + min : target - min;
    }
    
    private void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }
    
    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        boolean asc = true;
        while (l < r) {
            if (asc) {
                while(nums[l] <= nums[r] && l < --r);
            } else {
                while(nums[l] <= nums[r] && ++l < r);
            }
            swap(nums, l, r);
            asc = !asc;
        }
        quickSort(nums, left, l - 1);
        quickSort(nums, l + 1, right);
    }
    
    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}