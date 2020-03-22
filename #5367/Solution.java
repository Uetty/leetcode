class Solution {
    public String longestPrefix(String s) {
        char[] chars = s.toCharArray();
        
        int[] repeatCount = new int[s.length()];
        int lastIndex = s.length() - 1;
        char lastChar = chars[lastIndex];
        repeatCount[lastIndex] = 1;
        for (int i = s.length() - 2; i >= 0; i--) {
            if (chars[i] != lastChar) {
                lastIndex = i;
                lastChar = chars[lastIndex];
            }
            repeatCount[i] = lastIndex - i + 1;
        }
        
        for (int i  = 1; i < chars.length; i++) {
            if (chars[0] != chars[i]) {
                continue;
            }
            
            int k = 1;
            boolean valid = true;
            for (; k + i < chars.length; ) {
                if ((chars[k] ^ chars[k + i]) != 0 || repeatCount[k + i] > repeatCount[k]) {
                    valid = false;
                    break;
                }
                k += repeatCount[k + i];
            }

            if (valid) {
                return new String(chars, i, k);
            }
        }
        
        return "";
    }
}