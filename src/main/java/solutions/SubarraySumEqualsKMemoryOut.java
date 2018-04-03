package solutions;
/*
* Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

我觉得我一定是脑子烧坏了才写成这样……
* */
public class SubarraySumEqualsKMemoryOut {

    public int solve(int[] nums,int k){
        return new Solution().subarraySum(nums,k);
    }
    class Solution{
        private int[][] dp;
        private int[][] sumdp;
        public int subarraySum(int[] nums, int k) {
            dp = new int[nums.length][];
            sumdp = new int[nums.length][];
            for(int i=0;i<nums.length;i++){
                dp[i]=new int[nums.length];
                sumdp[i]=new int[nums.length];
                for(int j=0;j<nums.length;j++){
                    dp[i][j]=-1;
                    if(i == j){
                        sumdp[i][j]=nums[i];
                    }else {
                        sumdp[i][j] = -1;
                    }
                }
            }
            return subarraySolve(nums,0,nums.length-1,k);
        }

        private int subarraySolve(int[] nums,int start,int end,int k){
            if(dp[start][end]!=-1)return dp[start][end];

            if(start == end){
                dp[start][end] = (nums[start]==k?1:0);
            }else if(start == end-1){
                int a = nums[start]==k?1:0;
                int b = nums[end]==k?1:0;
                int c = nums[start]+nums[end]==k?1:0;
                dp[start][end]=a+b+c;
            }else {
                int a = sum(nums,start,end) == k?1:0;
                int b = subarraySolve(nums,start,end-1,k);
                int c = subarraySolve(nums,start+1,end,k);
                int d = subarraySolve(nums,start+1,end-1,k);
                dp[start][end] = a+b+c-d;
            }
            return dp[start][end];
        }

        private int sum(int[] nums,int start,int end){
            if(sumdp[start][end] != -1){
                return sumdp[start][end];
            }
            if(start == end){
                sumdp[start][end]=nums[start];
            }else if(start == end-1){
                sumdp[start][end]=nums[start]+nums[end];
            }else{
                int halfIndex = (start+end)/2;
                int left = sum(nums,start,halfIndex);
                int right = sum(nums,halfIndex+1,end);
                sumdp[start][end] = left + right;
            }
            return sumdp[start][end];
        }
    }
}
