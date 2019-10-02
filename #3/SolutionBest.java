class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        int pos = 0;
        char[] chars = s.toCharArray();
        s = null;
        for (int i = 0; i < chars.length; i++) {
            for (int j = i - 1; j >= pos; j--) {
                if (chars[i] == chars[j]) {
                    pos = j + 1;
                    break;
                }
            }
            if (i - pos + 1 > maxLen) maxLen = i - pos + 1;
        }
        return maxLen;
    }
}