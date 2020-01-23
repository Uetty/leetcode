class Solution {
    public List<List<Integer>> findSolution(CustomFunction func, int z) {
        List<List<Integer>> list = new ArrayList<>();
        
        int minX = 1;
        int maxX = maxX(func, z);
        for (int i = minX; i <= maxX; i++) {
            int j = findY(func, i, z);
            if (j != 0) {
                List<Integer> r = new ArrayList<>();
                r.add(i);
                r.add(j);
                list.add(r);
            }
        }
        
        return list;
    }
    
    
    private int maxX(CustomFunction func, int z) {
        int left = 1;
        int right = 1000;
        while (left < right) {
            int mid = (left + right) / 2;
            int r = func.f(mid, 1);
            if (r == z) {
                return mid;
            } if (r > z) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (func.f(left, 1) < z) {
            return left + 1;
        }
        return left;
    }
    
    
    private int findY(CustomFunction func, int i, int z) {
        int left = 1;
        int right = 1000;
        while (left < right) {
            int mid = (left + right) / 2;
            int r = func.f(i, mid);
            if (r == z) {
                return mid;
            } if (r > z) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return 0;
    }
}