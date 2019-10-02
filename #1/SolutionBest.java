class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> dict = new HashMap<>(nums.length / 2);
        for (int k = 0; k < nums.length; k++) {
            int coup = target - nums[k];
            if (dict.containsKey(coup)) {
                return new int[] {dict.get(coup), k};
            } else {
                dict.put(nums[k], k);
            }
        }
        return new int[] {-1, -1};
    }
    
}