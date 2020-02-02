class Solution {
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            Integer count = map.get(arr[i]);
            if (count == null) {
                map.put(arr[i], 1);
            } else {
                map.put(arr[i], count + 1);
            }
        }
        
        int[] counts = new int[map.size()];
        
        int i = 0;
        for (Integer val : map.values()) {
            counts[i++] = val;
        }
        
        Arrays.sort(counts);
        
        i = 0;
        for (int j = counts.length - 1; j >= 0; j--) {
            i += counts[j];
            if (i >= ((float)arr.length) / 2) {
                return counts.length - j;
            }
        }
        return counts.length;
    }
    
}