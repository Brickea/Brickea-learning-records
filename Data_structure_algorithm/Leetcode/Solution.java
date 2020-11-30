class Solution {
    public int maxSubArray(int[] nums) {
        int pre = 0;
        int res = 0;
        for(int x:nums){
            pre = Math.max(pre+x,x);
            res = Math.max(pre,res);
        }

        return res;

    }
}