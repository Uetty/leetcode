class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
        if (nums.length < 2) return list;      
        
        quickSort(nums, 0, nums.length - 1);
        
        
        int m1 = nums[0] + nums[1];
        int m2 = nums[nums.length - 2] + nums[nums.length - 1];
        int end = nums.length - 1;
        while(end > 0 && nums[end] + m1 > 0) end--;
        int start = 0;
        while(start < end && nums[start] + m2 < 0) start++;
        
        for (int left = start; left < end - 1; left++) {
            if (left > 0 && nums[left - 1] == nums[left]) continue;
            int middle = left + 1;
            int right = end;
            int exp = - nums[left];
            while (middle < right) {
                int sum = nums[middle] + nums[right];
                if (sum == exp) {
                    List<Integer> item = new ArrayList<>();
					item.add(nums[left]);
					item.add(nums[middle]);
					item.add(nums[right]);
					list.add(item);
					while (middle + 1 < right && nums[middle] == nums[middle + 1]) middle++;
                    while (right - 1 > middle && nums[right] == nums[right - 1]) right--;
                    middle++;
                    right--;
                } else if (sum < exp) {
                    while (middle + 1 < right && nums[middle] == nums[middle + 1]) middle++;
                    middle++;
                } else {
                    while (right - 1 > middle && nums[right] == nums[right - 1]) right--;
                    right--;
                }
            }
            
        }
        
		return list;
	}


	private void quickSort(int[] nums, int a, int b) {
		if (a >= b) return;
		boolean m = true;
		int l = a;
		int r = b;
		while (l < r) {
			if (m) {
				while (nums[r] > nums[l] && l < r) r--;
				swap(nums, l, r);
				if (l < r) l++;
				m = false;
			} else{
				while (nums[r] > nums[l] && l < r) l++;
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