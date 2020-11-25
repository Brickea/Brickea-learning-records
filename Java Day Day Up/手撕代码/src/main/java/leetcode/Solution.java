class Solution {
    public int subarraySum(int[] nums, int k) {
        // 前缀和
        Map<Integer,Integer> record = new HashMap<>();
        int res = 0;
        int sum = 0;


        for(int item: nums){
            sum += item;
            int pre = sum - k;

            int amount = record.getOrDefault(pre,0);

            res += amount;
        }
    }
}