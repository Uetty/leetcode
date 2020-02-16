class Solution {
    public boolean checkIfExist(int[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                if (i < arr.length - 1 && arr[i + 1] == 0) {
                    return true;
                }
            } else if (arr[i] > 0) {
                if (i < arr.length - 1 && findVal(arr, i + 1, arr.length - 1, 2 * arr[i])) {
                    return true;
                }
            } else {
                if (i > 0 && findVal(arr, 0, i - 1, 2 * arr[i])) {
                    return true;
                }
            }
            
        }
        return false;
    }
    
    public boolean findVal(int[] arr, int start, int end, int val) {
        if (arr[start] > val || arr[end] < val) {
            return false;
        }
        while (start <= end) {
            int middle = (start + end) / 2;
            if (arr[middle] == val) {
                return true;
            }
            if (arr[middle] < val) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
            
        }
        
        return false;
    }
}