class Solution {
    public List<Integer> luckyNumbers (int[][] mat) {
        
        int[] lackR = new int[mat.length];
        int[] lackC = new int[mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] < mat[i][lackR[i]]) {
                    lackR[i] = j;
                }
                if (mat[i][j] > mat[lackC[j]][j]) {
                    lackC[j] = i;
                }
            }
        }
        
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < lackR.length; i++) {
            if (lackC[lackR[i]] == i) {
                list.add(mat[i][lackR[i]]);
            }
        }
        
        return list;
    }
}