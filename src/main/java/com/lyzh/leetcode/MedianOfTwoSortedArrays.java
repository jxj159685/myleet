package com.lyzh.leetcode;

/**
 * 看到 log，很明显，我们只有用到二分的方法才能达到
 * 我们不妨用另一种思路，题目是求中位数，其实就是求第 k 小数的一种特殊情况，而求第 k 小数有一种算法
 * 如果是奇数个元素，中位数就是求中间元素，也就是求第 N/2 + 1 大元素， 比如 1 2 3，求的是第 3/2 + 1 大的元素
 * 如果是偶数个元素，中位数就是求中间元素，也就是求第 N/2大元素 与 第 N/2 + 1 大元素的平均值 比如 1 2 3 4， 求的是第二大元素跟第三大元素的平均值
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length; // 4
        int m = nums2.length; // 10
        int left = (n + m + 1) / 2; // 7
        int right = (n + m + 2) / 2; // 8
        // left and right just for deal with even-numbered scene， Even numbers need to find the average of the 7th and 8th largest;
        // left and right means the K which find the largest K;
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;

    }

    // Use recursion to process the kth largest
    private double getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        // 数组长度
        int len1 = end1 - start1 + 1;

        int len2 = end2 - start2 + 1;

        // When dealing with empty arrays, there must be an array with values
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        // If the first array is empty, directly return the Kth largest of the second array
        if (len1 == 0) return nums2[start2 + k - 1];

        // 如果k为1 ， 则直接返回两个数组的开始值的最小那个
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);
        // i 和 j的语义是啥 ， 2 ， 2， 我们要做的是修改其实位置重新寻找第K大， 这里的i, j ，这个语义需要联调才能出来
        // 至于为什么要取 长度跟k / 2的最小值， 如果数组长度比K / 2小，那个整个数组就可以排除
        int i = start1 + Math.min(len1, k / 2) - 1; // 数组1 第K大的位置
        int j = start2 + Math.min(len2, k / 2) - 1; // 数组2 第K大的位置

        if (nums1[i] > nums2[j]) {
            // 排除j 前面的元素 ， 数组二start位置 j + 1, 同时修改k的值为， K减去已经排除的元素 最长回文字符串

            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));

        } else {

            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (j - start1 + 1));
        }

    }

}

//
//    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
//        int len1 = end1 - start1 + 1;
//        int len2 = end2 - start2 + 1;
//        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
//        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
//        if (len1 == 0) return nums2[start2 + k - 1];
//
//        if (k == 1) return Math.min(nums1[start1], nums2[start2]);
//
//        int i = start1 + Math.min(len1, k / 2) - 1;
//        int j = start2 + Math.min(len2, k / 2) - 1;
//
//        if (nums1[i] > nums2[j]) {
//            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
//        }
//        else {
//            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
//        }
//    }

