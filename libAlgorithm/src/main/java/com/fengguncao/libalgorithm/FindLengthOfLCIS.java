package com.fengguncao.libalgorithm;

/**
 * name: FindLengthOfLCIS<p>
 *
 * @author YCKJ0165 <P>
 * date: 6/24/2020 <p>
 * copy: 重庆中科云从科技有限公司 <p>
 */
public class FindLengthOfLCIS {
    public static int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0, count = 1, max = 1;
        for (; i < nums.length -1; i ++) {
            if (nums[i + 1] > nums[i]) {
                count ++;
            } else {
                count = 1;
            }
            max = count > max ? count : max;
        }
        return max ;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2 , 5, 8, 0};
        System.out.println(findLengthOfLCIS(nums));
        //System.out.println(findLengthOfLCIS1(nums));
    }

    public static int findLengthOfLCIS1(int[] nums) {
        int max = 0, anchor = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i > 0 && nums[i-1] >= nums[i]) {
                anchor = i;
            }
            max = Math.max(max, i - anchor + 1);
        }
        return max;
    }
}
