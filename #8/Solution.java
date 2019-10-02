class Solution {
    public int myAtoi(String str) {
        int i = 0;
        boolean positive = true;
        while(i < str.length()) {
            char c = str.charAt(i);
            i++;
            if (c != ' ') {
                if (c == '-') {
                    positive = false;
                } else if (c == '+') {
                } else if (c <= '9' && c >= '0') {
                    i--;
                } else {
                    return 0;
                }
                break;
            }
        }
        int val = 0;
        int detectLine = Integer.MIN_VALUE / 10 + 1;
        while (i < str.length()) {
            char c = str.charAt(i);
            i++;
            if (c >= '0' && c <= '9') {
                int num = c - '0';
                if (val < detectLine && (Integer.MIN_VALUE + num) / 10 > val) { // 越界检测
                    val = Integer.MIN_VALUE;
                    break;
                }
                val = val * 10 - num;
            } else {
                break;
            }
        }
        
        // 需进行正数越界回归
        return positive ? val == Integer.MIN_VALUE ? Integer.MAX_VALUE : -val : val;
    }
}