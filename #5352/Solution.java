class Solution {
    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        
        char[] c = {'a', 'b'};
        if ((n & 1) == 0) {
            int a = n / 2;
            a = (a & 1) == 0 ? a - 1 : a;
            int b = n - a;
            addCharCount(sb, c[0], a);
            addCharCount(sb, c[1], b);
        } else {
            addCharCount(sb, c[0], n);
        }
        return sb.toString();
    }
    
    private void addCharCount(StringBuilder sb, char c, int count) {
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
    }
}