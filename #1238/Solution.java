class Solution {
    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> list = new ArrayList<Integer>();
        
        int count = (int) Math.pow(2, n);
        list.add(start);
        int last = start;
        for (int i = 1; i < count; i++) {
            int flag = 1;
            int j = i;
            while ((j & 1) == 0) {
                flag = flag << 1;
                j = j >> 1;
            }
            last = last ^ flag;
            list.add(last);
        }
        
        return list;
    }
}