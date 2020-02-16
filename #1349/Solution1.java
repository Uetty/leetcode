/**
 * 动态规划法（第二维下标是编码后的状态），更佳的方法应该用二分图
 * 每个座位坐人为二进制1，不坐人为二进制0，每行有最多8个座位，由此可以构成一个数值，作为二位数组第二维的下标
 */
class Solution {
    public int maxStudents(char[][] seats) {
        int cols = seats[0].length;
        int[][] dp = new int[seats.length + 1][];
        dp[0] = new int[1 << cols];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = new int[1 << cols];
            
            // 每个座位坐人和不坐人可以分别是1和0，一行（最多8个位置）就能构成一个数值，就是一个状态
            // loop state
            for (int j = 0; j < (1 << cols); j++) {
                // 检验该状态能不能成立（某些座位是坏的）
                boolean valid = ((j << 1) & j) == 0 && ((j >>> 1) & j) == 0;
                if (valid) {
                    for (int k = 0; k < cols; k++) {
                        if (seats[i - 1][k] == '#' && ((j >>> k) & 0x1) != 0) {
                            valid = false;
                            break;
                        }
                    }
                }
                if (!valid) {
                    dp[i][j] = -1;
                    continue;
                }

                // 与上一行的组合
                int bitCount = Integer.bitCount(j & 0xff);
                int val = -1;
                for (int k = 0; k < dp[i - 1].length; k++) {
                    if (dp[i - 1][k] == -1) {
                        continue;
                    }
                    boolean canCombined = ((j << 1) & k) == 0 && ((j >>> 1) & k) == 0;
                    if (!canCombined) {
                        continue;
                    }
                    if (bitCount + dp[i - 1][k] > val) {
                        val = bitCount + dp[i - 1][k];
                    }
                }

                dp[i][j] = val;
            }
        }

        int max = 0;
        for (int i = 0; i < dp[dp.length - 1].length; i++) {
            if (dp[dp.length - 1][i] > max) {
                max = dp[dp.length - 1][i];
            }
        }

        return max;
    }
}