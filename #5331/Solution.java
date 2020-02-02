class Solution {
    
	/*
	 * https://leetcode-cn.com/problems/jump-game-v/solution/dong-tai-gui-hua-by-vincent_field/
	 */
    public int maxJumps(int[] arr, int d) {
        boolean[][] reachable = new boolean[arr.length][];
        Integer[] sorted = new Integer[arr.length];
        for (int i = 0; i < sorted.length; i++) {
            sorted[i] = i;
        }
        Arrays.sort(sorted, (a1, a2) -> {
           return arr[a1] - arr[a2];
        });
        
        initReachable(arr, reachable, d);
        int[] dp = new int[arr.length];
        for (int i = 0; i < dp.length; i++) {
            int m = 0;
            for (int j = 0; j < i; j++) {
                if (reachable[sorted[i]][sorted[j]]) {
                    if (dp[j] > m) {
                        m = dp[j];
                    }
                }
            }
            dp[i] = m + 1;
        }
        
        int c = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > c) {
                c = dp[i];
            }
        }
        return c;
    }
    
    public void initReachable(int[] arr, boolean[][] reachable, int d) {
        for (int i = 0; i < reachable.length; i++) {
            reachable[i] = new boolean[arr.length];
            int left = Math.max(0, i - d);
            int right = Math.min(arr.length - 1, i + d);
            for (int j = left; j < i; j++) {
                if (arr[j] >= arr[i]) {
                    left = j + 1;
                }
            }
            for (int j = right; j > i; j--) {
                if (arr[j] >= arr[i]) {
                    right = j - 1;
                }
            }
            for (int j = left; j < i; j++) {
                reachable[i][j] = true;
            }
            for (int j = right; j > i; j--) {
                reachable[i][j] = true;
            }
        }
    }
    
}