class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) return 0;

        // 保证nums1长度大于0的前提下取长度更短的一个
        if (nums1.length == 0 || nums2.length > 0 && nums1.length > nums2.length) {
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }

        // nums1左边个数的区间视为一个闭区间，作二分法
        int sectionLeft = 0;
        int sectionRight = nums1.length;
        int leftCount = (nums1.length + nums2.length) / 2;
        // nums1左边个数
        int leftCount1 = (sectionLeft + sectionRight) / 2;
        int leftCount2;
        while (true) {
            leftCount2 = leftCount - leftCount1;
            if (leftCount1 > 0 && leftCount2 < nums2.length && nums1[leftCount1 - 1] > nums2[leftCount2]) {
                sectionRight = leftCount1 - 1;
                leftCount1 = (sectionLeft + sectionRight) / 2;
            } else if (leftCount2 > 0 && leftCount1 < nums1.length && nums2[leftCount2 - 1] > nums1[leftCount1]) {
                sectionLeft = leftCount1 + 1;
                leftCount1 = (sectionLeft + sectionRight) / 2;
            } else {
                break;
            }
        }

        double middleValue = getMinValue(nums1,nums2, leftCount1, leftCount2);

        if (((nums1.length + nums2.length) & 1) == 0) {
            int middleValue2 = getMaxValue(nums1, nums2, leftCount1 - 1, leftCount2 - 1);
            middleValue = (middleValue + middleValue2) / 2;
        }

        return middleValue;
    }

    private int getMinValue(int[] nums1, int[] nums2, int index1, int index2) {
        if (index1 < 0 || index1 >= nums1.length) return nums2[index2];
        if (index2 < 0 || index2 >= nums2.length) return nums1[index1];
        return Math.min(nums1[index1], nums2[index2]);
    }

    private int getMaxValue(int[] nums1, int[] nums2, int index1, int index2) {
        if (index1 < 0 || index1 >= nums1.length) return nums2[index2];
        if (index2 < 0 || index2 >= nums2.length) return nums1[index1];
        return Math.max(nums1[index1], nums2[index2]);
    }
    
}