class Solution {
    public String longestCommonPrefix(String[] strs) {
        int len = minLen(strs);
        if (len == 0) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        tag:
        for (int i = 0; i < len; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (c != strs[j].charAt(i)) {
                    break tag;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
    
    private int minLen(String[] strs) {
        if (strs.length == 0) {
            return 0;
        }
        
        int len = 0;
        len = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < len) {
                len = strs[i].length();
            }
        }
        return len;
    }
}