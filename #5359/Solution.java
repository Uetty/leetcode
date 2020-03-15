class Solution {
    
    private static final int mod = 1000_000_007;
    
    // 小顶堆
    int[] heap;
    int size = 0;
    
    /**
     * 向下调整位置
     */
    private void slipDown(int i, int c) {
        int half = size >>> 1;
        if (i < half) {
            int m = (i << 1) + 1;   
            if (m + 1 < size && heap[m + 1] < heap[m]) {
                m = m + 1;
            }
            if (heap[m] < c) {
                heap[i] = heap[m];
                slipDown(m, c);
            } else {
                heap[i] = c;
            }
        } else {
            heap[i] = c;
        }
    }
    
    /**
     * 向上调整位置
     */
    private void slipUp(int i, int c) {
        if (i > 0) {
            int m = (i - 1) >>> 1;
            if (heap[m] > c) {
                heap[i] = heap[m];
                slipUp(m, c);
            } else {
                heap[i] = c;
            }
        } else {
            heap[i] = c;
        }
    }
    
    
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] datas = new int[speed.length][];
        for (int i = 0; i < datas.length; i++) {
            datas[i] = new int[] {efficiency[i], speed[i]};
        }
        
        Arrays.sort(datas, (d1, d2) -> {
            if (d1[0] != d2[0]) {
                return d2[0] - d1[0];
            }
            return d2[1] - d1[1];
        });
        
        long mm = 0;
        long speedSum = 0;
        
        heap = new int[k];
        size = 0;
        for (int i = 0; i < datas.length; i++) { // 遍历索引，将速度排序所在位置点亮
            if (i > 0) {
                speedSum += datas[i - 1][1];
                slipUp(size++, datas[i - 1][1]);
                if (size > k - 1) {
                    speedSum -= heap[0];
                    slipDown(0, heap[--size]);
                }
            }
            
            
            long m = (speedSum + datas[i][1]) * datas[i][0];
            if (m > mm) {
                mm = m;
            }
        }
        
        return (int)(mm % mod);
    }
}