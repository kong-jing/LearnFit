package com.fengguncao.libalgorithm;

/**
 * name: RemoveDuplicates<p>
 * description <p>
 *
 * @author YCKJ0165 <P>
 * date: 6/22/2020 <p>
 * copy: 重庆中科云从科技有限公司 <p>
 */
public class RemoveDuplicates {
    public static int removeDuplicates(int[] nums) {
        int i = 0, j = 1;
        for (; j < nums.length; j ++) {
            if (nums[i] != nums[j]) {
                if (++i == j) {
                    continue;
                }
                nums[i] = nums[j];
                System.out.println(nums[i]);
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        int[] arr = {0,0,1,1,1,2,2,3,3,4};
        System.out.println("length " + removeDuplicates(arr));
    }
}
