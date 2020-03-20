/**
 * 后续挑战优解方案
 * https://leetcode-cn.com/problems/is-subsequence/solution/zhe-ge-ti-hen-jian-dan-jiu-shi-liang-ge-zhi-zhen-b/
 */
class Solution {
	
    Map<Character, Integer> map;

    int[][] nextPositions;

    public void compile(String t) {
        map = new HashMap<>();
        char[] chars = t.toCharArray();
        int idx = 0;
        for (char c : chars) {
            Integer hash = map.get(c);
            if (hash == null) {
                map.put(c, idx);
                idx++;
            }
        }

        nextPositions = new int[idx][];
        int tlen = chars.length;
        for (int i = 0; i < idx; i++) {
            nextPositions[i] = new int[tlen + 1];
            Arrays.fill(nextPositions[i], -1);
        }

        int[] counts = new int[idx];
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            
            int hash = map.get(c);
            int p = counts[hash];
            for (; p <= i; p++) {
                nextPositions[hash][p] = i;
            }
            counts[hash] = p;
        }
    }

    public boolean test(String s) {
        int pos = -1;
        for (char c : s.toCharArray()) {
            Integer hash = map.get(c);
            if (hash == null) {
                return false;
            }
            pos = nextPositions[hash][pos + 1];
            
            if (pos == -1) {
                return false;
            }
        }
        return true;
    }

    public boolean isSubsequence(String s, String t) {
        compile(t);

        return test(s);
    }
}