class Solution {
    public int reverse(int x) {
        if (x == -Integer.MIN_VALUE) return 0;
        boolean flag = false;
        if (x < 0)  {
            flag = true;
            x = -x;
        }
        int r = x % 10;
        while((x = x / 10) > 0) {
            if (r > 0 && Integer.MAX_VALUE / r < 10) {
                return 0;
            }
            r = r * 10 + x % 10;
        }
        
        return flag ? -r : r;
    }
}