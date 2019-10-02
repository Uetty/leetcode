class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        boolean isOdd = ((nums1.length + nums2.length) & 0x1) == 1;
        int middleIndex = (nums1.length + nums2.length - 1) / 2;
        int index = 0;
        for (int i = 0, j = 0; i < nums1.length || j < nums2.length; ) {
            if (i >= nums1.length) {
                if (index == middleIndex) {
                    if (isOdd) {
                        return 0.0 + nums2[j];
                    } else {
                        return (0.0 + nums2[j] + nums2[j + 1]) / 2;
                    }
                }
                j++;
            } else if (j >= nums2.length) {
                if (index == middleIndex) {
                    if (isOdd) {
                        return 0.0 + nums1[i];
                    } else {
                        return (0.0 + nums1[i] + nums1[i + 1]) / 2;  
                    } 
                }
                i++;
            } else if (nums1[i] < nums2[j]) {
                if (index == middleIndex) {
                    if (isOdd) {
                        return 0.0 + nums1[i];
                    } else {
                        int nextValue = nums2[j];
                        if (i + 1 < nums1.length && nums1[i + 1] < nextValue) {
                            nextValue = nums1[i + 1];
                        }
                        return (0.0 + nums1[i] + nextValue) / 2;
                    }
                }
                i++;
            } else {
                if (index == middleIndex) {
                    if (isOdd) {
                        return 0.0 + nums2[j];
                    } else {
                        int nextValue = nums1[i];
                        if (j + 1 < nums2.length && nums2[j + 1] < nextValue) {
                            nextValue = nums2[j + 1];
                        }
                        return (0.0 + nums2[j] + nextValue) / 2;
                    }
                }
                j++;
            }
            index++;
        }
        return 0;
    }
}