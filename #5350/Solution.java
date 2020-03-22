class Solution {
    public int getKth(int lo, int hi, int k) {

        int[] weight = IntStream.range(lo, hi + 1)
                .map(val -> {
                    int count = 0;
                    while (val != 1) {
                        if ((val & 1) == 0) {
                            val = val >> 1;
                        } else {
                            val = (val * 3) + 1;
                        }
                        count++;
                    }
                    return count;
                }).toArray();

        Integer[] rank = new Integer[hi - lo + 1];
        for (int i = 0; i < rank.length; i++) {
            rank[i] = i;
        }
        
        Arrays.sort(rank, (a1, a2) -> {
            if (weight[a1] != weight[a2]) {
                return weight[a1] - weight[a2];
            }
            return a1 - a2;
        });

        
        return rank[k - 1] + lo;
    }
}