class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length <= 1) {
            return true;
        }
        int m1 = coordinates[1][1] - coordinates[0][1]; // y2 - y1
        int m2 = coordinates[1][0] - coordinates[0][0]; // x2 - x1
        
        int i = 2;
        while (i < coordinates.length) {
            if ((coordinates[i][1] - coordinates[0][1]) * m2 != (coordinates[i][0] - coordinates[0][0]) * m1) {
                return false;
            }
            i++;
        }
        return true;
    }
}