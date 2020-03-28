class Solution {
    public int findDuplicate(int[] nums) {
        // 内部必然有环存在
        // 使用快慢指针，类似龟兔赛跑，跑的快的必然会在某一时间点领先整数圈
        // 使兔的速度比龟的速度快1，假设环的长度是s，且在第m秒使兔领先一圈，环外接入部分长度为l
        // 则这时，兔在环内跑了 2m - l，龟在环内跑了m - l，兔比龟在环内多跑了 (2m - l) - (m - l) = m
        // 兔比龟多跑了整数圈，所以必有：s * k = m (k是正整数)

        // 环外与环内的临界点（环外一点）必然与环内一点相同，且相差距离为s，所以就能通过环外一点加上m遍历环外部分，找到环内相同点

        // int m = 0;
        int slow = nums[0]; // 本题的数据，索引为0的必然不会是在环内，所以从0开始
        int fast = nums[0]; 
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
            // m++;
        } while (slow != fast);

        fast = slow; // 这时的slow与nums[0]刚好相差m
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return fast;
    }
}