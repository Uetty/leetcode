class Solution {
    
    public int[] kWeakestRows(int[][] mat, int k) {
        int[] scores = new int[mat.length];
        int[] rank = new int[k];
        for (int i = 0; i < rank.length; i++) {
            rank[i] = -1;
        }
        
        for (int i = 0; i < mat.length; i++) {
            int score = 0;
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 1) {
                    score++;
                }
            }
            scores[i] = score;
            insert(rank, scores, i);
        }
        
        return rank;
    }
    
    private void insert(int[] rank, int[] scores, int index) {
        if (index == 0) {
            rank[0] = 0;
        }
        int i = 0;
        for (; i < index && i < rank.length; i++) {
            if (scores[rank[i]] > scores[index]) {
                break;
            }
        }
        if (i >= rank.length) {
            return;
        }
        for (int j = Math.min(index, rank.length - 1); j > i; j--) {
            rank[j] = rank[j - 1];
        }
        rank[i] = index;
    }
}