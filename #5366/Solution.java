class Solution {
    int[] reachable;
    
    public boolean hasValidPath(int[][] grid) {
        int rowSize = grid[0].length;
        int size = grid.length * rowSize;
        
        reachable = new int[size];
        Arrays.fill(reachable, -1);
        
        Set<Integer> set = new HashSet<>(grid.length * grid[0].length);
        
        return isCanReach(0, 0, grid, grid.length - 1, grid[0].length - 1, rowSize, set);
    }
    
    private boolean isCanReach(int i, int j, int[][] grid, int ti, int tj, int rowSize, Set<Integer> set) {
        if (i == ti && j == tj) {
            return true;
        }
        int idx = i * rowSize + j;
        if (reachable[idx] == -1) {
            initReachable(i, j, grid, rowSize);
        }
        
        set.add(idx);
        if ((reachable[idx] & 0b1) > 0 && !set.contains(idx - rowSize)) {
            boolean canReach = isCanReach(i - 1, j, grid, ti, tj, rowSize, set);
            if (canReach) {
                return true;
            }
        }
        if ((reachable[idx] & 0b10) > 0 && !set.contains(idx - 1)) {
            boolean canReach = isCanReach(i, j - 1, grid, ti, tj, rowSize, set);
            if (canReach) {
                return true;
            }
        }
        if ((reachable[idx] & 0b100) > 0 && !set.contains(idx + 1)) {
            boolean canReach = isCanReach(i, j + 1, grid, ti, tj, rowSize, set);
            if (canReach) {
                return true;
            }
        }
        if ((reachable[idx] & 0b1000) > 0 && !set.contains(idx + rowSize)) {
            boolean canReach = isCanReach(i + 1, j, grid, ti, tj, rowSize, set);
            if (canReach) {
                return true;
            }
        }
        
        set.remove(idx);
        return false;
    }
    
    private void initReachable(int i, int j, int[][] grid, int rowSize) {
        int val = grid[i][j];
        
        int reachFlag = 0;
        if (i > 0) {
            boolean isReachable = isReachable(0, grid[i][j], grid[i - 1][j]);
            if (isReachable) {
                reachFlag |= 1 << 0;
            }
        }
        if (j > 0) {
            boolean isReachable = isReachable(1, grid[i][j], grid[i][j - 1]);
            if (isReachable) {
                reachFlag |= 1 << 1;
            }
        }
        if (j + 1 < rowSize) {
            boolean isReachable = isReachable(2, grid[i][j], grid[i][j + 1]);
            if (isReachable) {
                reachFlag |= 1 << 2;
            }
        }
        if (i + 1 < grid.length) {
            boolean isReachable = isReachable(3, grid[i][j], grid[i + 1][j]);
            if (isReachable) {
                reachFlag |= 1 << 3;
            }
        }
        reachable[i * rowSize + j] = reachFlag;
    }
    
    private boolean isReachable(int flag, int val1, int val2) {
        if (flag == 0) {
            if (val1 == 2 || val1 == 5 || val1 == 6) {
                if (val2 == 2 || val2 == 3 || val2 == 4) {
                    return true;
                }
            }
        } else if (flag == 1) {
            if (val1 == 1 || val1 == 3 || val1 == 5) {
                if (val2 == 1 || val2 == 4 || val2 == 6) {
                    return true;
                }
            }
        } else if (flag == 2) {
            if (val1 == 1 || val1 == 4 || val1 == 6) {
                if (val2 == 1 || val2 == 3 || val2 == 5) {
                    return true;
                }
            }
        } else if (flag == 3) {
            if (val1 == 2 || val1 == 3 || val1 == 4) {
                if (val2 == 2 || val2 == 5 || val2 == 6) {
                    return true;
                }
            }
        }
        return false;
    }
}