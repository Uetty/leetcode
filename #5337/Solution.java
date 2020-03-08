class Solution {
    public int findTheLongestSubstring(String s) {
        int m = 0;
        
        int status = 0;
        
        int[] statusPos = new int[1 << 5];
        Arrays.fill(statusPos, -1);
        statusPos[status] = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            status ^= getCode(c);
            if (statusPos[status] >= 0) {
                m = Math.max(m, i + 1 - statusPos[status]);
            } else {
                statusPos[status] = i + 1;
            }
        }
        
        return m;
    }
    
    private int getCode(char c) {
        switch (c) {
            case 'a':
                return 1;
            case 'e':
                return 2;
            case 'i':
                return 4;
            case 'o':
                return 8;
            case 'u':
                return 16;
        }
        return 0;
    }
    
}