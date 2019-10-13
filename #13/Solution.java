class Solution {
    public int romanToInt(String s) {
        int[] ROM_NUM = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] ROM = {"M","CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        int i = 0;
        int num = 0;
        while (i < ROM.length) {
            if (s.startsWith(ROM[i])) {
                num += ROM_NUM[i];
                s = s.substring(ROM[i].length());
            } else {
                i++;
            }
        }
        return num;
    }
}