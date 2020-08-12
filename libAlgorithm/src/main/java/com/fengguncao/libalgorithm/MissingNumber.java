package com.fengguncao.libalgorithm;

/**
 * name: MissingNumber<p>
 * description <p>
 *
 * @author YCKJ0165 <P>
 * date: 6/24/2020 <p>
 * copy: 重庆中科云从科技有限公司 <p>
 */
public class MissingNumber {
    public static int missingNumber(int[] nums) {
        int i = 0, j = nums.length -1;
        while (i <= j) {
            int m = (i +j) / 2;
            if (nums[m] == m) {
                i = m + 1;
            } else {
                j = m - 1;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,2,3,4,5,6,7,9};
        System.out.println(missingNumber(nums));
    }
}
