class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
        int count = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] < arr2[0] - d) {
                count++;
            } else if (arr1[i] > arr2[arr2.length - 1] + d) {
                count++;
            } else {
                boolean valid = true;
                for (int j = 0; j < arr2.length; j++) {
                    if (Math.abs(arr1[i] - arr2[j]) <= d) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    count++;
                }
            }
        }
        return count;
    }
}