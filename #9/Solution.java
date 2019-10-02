class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int m = x;
        int y = m % 10;
        while ((m = m / 10) > 0) {
            y = y * 10 + m % 10;
        }
        return x == y;
    }
}