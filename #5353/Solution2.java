class Solution2 {
    public int numTimesAllBlue(int[] light) {
        int count = 0;
        int mx = 0;

        for (int i = 0; i < light.length; i++) {
            mx = Math.max(mx, light[i]);
            if (mx == (i + 1)) {
                count++;
            }
        }
        return count;
    }
    
}