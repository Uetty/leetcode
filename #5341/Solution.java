class ProductOfNumbers {

    int[] nums = new int[40000];
    int nc = 0;
    int[] muls = new int[40000];
    int mc = 0;
    int mulNc = 0;
    
    public ProductOfNumbers() {
    }
    
    public void add(int num) {
        if (nc < nums.length) {
            nums[nc++] = num;
        } else {
            int overflow = nums[0];
            for (int i = 0; i < nums.length - 1; ) {
                nums[i] = nums[++i];
            }
            nums[nums.length - 1] = num;
            
            mc = 0;
            mulNc = 0;
            muls = new int[40000];
        }
        
    }
    
    public int getProduct(int k) {
        int mul = 0;
        if (mc >= k && mulNc == nc) {
            return muls[k - 1];
        } else {
            
            int i = 0;
            if (mulNc == nc && mc > 0) {
                i = mc;
            } else {
                muls[0] = nums[nc - 1];
                i = 1;
            }
            for (; i < k; i++)  {
                muls[i] = muls[i - 1] * nums[nc - 1 - i] ;
            }
            mc = k;
            mulNc = nc;
            return muls[k - 1];
        }
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */