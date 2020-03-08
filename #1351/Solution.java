class Solution {
    public int countNegatives(int[][] grid) {
        int c = 0;
        int j = 0;
        for (int i = grid.length - 1; i >= 0; i--) {
            for (; j < grid[i].length; j++) {
                if (grid[i][j] < 0) {
                    break;
                }
            }
            c += grid[i].length - j;
        }
        return c;
    }
}