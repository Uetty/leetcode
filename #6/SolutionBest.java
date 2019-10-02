class Solution {
    public String convert(String s, int m) {
        if (m == 1 || s.length() == 0) return s;
        int k = (m - 2) + m;
        int n = s.length();
        int blockNum = n / k + (n % k == 0 ? 0 : 1);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < blockNum; j++) {
                int charIndex = k * j + i;
                if (charIndex >= n) break;
                sb.append(s.charAt(charIndex));
                if (i > 0 && i < m - 1) {
                    charIndex = k * j + 2 * m - i - 2;
                    if (charIndex >= n) break;
                    sb.append(s.charAt(charIndex));
                }
            }
            addLineSeparator(sb);
        }
        
        return sb.toString();
    }
    
//    private int addCharInIndex(StringBuilder sb, int cursor, int index, char c) {
//        while(cursor <= index) {
//            if (cursor == index) {
//                sb.append(c);
//            } else {
//                sb.append(" ");
//            }
//            cursor++;
//        }
//        return cursor;
//    }
    
//    private void addLineSeparator(StringBuilder sb) {
//        sb.append("\n");
//    }
    
    private int addCharInIndex(StringBuilder sb, int cursor, int index, char c) {
        sb.append(c);
        return cursor;
    }
    
    private void addLineSeparator(StringBuilder sb) {
    }
}

https://leetcode-cn.com/problems/zigzag-conversion/solution/mei-xing-shu-chu-jie-fa-fu-dai-tian-chong-kong-ge-/