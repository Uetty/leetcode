class Solution {
    public String sortString(String s) {
        int[] count = new int[26];
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count[c - 'a']++;
            total++;
        }
        StringBuilder sb = new StringBuilder();
        boolean dir = true;
        int pos = 0;
        while (total > 0) {
            if (count[pos] > 0) {
                char cc = (char)('a' + pos);
                sb.append(cc);
                total--;
                count[pos]--;
            }
            if (dir) {
                if (pos == 25) {
                    dir = false;
                } else {
                    pos++;
                }
            } else {
                if (pos == 0) {
                    dir = true;
                } else {
                    pos--;
                }
            }
        }
        return sb.toString();
    }
}