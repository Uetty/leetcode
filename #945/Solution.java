class Solution {
    public int minIncrementForUnique(int[] arr) {
        if (arr.length <= 1) {
            return 0;
        }

        Arrays.sort(arr);

        int count = 0;
        int last = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] <= last) {
                count += ++last - arr[i];
            } else {
                last = arr[i];
            }
        }
        return count;
    }
}