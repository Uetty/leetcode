class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Arrays.sort(reservedSeats, (arr1, arr2) -> {
            if (arr1[0] != arr2[0]) {
                return arr1[0] - arr2[0];
            }
            return arr1[1] - arr2[1];
        });
        
        int lastRow = -1;
        boolean[] rowReserved = new boolean[4];
        int count = 0;
        
        for (int i = 0; i < reservedSeats.length; i++) {
            int row = reservedSeats[i][0] - 1;
            if (row - lastRow > 0) {
                
                if (row - lastRow > 1) {
                    count += 2 * (row - 1 - lastRow);
                }
                
                if (lastRow != -1) {
                    for (int j = 0; j < rowReserved.length - 1; j++) {
                        if (!rowReserved[j] && !rowReserved[j+1]) {
                            count++;
                            j++;
                        }
                    }
                }
                
                lastRow = row;
                rowReserved = new boolean[4]; 
            }
            
            if (reservedSeats[i][1] == 1 || reservedSeats[i][1] == 10) {
                continue;
            }
            int col = (reservedSeats[i][1] - 2) / 2;
            rowReserved[col] = true;
        }
        
        if (n - lastRow > 1) {
            count += 2 * (n - 1 - lastRow);
        }

        if (lastRow != -1) {
            for (int j = 0; j < rowReserved.length - 1; j++) {
                if (!rowReserved[j] && !rowReserved[j+1]) {
                    count++;
                    j++;
                }
            }
        }
        
        return count;
    }
}