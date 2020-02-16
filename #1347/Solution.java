class Solution {
    public int minSteps(String s, String t) {
        int[] sc = new int[26];
        int[] tc = new int[26];
        int len = s.length();

        for (int i = 0; i < len; i++) {
            sc[s.charAt(i) - 'a']++;
            tc[t.charAt(i) - 'a']++;
        }
        
        int div = 0;
        for (int i = 0; i < 26; i++) {
            if (sc[i] > tc[i]) {
                div += sc[i] - tc[i];
            }
        }

        return div;
    }
}